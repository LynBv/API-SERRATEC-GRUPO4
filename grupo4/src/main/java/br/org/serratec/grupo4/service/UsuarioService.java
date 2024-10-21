package br.org.serratec.grupo4.service;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.org.serratec.grupo4.domain.Usuario;
import br.org.serratec.grupo4.dto.UsuarioDTO;
import br.org.serratec.grupo4.dto.UsuarioInserirDTO;
import br.org.serratec.grupo4.exception.EmailException;
import br.org.serratec.grupo4.exception.SenhaException;
import br.org.serratec.grupo4.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Autowired
	private FotoService fotoService;

	public Optional<Usuario> buscarPorId(Long id) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		return usuario;
	}

	public List<UsuarioDTO> buscarTodos() {
		List<Usuario> usuarios = usuarioRepository.findAll();
		List<UsuarioDTO> usuariosDTO = usuarios.stream().map(UsuarioDTO::new).toList();
		return usuariosDTO;
	}

	public UsuarioDTO inserir(UsuarioInserirDTO usuarioInserirDTO) throws SenhaException, EmailException {
		if (!usuarioInserirDTO.getSenha().equals(usuarioInserirDTO.getConfirmaSenha())) {
			throw new SenhaException("Senha e Confirma Senha não são iguais");
		}
		if (usuarioRepository.findByEmail(usuarioInserirDTO.getEmail()) != null) {
			throw new EmailException("Email já existente");
		}
		Usuario usuario = new Usuario();
		usuario.setNome(usuarioInserirDTO.getNome());
		usuario.setEmail(usuarioInserirDTO.getEmail());
		usuario.setSobrenome(usuarioInserirDTO.getSobrenome());
		usuario.setDataNascimento(usuarioInserirDTO.getDataNascimento());
		usuario.setSenha(encoder.encode(usuarioInserirDTO.getSenha()));

		usuario = usuarioRepository.save(usuario);

		UsuarioDTO usuarioDTO = new UsuarioDTO(usuario);
		return usuarioDTO;
	}

	//////////////////////////////////////////////////////////////////

	public List<UsuarioDTO> listar() {
		List<UsuarioDTO> usuarios = usuarioRepository.findAll().stream().map(f -> adicionarImagemUri(f)).toList();
		return usuarios;
	}

	////////////////////////////////////////////////////////////////

	public UsuarioDTO buscar(Long id) {
		Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
		if (usuarioOpt.isEmpty()) {
			return null;
		}
		return adicionarImagemUri(usuarioOpt.get());
	}

	public UsuarioDTO inserir(Usuario usuario, MultipartFile file) throws IOException {
		usuario = usuarioRepository.save(usuario);
		fotoService.inserir(usuario, file);
		return adicionarImagemUri(usuario);
	}

	///////////////////////////////////////////////////////////////////

	public UsuarioDTO adicionarImagemUri(Usuario usuario) {
		URI uri = ServletUriComponentsBuilder
				.fromCurrentContextPath()
				.path("/usuarios/{id}/foto")
				.buildAndExpand(usuario.getId())
				.toUri();
		UsuarioDTO dto = new UsuarioDTO();
		dto.setNome(usuario.getNome());
		dto.setSobrenome(usuario.getSobrenome());
		dto.setEmail(usuario.getEmail());
		dto.setDataNascimento(usuario.getDataNascimento());
		dto.setUrl(uri.toString());
		return dto;
	}

	public UsuarioDTO inserirFoto(UsuarioInserirDTO usuarioInserirDTO, MultipartFile file)
			throws IOException, SenhaException, EmailException {
		if (!usuarioInserirDTO.getSenha().equals(usuarioInserirDTO.getConfirmaSenha())) {
			throw new SenhaException("Senha e Confirma Senha não são iguais");
		}
		if (usuarioRepository.findByEmail(usuarioInserirDTO.getEmail()) != null) {
			throw new EmailException("Email já existente");
		}

		Usuario usuario = new Usuario();
		usuario.setNome(usuarioInserirDTO.getNome());
		usuario.setEmail(usuarioInserirDTO.getEmail());
		usuario.setSobrenome(usuarioInserirDTO.getSobrenome());
		usuario.setDataNascimento(usuarioInserirDTO.getDataNascimento());
		usuario.setSenha(encoder.encode(usuarioInserirDTO.getSenha()));

		usuario = usuarioRepository.save(usuario);
		fotoService.inserir(usuario, file);

		return adicionarImagemUri(usuario);
	}

}

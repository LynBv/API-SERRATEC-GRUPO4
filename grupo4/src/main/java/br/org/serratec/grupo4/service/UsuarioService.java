
package br.org.serratec.grupo4.service;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.org.serratec.grupo4.domain.Relacionamento;
import br.org.serratec.grupo4.domain.Usuario;
import br.org.serratec.grupo4.dto.SeguindoDTO;
import br.org.serratec.grupo4.dto.UsuarioDTO;
import br.org.serratec.grupo4.dto.UsuarioInserirDTO;
import br.org.serratec.grupo4.exception.EmailException;
import br.org.serratec.grupo4.exception.IdUsuarioInvalido;
import br.org.serratec.grupo4.exception.ProprietarioIncompativelException;
import br.org.serratec.grupo4.exception.SenhaException;
import br.org.serratec.grupo4.repository.UsuarioRepository;
import br.org.serratec.grupo4.security.JwtUtil;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private FotoService fotoService;

	public List<UsuarioDTO> listarUsuariosFoto() {
		List<UsuarioDTO> usuarios = usuarioRepository.findAll().stream().map(f -> adicionarImagemUri(f)).toList();
		return usuarios;
	}

	public List<UsuarioDTO> ListarUsuarios() {
		List<Usuario> usuarios = usuarioRepository.findAll();
		List<UsuarioDTO> usuariosDTO = usuarios.stream().map(UsuarioDTO::new).toList();
		return usuariosDTO;
	}

	public UsuarioDTO buscarPorId(Long id) {
		Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
		if (usuarioOpt.isEmpty()) {
			throw new IdUsuarioInvalido("Id do usuario não encontrado");
		}

		Usuario usuario = usuarioOpt.get();
		UsuarioDTO usuarioDto = new UsuarioDTO(usuario);
        if (usuario.getFoto() == null) {
            return usuarioDto;
        }
        usuarioDto = adicionarImagemUri(usuario);


		return usuarioDto;
	}

	public Optional<UsuarioDTO> buscarPorNome(String nome) {
		Optional<Usuario> usuario = Optional.ofNullable(usuarioRepository.findByNome(nome));
		Optional<UsuarioDTO> usuariodto = Optional.ofNullable(new UsuarioDTO(usuario.get()));
		return usuariodto;
	}

	public Optional<UsuarioDTO> buscarPorEmail(String email) {
		Optional<Usuario> usuario = Optional.ofNullable(usuarioRepository.findByEmail(email));
		Optional<UsuarioDTO> usuariodto = Optional.ofNullable(new UsuarioDTO(usuario.get()));
		return usuariodto;
	}


	public UsuarioDTO inserir(UsuarioInserirDTO usuarioInserirDTO, MultipartFile file)
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
		usuario.setUrl(usuarioInserirDTO.getUrl());
		UsuarioDTO usuarioDTO = new UsuarioDTO(usuario);

		usuarioRepository.save(usuario);
		if (file == null) {
			return usuarioDTO;
		} else {
			fotoService.inserir(usuario, file);

		}

		return adicionarImagemUri(usuario);
	}

	public UsuarioDTO atualizar(UsuarioInserirDTO usuarioInserirDTO, Long id, String bearerToken, MultipartFile file) {

		Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
		if (usuarioOpt.isEmpty()) {
			throw new IdUsuarioInvalido("Id do usuario não encontrado");
		}

		Long idtoken = jwtUtil.getId(bearerToken);
		if (!id.equals(idtoken)) {
			throw new ProprietarioIncompativelException("Voce so pode alterar seu usuario");
		}

		if (!usuarioInserirDTO.getSenha().equals(usuarioInserirDTO.getConfirmaSenha())) {
			throw new SenhaException("Senha e Confirma Senha não são iguais");
		}

		Usuario usuario = usuarioOpt.get();

		if (!usuarioInserirDTO.getEmail().equals(usuario.getEmail())
				&& usuarioRepository.findByEmail(usuarioInserirDTO.getEmail()) != null) {
			throw new EmailException("Email já existente");
		}

		usuario.setId(id);
		usuario.setNome(usuarioInserirDTO.getNome());
		usuario.setEmail(usuarioInserirDTO.getEmail());
		usuario.setSobrenome(usuarioInserirDTO.getSobrenome());
		usuario.setDataNascimento(usuarioInserirDTO.getDataNascimento());
		usuario.setSenha(encoder.encode(usuarioInserirDTO.getSenha()));

		if (file == null || file.isEmpty()) {
			usuarioRepository.save(usuario);
			return new UsuarioDTO(usuario);
		}
		try {
			fotoService.atualizar(usuario, file);
			
		} catch (IOException e) {
			return null;
		}
		usuarioRepository.save(usuario);
		UsuarioDTO usuarioDTO = adicionarImagemUri(usuario);

		return usuarioDTO;
	}

	public UsuarioDTO adicionarImagemUri(Usuario usuario) {
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/usuario/{id}/foto")
				.buildAndExpand(usuario.getId()).toUri();
		UsuarioDTO dto = new UsuarioDTO();
		dto.setId(usuario.getId());
		dto.setNome(usuario.getNome());
		dto.setSobrenome(usuario.getSobrenome());
		dto.setEmail(usuario.getEmail());
		dto.setDataNascimento(usuario.getDataNascimento());
		dto.setUrl(uri.toString());
		usuario.setUrl(uri.toString());
		usuarioRepository.save(usuario);

		return dto;
	}

    public List<SeguindoDTO> ListarSeguindoUsuario(Long id) {

        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
        if (usuarioOpt.isEmpty()) {
            throw new IdUsuarioInvalido("Id do usuario não encontrado");
        }
        Set<Relacionamento> seguindo = usuarioOpt.get().getSeguidos();

        List<SeguindoDTO> seguindoDTO = seguindo.stream().map(SeguindoDTO::new).toList();

        return seguindoDTO;
    }

}

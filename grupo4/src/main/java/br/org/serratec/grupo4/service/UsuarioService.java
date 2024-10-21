package br.org.serratec.grupo4.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.org.serratec.grupo4.domain.Usuario;
import br.org.serratec.grupo4.dto.UsuarioDTO;
import br.org.serratec.grupo4.dto.UsuarioInserirDTO;
import br.org.serratec.grupo4.exception.EmailException;
import br.org.serratec.grupo4.exception.IdUsuarioInvalido;
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


    /* public List<Usuario> ListarSeguidoresUsuario(Long id) {
	
		List<Usuario> seguidores =  buscarPorId(id).get().getSeguidores();
		List<UsuarioDTO> seguidoresDTO = seguidores.stream().map(SeguidorDTO::new).toList();
	} */
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

    public Optional<UsuarioDTO> buscarPorId(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        Optional<UsuarioDTO> usuariodto = Optional.ofNullable(new UsuarioDTO(usuario.get()));
        return usuariodto;
    }

    public List<UsuarioDTO> ListarTodos() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<UsuarioDTO> usuariosDTO = usuarios.stream().map(UsuarioDTO::new).toList();
        return usuariosDTO;
    }

    public UsuarioDTO inserir(UsuarioInserirDTO usuarioInserirDTO)
            throws SenhaException, EmailException {

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

    public UsuarioDTO atualizar(UsuarioInserirDTO usuarioInserirDTO, Long id, String bearerToken)
            throws RuntimeException, SenhaException, EmailException, IdUsuarioInvalido {

        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
		if (usuarioOpt.isEmpty()) {
			throw new IdUsuarioInvalido("Id do usuario não encontrado");
		}

        Long idtoken = jwtUtil.getId(bearerToken);
        if (!id.equals(idtoken)) {
            throw new RuntimeException("Voce so pode alterar seu usuario");
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

        usuario = usuarioRepository.save(usuario);

        UsuarioDTO usuarioDTO = new UsuarioDTO(usuario);
        return usuarioDTO;
    }
}

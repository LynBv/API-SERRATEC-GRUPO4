package br.org.serratec.grupo4.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.grupo4.domain.Comentario;
import br.org.serratec.grupo4.domain.Usuario;
import br.org.serratec.grupo4.dto.ComentarioDTO;
import br.org.serratec.grupo4.dto.ComentarioInserirDTO;
import br.org.serratec.grupo4.exception.IdUsuarioInvalido;
import br.org.serratec.grupo4.repository.ComentarioRepository;
import br.org.serratec.grupo4.repository.UsuarioRepository;
import br.org.serratec.grupo4.security.JwtUtil;

@Service
public class ComentarioService {

    @Autowired
    ComentarioRepository comentarioRepository;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<ComentarioDTO> buscarTodos() {
    	List<Comentario> comentarios = comentarioRepository.findAll();
    	List<ComentarioDTO> comentariosDTO = comentarios.stream().map(ComentarioDTO::new).toList();
    	return comentariosDTO;
    }
    
    public Optional<ComentarioDTO> buscarPorId(Long id) {
        Optional<Comentario> comentario = comentarioRepository.findById(id);
        Optional<ComentarioDTO> comentarioDTO = Optional.ofNullable(new ComentarioDTO(comentario.get()));
        return comentarioDTO;
    }


    public ComentarioDTO inserir(ComentarioInserirDTO comentarioInserirDTO, String bearerToken) {
        Comentario comentario = new Comentario();
        //comentario.setPostagem(comentarioInserirDTO.getPostagem());
        comentario.setTexto(comentarioInserirDTO.getTexto());
        comentario.setDataCriacao(LocalDate.now());

        Long id = jwtUtil.getId(bearerToken);
        Optional<Usuario> usuarioOPT = usuarioRepository.findById(id);
        if (usuarioOPT.isEmpty()) {
            throw new IdUsuarioInvalido("Usuário não encontrado");
        }
        //comentario.setUsuario(usuarioOPT.get());

        comentario = comentarioRepository.save(comentario);
        ComentarioDTO comentarioDTO = new ComentarioDTO(comentario);
        return comentarioDTO;
    }

    public ComentarioDTO atualizar(Long id, ComentarioInserirDTO comentarioInserirDTO, String bearerToken) {

        Optional<Comentario> comentarioOPT = comentarioRepository.findById(id);

        if (comentarioOPT.isEmpty()) {
            throw new RuntimeException("Comentario não encontrado");
        }
        /*  Long idtoken = jwtUtil.getId(bearerToken);

        if (!comentarioOPT.get().getUsuario().getId().equals(idtoken)) {
            throw new RuntimeException("Voce so pode alterar suas proprias postagens");
        } */

        Comentario comentario = comentarioOPT.get();
        comentario.setId(id);
        comentario.setTexto(comentarioInserirDTO.getTexto());
        //comentario.setUsuario(comentarioInserirDTO.getUsuario());

        comentario = comentarioRepository.save(comentario);

        ComentarioDTO comentarioDTO = new ComentarioDTO(comentario);
        return comentarioDTO;
    }
}

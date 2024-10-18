package br.org.serratec.grupo4.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.grupo4.domain.Comentario;
import br.org.serratec.grupo4.dto.ComentarioDTO;
import br.org.serratec.grupo4.dto.ComentarioInserirDTO;
import br.org.serratec.grupo4.repository.ComentarioRepository;


@Service
public class ComentarioService {

    @Autowired
    ComentarioRepository comentarioRepository;

     public Optional<Comentario> buscarPorId(Long id) {
        Optional<Comentario> comentario = comentarioRepository.findById(id);
        return comentario;
    }

    public List<ComentarioDTO> buscarTodos(Long id) {
        List<Comentario> comentarios = comentarioRepository.findAll();
		List<ComentarioDTO> comentariosDTO = comentarios.stream().map(ComentarioDTO::new).toList();
		return comentariosDTO; 
    }

    public ComentarioDTO inserir(ComentarioInserirDTO comentarioInserirDTO){
        Comentario comentario = new Comentario();
        comentario.setDataCriacao(comentarioInserirDTO.getDataCriacao());
        comentario.setPostagem(comentarioInserirDTO.getPostagem());
        comentario.setTexto(comentarioInserirDTO.getTexto());

        comentario = comentarioRepository.save(comentario);
        ComentarioDTO comentarioDTO = new ComentarioDTO(comentario);
        return comentarioDTO;
    }
}

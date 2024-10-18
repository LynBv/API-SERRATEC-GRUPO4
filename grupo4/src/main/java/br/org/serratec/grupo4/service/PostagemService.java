package br.org.serratec.grupo4.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.grupo4.domain.Postagem;
import br.org.serratec.grupo4.dto.PostagemDTO;
import br.org.serratec.grupo4.dto.PostagemInserirDTO;
import br.org.serratec.grupo4.repository.PostagemRepository;

@Service
public class PostagemService {

    @Autowired
    PostagemRepository  postagemRepository;

     public Optional<Postagem> buscarPorId(Long id) {
        Optional<Postagem> postagem = postagemRepository.findById(id);
        return postagem;
    }

    public List<PostagemDTO> buscarTodos() {
        List<Postagem> postagems = postagemRepository.findAll();
		List<PostagemDTO> postagemsDTO = postagems.stream().map(PostagemDTO::new).toList();
		return postagemsDTO; 
    }

    public PostagemDTO inserir(PostagemInserirDTO postagemInserirDTO){
        Postagem postagem = new Postagem();
        postagem.setConteudo(postagemInserirDTO.getConteudo());
        postagem.setDataCriacao(postagemInserirDTO.getDataCriacao());
        postagem.setUsuario(postagemInserirDTO.getUsuario());

        postagem = postagemRepository.save(postagem);

        PostagemDTO postagemDTO = new PostagemDTO(postagem);
        return postagemDTO;

    }

}

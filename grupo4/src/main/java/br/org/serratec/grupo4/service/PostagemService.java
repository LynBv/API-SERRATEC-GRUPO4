package br.org.serratec.grupo4.service;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.grupo4.domain.Postagem;
import br.org.serratec.grupo4.domain.Usuario;
import br.org.serratec.grupo4.dto.PostagemDTO;
import br.org.serratec.grupo4.dto.PostagemInserirDTO;
import br.org.serratec.grupo4.exception.IdUsuarioInvalido;
import br.org.serratec.grupo4.repository.PostagemRepository;
import br.org.serratec.grupo4.repository.UsuarioRepository;
import br.org.serratec.grupo4.security.JwtUtil;

@Service
public class PostagemService {

    @Autowired
    PostagemRepository  postagemRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
	private JwtUtil jwtUtil;

    @Autowired 
    UsuarioService usuarioService;

     public Optional<Postagem> buscarPorId(Long id) {
        Optional<Postagem> postagem = postagemRepository.findById(id);
        return postagem;
    }

    public List<PostagemDTO> buscarTodos() {
        List<Postagem> postagems = postagemRepository.findAll();
		List<PostagemDTO> postagemsDTO = postagems.stream().map(PostagemDTO::new).toList();
		return postagemsDTO; 
    }

    public PostagemDTO inserir(PostagemInserirDTO postagemInserirDTO, String bearerToken) throws  IdUsuarioInvalido{

        Postagem postagem = new Postagem();
        postagem.setConteudo(postagemInserirDTO.getConteudo());
        postagem.setDataCriacao( LocalDate.now());
        String token = bearerToken.substring(7);
        Long id = jwtUtil.getId(token);
        System.out.println(id);

        Optional<Usuario> usuarioOPT = usuarioRepository.findById(id);

        if (usuarioOPT.isEmpty()) {
            throw new IdUsuarioInvalido("Usuário não encontrado");
          
        }
        postagem.setUsuario(usuarioOPT.get());


        postagem = postagemRepository.save(postagem);

        PostagemDTO postagemDTO = new PostagemDTO(postagem);
        return postagemDTO;
    }

}

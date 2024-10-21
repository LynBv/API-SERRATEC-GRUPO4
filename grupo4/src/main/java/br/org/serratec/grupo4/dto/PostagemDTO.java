package br.org.serratec.grupo4.dto;


import java.util.List;

import br.org.serratec.grupo4.domain.Comentario;
import br.org.serratec.grupo4.domain.Postagem;
import br.org.serratec.grupo4.domain.Usuario;

public class PostagemDTO {

	private Long id;
	private String conteudo;
	private List<ComentarioDTO> comentarios;
	
	private String usuarioNome;

	public PostagemDTO() {
	}


	public PostagemDTO(Long id, String conteudo, List<ComentarioDTO> comentarios, String usuarioNome) {
		super();
		this.id = id;
		this.conteudo = conteudo;
		this.comentarios = comentarios;
		this.usuarioNome = usuarioNome;
	}



	public PostagemDTO(Postagem postagem) {
		this.id = postagem.getId();
		this.conteudo = postagem.getConteudo();
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getConteudo() {
		return conteudo;
	}


	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}


	public List<ComentarioDTO> getComentarios() {
		return comentarios;
	}


	public void setComentarios(List<ComentarioDTO> comentarios) {
		this.comentarios = comentarios;
	}


	public String getUsuarioNome() {
		return usuarioNome;
	}


	public void setUsuarioNome(String usuarioNome) {
		this.usuarioNome = usuarioNome;
	}

	
}

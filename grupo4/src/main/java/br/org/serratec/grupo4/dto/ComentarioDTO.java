package br.org.serratec.grupo4.dto;

import br.org.serratec.grupo4.domain.Comentario;
import br.org.serratec.grupo4.domain.Postagem;

public class ComentarioDTO {

	private Long id;
	private String texto;
	private Postagem postagem;

	public ComentarioDTO() {
	}

	public ComentarioDTO(Long id, String texto, Postagem postagem) {
		super();
		this.id = id;
		this.texto = texto;
		this.postagem = postagem;
	}

	public ComentarioDTO(Comentario comentario) {
		this.id = comentario.getId();
		this.texto = comentario.getTexto();
		this.postagem = comentario.getPostagem();
		;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Postagem getPostagem() {
		return postagem;
	}

	public void setPostagem(Postagem postagem) {
		this.postagem = postagem;
	}

}

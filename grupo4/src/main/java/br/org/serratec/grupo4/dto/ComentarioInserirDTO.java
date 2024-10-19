package br.org.serratec.grupo4.dto;

import br.org.serratec.grupo4.domain.Postagem;

public class ComentarioInserirDTO {

	private String texto;
	private Postagem postagem;

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

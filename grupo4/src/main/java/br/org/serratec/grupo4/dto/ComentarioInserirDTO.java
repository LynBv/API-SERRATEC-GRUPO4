package br.org.serratec.grupo4.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ComentarioInserirDTO {

	@NotNull(message = "Comentário não pode estar vazio!!")
	@NotBlank(message = "Comentário não pode estar em branco!!")
	@Size(max = 400, message = "Comentario não pode ultraprassar o limite de (max) caracteres!!")
	@Schema(description = "Id do Texto")
	private String texto;
	
	@Schema(description = "Id da Postagem")
	private Long idPostagem;

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Long getIdPostagem() {
		return idPostagem;
	}

	public void setIdPostagem(Long idPostagem) {
		this.idPostagem = idPostagem;
	}
}

package br.org.serratec.grupo4.dto;

import java.time.LocalDate;

import br.org.serratec.grupo4.domain.Comentario;
import br.org.serratec.grupo4.domain.Postagem;

public class ComentarioDTO {

	private String usuarioNome;

	private Long id;

	private String texto;

	private LocalDate dataCriacao;

	public ComentarioDTO() {
	}

	public ComentarioDTO(Long id, String texto, Postagem postagem) {
		super();
		this.id = id;
		this.texto = texto;
	}

	public ComentarioDTO(Comentario comentario) {
		this.id = comentario.getId();
		this.texto = comentario.getTexto();
		this.dataCriacao = comentario.getDataCriacao();
		this.usuarioNome = comentario.getUsuario().getNome();
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

	public String getUsuarioNome() {
		return usuarioNome;
	}

	public void setUsuarioNome(String usuarioNome) {
		this.usuarioNome = usuarioNome;
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	
}

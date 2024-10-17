package br.org.serratec.grupo4.dto;

import java.time.LocalDate;
import java.util.List;

import br.org.serratec.grupo4.domain.Comentario;
import br.org.serratec.grupo4.domain.Usuario;

public class PostagemInserirDTO {

	private String conteudo;
	private LocalDate dataCriacao;
	private List<Comentario> comentarios;
	private Usuario usuario;

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}

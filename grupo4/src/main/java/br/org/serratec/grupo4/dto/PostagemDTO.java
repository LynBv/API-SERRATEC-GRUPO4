package br.org.serratec.grupo4.dto;

import java.time.LocalDate;
import java.util.List;

import br.org.serratec.grupo4.domain.Comentario;
import br.org.serratec.grupo4.domain.Postagem;
import br.org.serratec.grupo4.domain.Usuario;

public class PostagemDTO {

	private Long id;
	private String conteudo;
	private List<Comentario> comentarios;
	private Usuario usuario;

	public PostagemDTO() {
	}

	public PostagemDTO(Long id, String conteudo, LocalDate dataCriacao, List<Comentario> comentarios, Usuario usuario) {
		super();
		this.id = id;
		this.conteudo = conteudo;
		this.comentarios = comentarios;
		this.usuario = usuario;
	}

	public PostagemDTO(Postagem postagem) {
		this.id = postagem.getId();
		this.conteudo = postagem.getConteudo();
		this.comentarios = postagem.getComentarios();
		this.usuario = postagem.getUsuario();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public Usuario getIdUsuario() {
		return usuario;
	}

	public void setIdUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}

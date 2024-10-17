package br.org.serratec.grupo4.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import br.org.serratec.grupo4.domain.Postagem;
import br.org.serratec.grupo4.domain.Relacionamento;

public class UsuarioInserirDTO {

	private Long id;
	private String nome;
	private String sobrenome;
	private String email;
	private String senha;
	private LocalDate dataNascimento;
	private List<Postagem> postagem;
	private Set<Relacionamento> relacionamentos;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public List<Postagem> getPostagem() {
		return postagem;
	}
	public void setPostagem(List<Postagem> postagem) {
		this.postagem = postagem;
	}
	public Set<Relacionamento> getRelacionamentos() {
		return relacionamentos;
	}
	public void setRelacionamentos(Set<Relacionamento> relacionamentos) {
		this.relacionamentos = relacionamentos;
	}
	
	
	
}

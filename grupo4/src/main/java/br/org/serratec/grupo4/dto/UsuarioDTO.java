package br.org.serratec.grupo4.dto;

import java.util.Set;

import br.org.serratec.grupo4.domain.Relacionamento;
import br.org.serratec.grupo4.domain.Usuario;

public class UsuarioDTO {
	private Long id;
	private String nome;
	private String sobrenome;
	private String email;
	private String senha;
	private String dataNascimento;
	private Set<Relacionamento> relacionamentos;
	
	public UsuarioDTO(Usuario usuario) {
		this.id = usuario.get
	}
	
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
	public String getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public Set<Relacionamento> getRelacionamentos() {
		return relacionamentos;
	}
	public void setRelacionamentos(Set<Relacionamento> relacionamentos) {
		this.relacionamentos = relacionamentos;
	}
	
	
}
 
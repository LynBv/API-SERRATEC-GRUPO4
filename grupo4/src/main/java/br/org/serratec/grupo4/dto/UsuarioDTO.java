package br.org.serratec.grupo4.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.org.serratec.grupo4.domain.Usuario;


public class UsuarioDTO { // devolve para o usuario que vai ver na api 
	
	private Long id;
	
	private String nome;
	
	private String sobrenome;

	private String email;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataNascimento;

	public UsuarioDTO() {
	}

	public UsuarioDTO(Long id, String nome, String sobrenome, String email, LocalDate dataNascimento) {
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.email = email;
		this.dataNascimento = dataNascimento;
	}

	public UsuarioDTO(Usuario usuario) {
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.sobrenome = usuario.getSobrenome();
		this.email = usuario.getEmail();
		this.dataNascimento = usuario.getDataNascimento();
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

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

}
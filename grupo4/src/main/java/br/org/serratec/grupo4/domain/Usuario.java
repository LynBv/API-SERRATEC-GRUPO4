package br.org.serratec.grupo4.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Valid
public class Usuario implements UserDetails, Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Column(name = "id_usuario")
	private Long id;

	@NotBlank(message = "Nome não pode estar em branco!!")
	@Size(max = 100, message = "Nome não pode ultraprassar o limite de (max) caracteres!!")
	@Column(nullable = false, length = 100)
	private String nome;

	@NotBlank(message = "Sobrenome não pode estar em branco!!")
	@Size(max = 100, message = "Sobrenome não pode ultraprassar o limite de (max) caracteres!!")
	@Column(nullable = false, length = 100)
	private String sobrenome;

	@NotBlank(message = "E-mail não pode estar EM Branco!!")
	@NotNull(message = "E-mail não pode estar vazio!!")
	@Size(max = 150, message = "E-mail não pode ultraprassar o limite de (max) caracteres!!")
	@Column(nullable = false, length = 150, unique = true)
	private String email;

	@NotBlank(message = "Senha não pode estar vazia ou em Branco!!")
	@NotNull(message = "Senha não pode estar nula!!")
	@Size(min = 6, message = "Senha deve ter no mínimo (min) caracteres!!")
	@Column(nullable = false)
	private String senha;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "data_nascimento")
	private LocalDate dataNascimento;
	
	@OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
	private Foto foto;

	@JsonManagedReference
	@OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Postagem> postagens = new ArrayList<>();

	@OneToMany(mappedBy = "id.usuario", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<UsuarioRelacionamento> usuarioRelacionamento = new HashSet<>();

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return Collections.emptyList();
	}

	@Override
	public String getPassword() {

		return senha;
	}

	@Override
	public String getUsername() {
		return email;
	}

}

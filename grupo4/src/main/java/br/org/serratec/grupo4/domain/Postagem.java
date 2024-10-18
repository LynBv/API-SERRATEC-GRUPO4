package br.org.serratec.grupo4.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Valid
public class Postagem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	@NotBlank(message = "Conteudo não pode estar em branco!!")
	@Size(max = 400, message = "Conteudo não pode ultraprassar o limite de (max) caracteres!!")
	@Column(name = "conteudo", nullable = false, length = 400)	
	private String conteudo;
	
	@UpdateTimestamp
	@Column(name = "data_criacao", nullable = false, updatable = true)
	private LocalDate dataCriacao;

	@JsonManagedReference
	@OneToMany(mappedBy = "postagem", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Comentario> comentarios = new ArrayList<>();

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "id_usuario", nullable = false)
	private Usuario usuario;

}

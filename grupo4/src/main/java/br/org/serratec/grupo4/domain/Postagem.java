package br.org.serratec.grupo4.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Postagem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	private String conteudo;
	private LocalDate dataCriacao;

	@JsonManagedReference
	@OneToMany(mappedBy = "postagem", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Comentario> comentarios = new ArrayList<>();

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "id_usuario", nullable = false)
	private Usuario usuario;
	
	 

}

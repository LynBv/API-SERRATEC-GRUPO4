package br.org.serratec.grupo4.domain;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;


import jakarta.persistence.Entity;
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
	@OneToMany(mappedBy = "comentario") 
	private List<Comentario> comentarios;
	
	@JsonBackReference 
	@ManyToOne 
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;
	
	

}

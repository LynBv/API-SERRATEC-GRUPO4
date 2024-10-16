package br.org.serratec.grupo4.domain;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode
public class Comentario {
	
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message ="Comenário não pode estar vazio!!")
	@Column(name = "comentario", length = 400)
	private String texto;
	
	@NotBlank(message= "Preencha à Data do Comentário.")
	@Column(name= "data_criacao", nullable= false)
	private LocalDate dataCriacao;

	@JsonBackReference 
	@ManyToOne 
	@JoinColumn(name = "id_postagem")
	private Postagem postagem;
}

package br.org.serratec.grupo4.domain;

import java.time.LocalDate;

import org.hibernate.annotations.UpdateTimestamp;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode
@Valid
public class Comentario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(description = "Id do Comentário")
	private Long id;

	@NotNull(message = "Comenário não pode estar vazio!!")
	@Column(name = "conteudo_coment", length = 400)
	@Schema(description = "Texto do Comentario")
	private String texto;

	@NotBlank(message = "Preencha à Data do Comentário.")
	@Column(name = "data_criacao", nullable = false)
	@UpdateTimestamp
	@Schema(description = "Data da criação do Comentário")
	private LocalDate dataCriacao;

	@ManyToOne
	@JoinColumn(name = "id_postagem", nullable = false)
	@Schema(description = "Postagem do Comentário")
	private Postagem postagem;

	@ManyToOne
	@JoinColumn(name = "id_usuario", nullable = false)
	private Usuario usuario; //usuário que fez a postagem 

}

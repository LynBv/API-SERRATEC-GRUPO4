package br.org.serratec.grupo4.domain;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Valid
public class Relacionamento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_relacionamento")
	@EqualsAndHashCode.Include
	private Long id;
	
	
	@NotBlank
	@CreationTimestamp
	@Column(name="data_inicio_seguimento", nullable = false, updatable = false)
	private LocalDate dataInicioSeguimento;

}

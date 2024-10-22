package br.org.serratec.grupo4.domain;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Valid
public class Relacionamento {
	
	
	   @EmbeddedId
	    private UsuarioRelacionamentoPK id; 

	    @NotNull
	    @CreationTimestamp
	    @Column(name="data_inicio_seguimento", nullable = false, updatable = false)
	    private LocalDate dataInicioSeguimento;


}

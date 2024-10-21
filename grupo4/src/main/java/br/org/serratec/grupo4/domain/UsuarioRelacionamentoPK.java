package br.org.serratec.grupo4.domain;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@Embeddable
public class UsuarioRelacionamentoPK implements Serializable {

	private static final long serialVersionUID = 1L;	
	
	@ManyToOne
	@JoinColumn(name = "id_seguidor", nullable = false)
	private Usuario seguidor;
	
	@ManyToOne
	@JoinColumn(name = "id_seguido", nullable = false)
	private Usuario seguido;

}

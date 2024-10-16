package br.org.serratec.grupo4.domain;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "usuario_relacionamento")
@NoArgsConstructor

	public class UsuarioRelacionamento {
    
    @EmbeddedId 
    private UsuarioRelacionamentoPK id = new UsuarioRelacionamentoPK();
    
    @Column(name = "data_relacionamento")
    private LocalDate dataRelacionamento;
    
	public UsuarioRelacionamento(Usuario usuario, Relacionamento relacionamento, LocalDate dataRelacinamento) {

		this.id.setUsuario(usuario);
		this.id.setRelacionamento(relacionamento);
		this.dataRelacionamento = dataRelacionamento;
	}

}

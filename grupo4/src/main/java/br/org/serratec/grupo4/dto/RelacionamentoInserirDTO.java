package br.org.serratec.grupo4.dto;

public class RelacionamentoInserirDTO {

    private Long idSeguido;

    
    public RelacionamentoInserirDTO() {
    }

    public RelacionamentoInserirDTO(Long idSeguido) {
        this.idSeguido = idSeguido;
    }

    public Long getIdSeguido() {
        return idSeguido;
    }

    public void setIdSeguido(Long idSeguido) {
        this.idSeguido = idSeguido;
    }
}

package br.org.serratec.grupo4.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.org.serratec.grupo4.domain.Usuario;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;

public class UsuarioDTO {

    @Schema(description = "Id")
    private Long id;

    @Schema(description = "Nome")
    private String nome;

    @Schema(description = "Sobrenome")
    private String sobrenome;

    @Schema(description = "Email")
    private String email;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataNascimento;

    @Column(name = "url")
    private String url;

    public UsuarioDTO() {
    }

    public UsuarioDTO(Long id, String nome, String sobrenome, String email, LocalDate dataNascimento, String url) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.url = url;
    }

    public UsuarioDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.sobrenome = usuario.getSobrenome();
        this.email = usuario.getEmail();
        this.dataNascimento = usuario.getDataNascimento();
        this.url = usuario.getUrl();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}

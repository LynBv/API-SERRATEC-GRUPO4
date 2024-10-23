package br.org.serratec.grupo4.dto;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Valid
public class UsuarioInserirDTO {

    @NotBlank(message = "Nome não pode estar em branco!!")
    @NotNull(message = "Nome não pode estar vazio!!")
    @Size(max = 100, message = "Nome não pode ultraprassar o limite de (max) caracteres!!")
    @Schema(description = "Nome")
    private String nome;

    @NotBlank(message = "Sobrenome não pode estar em branco!!")
    @NotNull(message = "Sobrenome não pode estar vazio!!")
    @Size(max = 100, message = "Sobrenome não pode ultraprassar o limite de (max) caracteres!!")
    @Schema(description = "Sobrenome")
    private String sobrenome;

    @NotBlank(message = "E-mail não pode estar EM Branco!!")
    @NotNull(message = "E-mail não pode estar vazio!!")
    @Schema(description = "Email")
    private String email;

    @NotBlank(message = "Senha não pode estar vazia ou em Branco!!")
    @NotNull(message = "Senha não pode estar nula!!")
    @Size(min = 6, message = "Senha deve ter no mínimo (min) caracteres!!")
    @Schema(description = "Senha")
    private String senha;

    @NotBlank(message = "Confirmar Senha não pode estar vazia ou em Branco!!")
    @NotNull(message = "Confirmar Senha não pode estar nula!!")
    @Size(min = 6, message = "Confirmar Senha deve ter no mínimo (min) caracteres!!")
    private String confirmaSenha;

    @NotNull(message = "Data de Nascimento não pode estar vazia!!")
    private LocalDate dataNascimento;

    private String url;

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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getConfirmaSenha() {
        return confirmaSenha;
    }

    public void setConfirmaSenha(String confirmaSenha) {
        this.confirmaSenha = confirmaSenha;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}

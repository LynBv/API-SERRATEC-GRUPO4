package br.org.serratec.grupo4.dto;

import jakarta.validation.constraints.Size;

public class LoginDTO {


	@Size(max = 100, message = "Username não pode ultraprassar o limite de (max) caracteres!!")
	    public String username;
	
	@Size(min = 6, message = "Senha deve ter no mínimo (min) caracteres!!")
    public String password;
	
	
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    
}

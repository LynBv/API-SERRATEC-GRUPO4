package br.org.serratec.grupo4.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.serratec.grupo4.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByEmail(String email);
    
}

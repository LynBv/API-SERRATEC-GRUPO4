package br.org.serratec.grupo4.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.serratec.grupo4.domain.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

}

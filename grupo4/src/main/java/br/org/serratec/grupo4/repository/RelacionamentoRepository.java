package br.org.serratec.grupo4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.serratec.grupo4.domain.Relacionamento;

@Repository
public interface RelacionamentoRepository extends JpaRepository<Relacionamento, Long> {

}

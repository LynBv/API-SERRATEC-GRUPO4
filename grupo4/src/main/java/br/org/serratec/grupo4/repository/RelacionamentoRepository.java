package br.org.serratec.grupo4.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.org.serratec.grupo4.domain.Relacionamento;
import br.org.serratec.grupo4.domain.UsuarioRelacionamentoPK;

@Repository
public interface RelacionamentoRepository extends JpaRepository<Relacionamento, UsuarioRelacionamentoPK> {
	
	  @Query(value = """
            SELECT SGD.NOME AS nome_Seguidor, SG.NOME AS nome_Seguindo, R.DATA_INICIO_SEGUIMENTO
            FROM RELACIONAMENTO AS R
            INNER JOIN USUARIO AS SGD ON R.ID_SEGUIDOR = SGD.ID_USUARIO
            INNER JOIN USUARIO AS SG ON R.ID_SEGUIDO = SG.ID_USUARIO
            WHERE R.ID_SEGUIDOR = :usuarioId
            ORDER BY SG.NOME
            """, nativeQuery = true)
    List<Map<String, Object>> findSeguidoresPorUsuarioId(@Param("usuarioId") Long usuarioId);
    
    @Query(value = """
    		SELECT SG.NOME AS nome_Seguidor, SGD.NOME AS nome_Seguindo, R.DATA_INICIO_SEGUIMENTO
            FROM RELACIONAMENTO AS R
            INNER JOIN USUARIO AS SG ON R.ID_SEGUIDOR = SG.ID_USUARIO
            INNER JOIN USUARIO AS SGD ON R.ID_SEGUIDO = SGD.ID_USUARIO
            WHERE R.ID_SEGUIDO = :usuarioId
            ORDER BY SGD.NOME
            """, nativeQuery = true)
    List<Map<String, Object>> findSeguindoPorUsuarioId(@Param("usuarioId") Long usuarioId);
}

package br.org.serratec.grupo4.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.org.serratec.grupo4.domain.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {


	Usuario findByEmail(String email);

	Usuario findByNome(String nome);

	@Query(value = """
					SELECT u.nome AS nome, c.data_criacao AS dataComentario, c.conteudo_coment AS conteudo 	
					FROM usuario AS u
					INNER JOIN
					comentario AS c
					ON u.id_usuario = c.id_usuario 
					WHERE c.id_postagem=:postagemId """, nativeQuery = true)

	List<Map<String, Object>> findNomeEDataComentarioByPostagemId(@Param("postagemId") Long postagemId);
}


    
    /*//QUERY PARA BUSCAR SEGUIDORES POR EMAIL
    @Query("SELECT ur.id.usuario FROM UsuarioRelacionamento ur WHERE ur.id.relacionamento.usuario.email = :email")
    public List<Usuario> findSeguidoresByEmail(@Param("email") String email);*/



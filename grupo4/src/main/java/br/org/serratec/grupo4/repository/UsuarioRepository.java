package br.org.serratec.grupo4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.serratec.grupo4.domain.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByEmail(String email);
    //Usuario findiByNome	(String nome);
    
    /*//QUERY PARA BUSCAR SEGUIDORES POR EMAIL
    @Query("SELECT ur.id.usuario FROM UsuarioRelacionamento ur WHERE ur.id.relacionamento.usuario.email = :email")
    public List<Usuario> findSeguidoresByEmail(@Param("email") String email);*/

	
    
}

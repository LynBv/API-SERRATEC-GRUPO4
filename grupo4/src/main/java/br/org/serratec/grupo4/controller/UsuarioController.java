package br.org.serratec.grupo4.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.org.serratec.grupo4.domain.Usuario;
import br.org.serratec.grupo4.repository.UsuarioRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	
	@Operation(summary = "📝 Lista todos os usuarios", description = "Todos os Usuarios")
	@ApiResponses(
		value = {
			@ApiResponse(responseCode = "200", description = "Operação efetuada com sucesso ｡◕‿◕｡"),
			@ApiResponse(responseCode = "401", description = "Erro na autenticação (•ิ_•ิ)"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado ⊙▂⊙"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação |˚–˚|") 
		}
	)
	@GetMapping
	public ResponseEntity<List<Usuario>> listar() {
		return ResponseEntity.ok(usuarioRepository.findAll());
	}

	
	
	@Operation(summary = "📖 Lista Paginado", description = ":)")
	@ApiResponses(
			value = {
					@ApiResponse(responseCode = "200", description = "Operação efetuada com sucesso ｡◕‿◕｡"),
					@ApiResponse(responseCode = "401", description = "Erro na autenticação (•ิ_•ิ)"),
					@ApiResponse(responseCode = "404", description = "Recurso não encontrado ⊙▂⊙"),
					@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação |˚–˚|") 
			}
		)
	@GetMapping("/pagina")
	public ResponseEntity<Page<Usuario>> listarPaginado(
			@PageableDefault(direction = Sort.Direction.ASC, page = 0, size = 8) Pageable pageable) {
		return ResponseEntity.ok(usuarioRepository.findAll(pageable));
	}

	
	@Operation(summary = "🔎 Busca o usuario pelo Id", description = "Verifique se o id está correto :)")
	@ApiResponses(
			value = {
					@ApiResponse(responseCode = "200", description = "Operação efetuada com sucesso ｡◕‿◕｡"),
					@ApiResponse(responseCode = "401", description = "Erro na autenticação (•ิ_•ิ)"),
					@ApiResponse(responseCode = "404", description = "Recurso não encontrado ⊙▂⊙"),
					@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação |˚–˚|") 
			}
		)
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> buscar(@PathVariable Long id) {
		Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
		if (usuarioOpt.isPresent()) {
			return ResponseEntity.ok(usuarioOpt.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@Operation(summary = "📚 Inserir um novo usuario", description = ":)")
	@ApiResponses(
			value = {
					@ApiResponse(responseCode = "200", description = "Operação efetuada com sucesso ｡◕‿◕｡"),
					@ApiResponse(responseCode = "401", description = "Erro na autenticação (•ิ_•ิ)"),
					@ApiResponse(responseCode = "404", description = "Recurso não encontrado ⊙▂⊙"),
					@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação |˚–˚|")  
			}
		)
	@PostMapping
	public ResponseEntity<Usuario> inserir(@Valid @RequestBody Usuario usuario) {
		Usuario usuarioDTO = usuarioRepository.save(usuario);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuarioDTO.getId())
				.toUri();
		return ResponseEntity.created(uri).body(usuarioDTO);
	}

	@Operation(summary = "🔢 Atualiza o usuario pelo id", description = "Verifique se o id está correto :)")
	@ApiResponses(
			value = {
					@ApiResponse(responseCode = "200", description = "Operação efetuada com sucesso ｡◕‿◕｡"),
					@ApiResponse(responseCode = "401", description = "Erro na autenticação (•ิ_•ิ)"),
					@ApiResponse(responseCode = "404", description = "Recurso não encontrado ⊙▂⊙"),
					@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação |˚–˚|") 
			}
		)
	@PutMapping("/{id}")
	public ResponseEntity<Usuario> atualizar(@PathVariable Long id, @Valid @RequestBody Usuario usuario) {
		if (usuarioRepository.existsById(id)) {
			usuario.setId(id);
			return ResponseEntity.ok(usuarioRepository.save(usuario));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@Operation(summary = "❌ Deleta o usuario pelo id", description = "Verifique se o id está correto :)")
	@ApiResponses(
			value = {
					@ApiResponse(responseCode = "200", description = "Operação efetuada com sucesso ｡◕‿◕｡"),
					@ApiResponse(responseCode = "401", description = "Erro na autenticação (•ิ_•ิ)"),
					@ApiResponse(responseCode = "404", description = "Recurso não encontrado ⊙▂⊙"),
					@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação |˚–˚|")  
			}
		)
	@DeleteMapping("/{id}")
	public ResponseEntity<Usuario> deletar(@PathVariable Long id) {
		if (usuarioRepository.existsById(id)) {
			usuarioRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}

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

import br.org.serratec.grupo4.domain.Postagem;
import br.org.serratec.grupo4.repository.PostagemRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/postagem")
public class PostagemController {
	@Autowired
	private PostagemRepository postagemRepository;

	
	@Operation(summary = "📝 Lista todos as postagens", description = "Todos as Postagens")
	@ApiResponses(
		value = {
			@ApiResponse(responseCode = "200", description = "Operação efetuada com sucesso ｡◕‿◕｡"),
			@ApiResponse(responseCode = "401", description = "Erro na autenticação (•ิ_•ิ)"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado ⊙▂⊙"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação |˚–˚|") 
		}
	)
	@GetMapping
	public ResponseEntity<List<Postagem>> listar() {
		return ResponseEntity.ok(postagemRepository.findAll());
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
	public ResponseEntity<Page<Postagem>> listarPaginado(
			@PageableDefault(direction = Sort.Direction.ASC, page = 0, size = 8) Pageable pageable) {
		return ResponseEntity.ok(postagemRepository.findAll(pageable));
	}

	@Operation(summary = "🔎 Busca a postagem pelo Id", description = "Verifique se o id está correto :)")
	@ApiResponses(
			value = {
					@ApiResponse(responseCode = "200", description = "Operação efetuada com sucesso ｡◕‿◕｡"),
					@ApiResponse(responseCode = "401", description = "Erro na autenticação (•ิ_•ิ)"),
					@ApiResponse(responseCode = "404", description = "Recurso não encontrado ⊙▂⊙"),
					@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação |˚–˚|") 
			}
		)
	@GetMapping("/{id}")
	public ResponseEntity<Postagem> buscar(@PathVariable Long id) {
		Optional<Postagem> postagemOpt = postagemRepository.findById(id);
		if (postagemOpt.isPresent()) {
			return ResponseEntity.ok(postagemOpt.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@Operation(summary = "📚 Inserir uma nova postagem", description = ":)")
	@ApiResponses(
			value = {
					@ApiResponse(responseCode = "200", description = "Operação efetuada com sucesso ｡◕‿◕｡"),
					@ApiResponse(responseCode = "401", description = "Erro na autenticação (•ิ_•ิ)"),
					@ApiResponse(responseCode = "404", description = "Recurso não encontrado ⊙▂⊙"),
					@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação |˚–˚|")  
			}
		)
	@PostMapping
	public ResponseEntity<Postagem> inserir(@Valid @RequestBody Postagem postagem) {
		Postagem postagemDTO = postagemRepository.save(postagem);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(postagemDTO.getId())
				.toUri();
		
		return ResponseEntity.created(uri).body(postagemDTO);
	}

	@Operation(summary = "🔢 Atualiza a postagem pelo id", description = "Verifique se o id está correto :)")
	@ApiResponses(
			value = {
					@ApiResponse(responseCode = "200", description = "Operação efetuada com sucesso ｡◕‿◕｡"),
					@ApiResponse(responseCode = "401", description = "Erro na autenticação (•ิ_•ิ)"),
					@ApiResponse(responseCode = "404", description = "Recurso não encontrado ⊙▂⊙"),
					@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação |˚–˚|") 
			}
		)
	@PutMapping("/{id}")
	public ResponseEntity<Postagem> atualizar(@PathVariable Long id, @Valid @RequestBody Postagem postagem) {
		if (postagemRepository.existsById(id)) {
			postagem.setId(id);
			return ResponseEntity.ok(postagemRepository.save(postagem));
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	
	@Operation(summary = "❌ Deleta a postagem pelo id", description = "Verifique se o id está correto :)")
	@ApiResponses(
			value = {
					@ApiResponse(responseCode = "200", description = "Operação efetuada com sucesso ｡◕‿◕｡"),
					@ApiResponse(responseCode = "401", description = "Erro na autenticação (•ิ_•ิ)"),
					@ApiResponse(responseCode = "404", description = "Recurso não encontrado ⊙▂⊙"),
					@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação |˚–˚|")  
			}
		)
	@DeleteMapping("/{id}")
	public ResponseEntity<Postagem> deletar(@PathVariable Long id) {
		if (postagemRepository.existsById(id)) {
			postagemRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}

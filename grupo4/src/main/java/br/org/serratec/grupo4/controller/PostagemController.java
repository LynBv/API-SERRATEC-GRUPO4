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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.org.serratec.grupo4.domain.Postagem;
import br.org.serratec.grupo4.dto.PostagemDTO;
import br.org.serratec.grupo4.dto.PostagemInserirDTO;
import br.org.serratec.grupo4.repository.PostagemRepository;
import br.org.serratec.grupo4.service.PostagemService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/postagem")
public class PostagemController {
	@Autowired
	private PostagemService postagemService;
	
	@Autowired
	private PostagemRepository postagemRepository; 

	@GetMapping
	public ResponseEntity<List<PostagemDTO>> listar() {
		return ResponseEntity.ok(postagemService.buscarTodos());
	}

	@GetMapping("/pagina")
	public ResponseEntity<Page<Postagem>> listarPaginado(
			@PageableDefault(direction = Sort.Direction.ASC, page = 0, size = 8) Pageable pageable) {
		return ResponseEntity.ok(postagemRepository.findAll(pageable));
	}

	@GetMapping("/{id}")
	public ResponseEntity<PostagemDTO> buscar(@PathVariable Long id) {
		Optional<Postagem> postagemOpt = postagemService.buscarPorId(id);
		if (postagemOpt.isPresent()) {
			PostagemDTO postagemDTO = new PostagemDTO(postagemOpt.get());
			return ResponseEntity.ok(postagemDTO);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<PostagemDTO> inserir(@Valid @RequestBody PostagemInserirDTO postagem) {
		PostagemDTO postagemDTO = postagemService.inserir(postagem);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(postagemDTO.getId())
				.toUri();
		
		return ResponseEntity.created(uri).body(postagemDTO);
	}

	@PutMapping("/{id}")
	public ResponseEntity<PostagemDTO> atualizar(@PathVariable Long id, @Valid @RequestBody PostagemInserirDTO postagem) {
		if (postagemRepository.existsById(id)) {
			
			return ResponseEntity.ok(postagemService.inserir(postagem));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<PostagemDTO> deletar(@PathVariable Long id) {
		if (postagemRepository.existsById(id)) {
			postagemRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}

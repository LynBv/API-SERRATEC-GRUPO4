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
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/postagem")
public class PostagemController {
	@Autowired
	private PostagemRepository postagemRepository;

	@GetMapping
	public ResponseEntity<List<Postagem>> listar() {
		return ResponseEntity.ok(postagemRepository.findAll());
	}

	@GetMapping("/pagina")
	public ResponseEntity<Page<Postagem>> listarPaginado(
			@PageableDefault(direction = Sort.Direction.ASC, page = 0, size = 8) Pageable pageable) {
		return ResponseEntity.ok(postagemRepository.findAll(pageable));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Postagem> buscar(@PathVariable Long id) {
		Optional<Postagem> postagemOpt = postagemRepository.findById(id);
		if (postagemOpt.isPresent()) {
			return ResponseEntity.ok(postagemOpt.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

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

	@PutMapping("/{id}")
	public ResponseEntity<Postagem> atualizar(@PathVariable Long id, @Valid @RequestBody Postagem postagem) {
		if (postagemRepository.existsById(id)) {
			postagem.setId(id);
			return ResponseEntity.ok(postagemRepository.save(postagem));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

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

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

import br.org.serratec.grupo4.domain.Comentario;
import br.org.serratec.grupo4.repository.ComentarioRepository;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/comentarios")
public class ComentarioController {

	@Autowired
	private ComentarioRepository comentarioRepository;
	
	@GetMapping
	public ResponseEntity<List<Comentario>> listar(){
		return ResponseEntity.ok(comentarioRepository.findAll());
	}
	
	@GetMapping("/pagina")
	public ResponseEntity<Page<Comentario>> listarPaginado(@PageableDefault
			(direction= Sort.Direction.ASC, page= 0 ,size =8) Pageable pageable){
		return ResponseEntity.ok(comentarioRepository.findAll(pageable));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Comentario>buscar(@PathVariable Long id){
		Optional<Comentario>comentarioOpt = comentarioRepository.findById(id);
		if (comentarioOpt.isPresent()) {
			return ResponseEntity.ok(comentarioOpt.get());
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping
	public ResponseEntity<Comentario>inserir(@Valid @RequestBody Comentario comentario){
		Comentario comentarioDTO = comentarioRepository.save(comentario);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(comentarioDTO.getId())
				.toUri();
		return ResponseEntity.created(uri).body(comentarioDTO);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Comentario> atualizar(@PathVariable Long id, @Valid @RequestBody Comentario comentario){
       if (comentarioRepository.existsById(id)) {
    	   comentario.setId(id);
    	   return ResponseEntity.ok(comentarioRepository.save(comentario));
       }else {
    	   return ResponseEntity.notFound().build();
       }
    }
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Comentario> deletar(@PathVariable Long id){
		if (comentarioRepository.existsById(id)){
			comentarioRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
}

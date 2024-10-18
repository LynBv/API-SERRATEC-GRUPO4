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

import br.org.serratec.grupo4.domain.Comentario;
import br.org.serratec.grupo4.dto.ComentarioDTO;
import br.org.serratec.grupo4.dto.ComentarioInserirDTO;
import br.org.serratec.grupo4.repository.ComentarioRepository;
import br.org.serratec.grupo4.service.ComentarioService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/comentarios")
public class ComentarioController {

	@Autowired
	private ComentarioService comentarioService;
	
	@Autowired
	private ComentarioRepository comentarioRepository;
	
	@GetMapping
	public ResponseEntity<List<ComentarioDTO>> listar(){
		return ResponseEntity.ok(comentarioService.buscarTodos());
	}
	
	@GetMapping("/pagina")
	public ResponseEntity<Page<Comentario>> listarPaginado(@PageableDefault
			(direction= Sort.Direction.ASC, page= 0 ,size =8) Pageable pageable){
		return ResponseEntity.ok(comentarioRepository.findAll(pageable));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ComentarioDTO>buscar(@PathVariable Long id){
		Optional<Comentario>comentarioOpt = comentarioService.buscarPorId(id);
		if (comentarioOpt.isPresent()) {
			ComentarioDTO comentarioDTO = new ComentarioDTO(comentarioOpt.get());
			return ResponseEntity.ok(comentarioDTO);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping
	public ResponseEntity<ComentarioDTO>inserir(@Valid @RequestBody ComentarioInserirDTO comentario){
		ComentarioDTO comentarioDTO =  comentarioService.inserir(comentario);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(comentarioDTO.getId())
				.toUri();
		return ResponseEntity.created(uri).body(comentarioDTO);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ComentarioDTO> atualizar(@PathVariable Long id, @Valid @RequestBody ComentarioInserirDTO comentario){
       if (comentarioRepository.existsById(id)) {
    	   return ResponseEntity.ok(comentarioService.inserir(comentario));
       }else {
    	   return ResponseEntity.notFound().build();
       }
    }
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ComentarioDTO> deletar(@PathVariable Long id){
		if (comentarioRepository.existsById(id)){
			comentarioRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
}

package br.org.serratec.grupo4.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.serratec.grupo4.dto.RelacionamentoDTO;
import br.org.serratec.grupo4.dto.SeguindoDTO;
import br.org.serratec.grupo4.service.RelacionamentoService;

@RestController
@RequestMapping("/relacionamentos")
public class RelacionamentoController {

	@Autowired
	private RelacionamentoService relacionamentoService;
	
	@GetMapping("/seguem")
	public ResponseEntity<List<RelacionamentoDTO>>meusSeguidores(@RequestHeader("Authorization") String token) {
        List<RelacionamentoDTO> seguidores = relacionamentoService.ListarSeguidoresUsuario(token);
        return ResponseEntity.ok(seguidores);
    }
	
	@GetMapping("/sigo")
	public ResponseEntity<List<RelacionamentoDTO>>Seguindo(@RequestHeader("Authorization") String token ){
		List<RelacionamentoDTO> seguidores = relacionamentoService.ListarSeguindoUsuario(token);
        return ResponseEntity.ok(seguidores);
    }
	
	
	@PostMapping("/{idSeguido}")
	public ResponseEntity<SeguindoDTO>seguirUsuario(@RequestHeader("Authorization") String token, @PathVariable Long idSeguido) {
        SeguindoDTO seguindo = relacionamentoService.seguirUsuario(token, idSeguido);
        return ResponseEntity.ok(seguindo);
    }
	
	@DeleteMapping("/{idSeguido}")
	public String deixarSeguir(@RequestHeader("Authorization") String token, @PathVariable Long idSeguido) {
        relacionamentoService.deixarSeguir(token, idSeguido);
        String mensagem = "Voce deixou de seguir essa pessoa!";
        return mensagem;
	}
	
}

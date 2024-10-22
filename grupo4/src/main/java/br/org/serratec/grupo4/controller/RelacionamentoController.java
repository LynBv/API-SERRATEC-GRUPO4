package br.org.serratec.grupo4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.serratec.grupo4.dto.SeguindoDTO;
import br.org.serratec.grupo4.service.RelacionamentoService;

@RestController
@RequestMapping("/relacionamentos")
public class RelacionamentoController {

	@Autowired
	private RelacionamentoService relacionamentoService;
	
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

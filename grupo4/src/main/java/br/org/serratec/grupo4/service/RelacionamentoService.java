package br.org.serratec.grupo4.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.grupo4.domain.Relacionamento;
import br.org.serratec.grupo4.domain.Usuario;
import br.org.serratec.grupo4.domain.UsuarioRelacionamentoPK;
import br.org.serratec.grupo4.dto.SeguindoDTO;
import br.org.serratec.grupo4.exception.IdUsuarioInvalido;
import br.org.serratec.grupo4.exception.RelacionamentoException;
import br.org.serratec.grupo4.repository.RelacionamentoRepository;
import br.org.serratec.grupo4.repository.UsuarioRepository;
import br.org.serratec.grupo4.security.JwtUtil;

@Service
public class RelacionamentoService {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RelacionamentoRepository relacionamentoRepository;

    public SeguindoDTO seguirUsuario(String bearerToken, Long idSeguido) {
        Long id = jwtUtil.getId(bearerToken);
        Optional<Usuario> usuarioOPT = usuarioRepository.findById(id);
        if (usuarioOPT.isEmpty()) {
            throw new IdUsuarioInvalido("Seu Usuário não foi encontrado");
        }
        Optional<Usuario> seguidoOpt = usuarioRepository.findById(idSeguido);
        if (seguidoOpt.isEmpty()) {
            throw new IdUsuarioInvalido("Usuario que deseja seguir não foi encontrado");
        }
        Usuario seguido = seguidoOpt.get();
        Usuario usuario = usuarioOPT.get();
        if (usuario.getSeguidos().stream()
                .anyMatch(relacionamento -> relacionamento.getId().getSeguido().equals(seguido))) {
            throw new RelacionamentoException("Você já segue o usuário");
        }

        UsuarioRelacionamentoPK relacionamentoPK = new UsuarioRelacionamentoPK();
        relacionamentoPK.setSeguidor(usuario);
        relacionamentoPK.setSeguido(seguido);
        Relacionamento relacionamento = new Relacionamento();
        relacionamento.setId(relacionamentoPK);
        relacionamento.setDataInicioSeguimento(LocalDate.now());
        relacionamentoRepository.save(relacionamento);
        usuario.getSeguidos().add(relacionamento);
        SeguindoDTO seguindoDTO = new SeguindoDTO(relacionamento);

        return seguindoDTO ;

    }

    public void deixarSeguir(String bearerToken, Long idSeguido) {
        Long id = jwtUtil.getId(bearerToken);
        Optional<Usuario> usuarioOPT = usuarioRepository.findById(id);
        if (usuarioOPT.isEmpty()) {
            throw new IdUsuarioInvalido("Seu Usuário não foi encontrado");
        }
        Optional<Usuario> seguidoOpt = usuarioRepository.findById(idSeguido);
        if (seguidoOpt.isEmpty()) {
            throw new IdUsuarioInvalido("Usuario que deseja parar de seguir não foi encontrado");
        }
        Usuario seguido = seguidoOpt.get();
        Usuario usuario = usuarioOPT.get();
        Relacionamento relacionamento = usuario.getSeguidos().stream()
            .filter(rel -> rel.getId().getSeguido().equals(seguido))
            .findFirst()
            .orElseThrow(() -> new RelacionamentoException("Você não segue esse usuário"));

        relacionamentoRepository.delete(relacionamento);
        usuario.getSeguidos().remove(relacionamento);
        usuarioRepository.save(usuario);
        
        
        
       
    }
}

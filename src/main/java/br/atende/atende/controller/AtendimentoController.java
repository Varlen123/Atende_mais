package br.atende.atende.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.atende.atende.entity.Atendimento;
import br.atende.atende.service.AtendimentoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("atende/atendimento")
public class AtendimentoController {
    
    private final AtendimentoService atendimentoService;

    @PostMapping("/criaratendimentos")
    public ResponseEntity<?> criarAtendimento(
        @RequestParam int pacienteId,
        @RequestParam int medicoId,
        @RequestBody Atendimento atendimento) {
            try{
                Atendimento novoAtendimento = atendimentoService.criarAtendimento(pacienteId, medicoId, atendimento);
                return ResponseEntity.ok(novoAtendimento);
            } catch (RuntimeException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }

    @GetMapping("/listaratendimentos")
    public ResponseEntity<List<Atendimento>> listarAtendimentos() {
        List<Atendimento> atendimentos = atendimentoService.listarAtendimentos();
        return ResponseEntity.ok(atendimentos);
    }

    @GetMapping("listaratendimento/{id}")
    public ResponseEntity<Atendimento> listarAtendimentoPorId(@RequestParam int id) {
        Atendimento atendimento = atendimentoService.buscarAtendimentoPorId(id).get();
        return ResponseEntity.ok(atendimento);
    }

    
    @GetMapping("deletaratendimento/{id}")
    public ResponseEntity<String> deletarAtendimento(@RequestParam int id) {
        atendimentoService.deletar(id);
        return ResponseEntity.ok("Atendimento deletado com sucesso");
    }

/*************  ✨ Windsurf Command ⭐  *************/
/**
 * Handles HTTP GET requests to retrieve a list of atendimentos for a specific paciente.
 *
 * @param pacienteId the ID of the paciente whose atendimentos are to be retrieved
 * @return a ResponseEntity containing the list of atendimentos for the given paciente
 */

/*******  a1eda11c-d18f-4d02-9cd0-e449b3cff2c7  *******/
    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<Atendimento>> buscarPorPaciente(@PathVariable int pacienteId) {
        return ResponseEntity.ok(atendimentoService.buscarPorPaciente(pacienteId));
    }
    @GetMapping("/medico/{medicoId}")
    public ResponseEntity<List<Atendimento>> buscarPorMedico(@PathVariable int medicoId) {
        return ResponseEntity.ok(atendimentoService.buscarPorMedico(medicoId));
    }
}

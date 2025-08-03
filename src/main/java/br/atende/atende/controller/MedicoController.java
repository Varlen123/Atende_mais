package br.atende.atende.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.atende.atende.controller.request.MedicoRequest;
import br.atende.atende.controller.response.MedicoResponse;
import br.atende.atende.entity.Medico;
import br.atende.atende.mapper.MedicoMapper;
import br.atende.atende.service.MedicoService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("atende/medicos")
public class MedicoController {

    private final MedicoService medicoService;

    @PostMapping("/criarmedicos")
    public ResponseEntity<MedicoResponse> adicionarMedico(@RequestBody MedicoRequest medicoRequest) {
        Medico newMedico = MedicoMapper.toMedico(medicoRequest);
        Medico savedMedico = medicoService.save(newMedico);
        return ResponseEntity.status(HttpStatus.CREATED).body(MedicoMapper.toMedicoResponse(savedMedico));
    }

    @GetMapping("/listarmedicos")
    public ResponseEntity<List<MedicoResponse>> listarMedicos() {
        List<Medico> medicos = medicoService.listaMedicos();
        List<MedicoResponse> response = medicos.stream()
                .map(MedicoMapper::toMedicoResponse)
                .toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/listarmedicos/{id}")
    public ResponseEntity<MedicoResponse> listarMedicoPorId(@PathVariable int id) {
        return medicoService.procurarMedicosPorId(id)
                .map(medico -> ResponseEntity.ok(MedicoMapper.toMedicoResponse(medico)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/atualizarmedicos/{id}")
    public ResponseEntity<?> alterarMedicoPorId(@PathVariable int id, @RequestBody MedicoRequest medicoRequest) {
        Medico atualizado = medicoService.atualizar(id, MedicoMapper.toMedico(medicoRequest));
        if (atualizado != null) {
            return ResponseEntity.ok(MedicoMapper.toMedicoResponse(atualizado));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Médico com o id: " + id + " não existe nos nossos registros");
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarMedicoPorId(@PathVariable int id) {
        return medicoService.procurarMedicosPorId(id)
                .map(medico -> {
                    medicoService.delete(medico);
                    return ResponseEntity.ok("Médico com o ID " + id + " deletado com sucesso");
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Médico com o id " + id + " não encontrado"));
    }

    @GetMapping("/buscarporcrm/{crm}")
    public ResponseEntity<MedicoResponse> buscarPorCrm(@PathVariable String crm) {
        Medico medico = medicoService.buscarPorCrm(crm);
        return medico != null
                ? ResponseEntity.ok(MedicoMapper.toMedicoResponse(medico))
                : ResponseEntity.notFound().build();
    }

    @GetMapping("/buscarpornome/{nome}")
    public ResponseEntity<List<MedicoResponse>> buscarPorNome(@PathVariable String nome) {
        List<Medico> medicos = medicoService.buscarPorNome(nome);
        List<MedicoResponse> response = medicos.stream()
                .map(MedicoMapper::toMedicoResponse)
                .toList();
        return ResponseEntity.ok(response);
    }
}

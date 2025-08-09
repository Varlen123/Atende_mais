package br.atende.atende.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.atende.atende.controller.request.AtendimentoRequest;
import br.atende.atende.controller.response.AtendimentoResponse;
import br.atende.atende.entity.Atendimento;
import br.atende.atende.entity.Medico;
import br.atende.atende.entity.Paciente;
import br.atende.atende.service.AtendimentoService;
import br.atende.atende.service.MedicoService;
import br.atende.atende.service.PacienteService;
import br.atende.atende.mapper.AtendimentoMapper;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("atende/atendimento")
public class AtendimentoController {

    private final AtendimentoService atendimentoService;

    private final MedicoService medicoService;

    private final PacienteService pacienteService;

    @PostMapping("/criaratendimentos")
public ResponseEntity<?> criarAtendimento(@RequestBody AtendimentoRequest request) {
    try {
        Paciente paciente = pacienteService.procurarPacientePorId(request.pacienteId());
        Medico medico = medicoService.procurarMedicosPorId(request.medicoId())
                .orElseThrow(() -> new RuntimeException("Médico nao encontrado"));
        Atendimento atendimento = atendimentoService.criarAtendimento(request, paciente, medico);
        AtendimentoResponse response = AtendimentoMapper.toAtendimentoResponse(atendimento);
        return ResponseEntity.ok(response);
    } catch (RuntimeException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}



    @GetMapping("/listaratendimentos")
    public ResponseEntity<List<AtendimentoResponse>> listarAtendimentos() {
        List<Atendimento> atendimentos = atendimentoService.listarAtendimentos();
        List<AtendimentoResponse> response = atendimentos.stream()
                .map(AtendimentoMapper::toAtendimentoResponse)
                .toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/listaratendimento/{id}")
    public ResponseEntity<AtendimentoResponse> listarAtendimentoPorId(@PathVariable int id) {
        return atendimentoService.buscarAtendimentoPorId(id)
                .map(AtendimentoMapper::toAtendimentoResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/deletaratendimento/{id}")
    public ResponseEntity<String> deletarAtendimento(@PathVariable int id) {
        atendimentoService.deletar(id);
        return ResponseEntity.ok("Atendimento deletado com sucesso");
    }

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<AtendimentoResponse>> buscarPorPaciente(@PathVariable int pacienteId) {
        List<Atendimento> atendimentos = atendimentoService.buscarPorPaciente(pacienteId);
        List<AtendimentoResponse> response = atendimentos.stream()
                .map(AtendimentoMapper::toAtendimentoResponse)
                .toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/medico/{medicoId}")
    public ResponseEntity<List<AtendimentoResponse>> buscarPorMedico(@PathVariable int medicoId) {
        List<Atendimento> atendimentos = atendimentoService.buscarPorMedico(medicoId);
        List<AtendimentoResponse> response = atendimentos.stream()
                .map(AtendimentoMapper::toAtendimentoResponse)
                .toList();
        return ResponseEntity.ok(response);
    }
}

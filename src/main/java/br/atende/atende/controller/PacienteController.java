package br.atende.atende.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.atende.atende.controller.request.PacienteRequest;
import br.atende.atende.controller.response.PacienteResponse;
import br.atende.atende.entity.Paciente;
import br.atende.atende.mapper.PacienteMapper;
import br.atende.atende.service.PacienteService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("atende/paciente")    
public class PacienteController {

    private final PacienteService pacienteService;

    @PostMapping("/adicionarpacientes")
    public ResponseEntity<PacienteResponse> adicionarPaciente(@RequestBody PacienteRequest pacienteRequest){
        Paciente paciente = PacienteMapper.toPaciente(pacienteRequest);
        Paciente pacienteSalvo = pacienteService.adicionarPaciente(paciente);
        PacienteResponse response = PacienteMapper.toPacienteResponse(pacienteSalvo);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/listarpacientes")
    public ResponseEntity<List<PacienteResponse>> listarPacientes(){
        List<Paciente> pacientes = pacienteService.listarPacientes();
        List<PacienteResponse> responses = pacientes.stream()
            .map(PacienteMapper::toPacienteResponse)
            .toList();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/listarpaciente/{id}")
    public ResponseEntity<PacienteResponse> listarPacientePorId(@PathVariable int id){
        Paciente paciente = pacienteService.procurarPacientePorId(id);
        if (paciente != null) {
            return ResponseEntity.ok(PacienteMapper.toPacienteResponse(paciente));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/atualizarpaciente/{id}")
    public ResponseEntity<?> alterarPacientePorId(@PathVariable int id, @RequestBody PacienteRequest pacienteRequest) {
        Paciente pacienteAtualizado = PacienteMapper.toPaciente(pacienteRequest);
        Paciente paciente = pacienteService.update(id, pacienteAtualizado);
        if (paciente != null) {
            return ResponseEntity.ok(PacienteMapper.toPacienteResponse(paciente));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Paciente com o id: " + id + " nao existe nos nossos registros");
        }
    }

    @DeleteMapping("/deletarpaciente/{id}")
    public ResponseEntity<String> deletarPacientePorId(@PathVariable int id) {
        Paciente paciente = pacienteService.procurarPacientePorId(id);
        if (paciente != null) {
            pacienteService.delete(id);
            return ResponseEntity.ok("Paciente com o ID " + id + " deletado com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Paciente com o id " + id + " nao encontrado");
        }
    }
}

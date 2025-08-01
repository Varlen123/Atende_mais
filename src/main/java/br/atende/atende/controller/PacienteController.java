package br.atende.atende.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.atende.atende.entity.Paciente;
import br.atende.atende.service.PacienteService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("atende/paciente")    
public class PacienteController {
 
        private final PacienteService pacienteService;

        @PostMapping("/adicionarpacientes")
        public ResponseEntity<String> adicionarPaciente(@RequestBody Paciente paciente){
        Paciente pacienteSalvo = pacienteService.adicionarPaciente(paciente);
        return ResponseEntity.status(HttpStatus.CREATED)
        .body("Paciente adicionado com sucesso: " + pacienteSalvo.getNome());
    }

        @GetMapping("/listarpacientes")
        public ResponseEntity<List<Paciente>> listarPacientes(){
            List<Paciente> pacientes = pacienteService.listarPacientes();
            return ResponseEntity.ok(pacientes);
        }

        @GetMapping("/listarpaciente/{id}")
        public ResponseEntity<Paciente> listarPacientePorId(@PathVariable int id){
            Paciente paciente = pacienteService.procurarPacientePorId(id);
            if(paciente != null){
                return ResponseEntity.ok(paciente);
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(null);
            }
        }

        @PutMapping("/atualizarpaciente/{id}")
        public ResponseEntity<?> alterarPacientePorId(@PathVariable int id, @RequestBody Paciente atualizarPaciente) {
            Paciente paciente = pacienteService.update(id, atualizarPaciente);
            if (paciente != null) {
                return ResponseEntity.ok(paciente);
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

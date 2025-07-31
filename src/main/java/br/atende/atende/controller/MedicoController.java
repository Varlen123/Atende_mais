package br.atende.atende.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.atende.atende.entity.Medico;
import br.atende.atende.service.MedicoService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/medicos")
public class MedicoController {
    
    private final MedicoService medicoService;

    @PostMapping("/criarmedicos")
    public ResponseEntity<String> adicionarMedico(@RequestBody Medico medico){
        Medico medicoSalvo = medicoService.save(medico);
        return ResponseEntity.status(HttpStatus.CREATED).body("Médico adicionado com sucesso: " + medicoSalvo.getNome());
    }

    @GetMapping("/listarmedicos")
    public ResponseEntity<List<Medico>> listarMedicos(){
        List<Medico> medicos = medicoService.findall();
        return ResponseEntity.ok(medicos);
    }

    @GetMapping("/listarmedicos/{id}")
    public ResponseEntity<?> listarMedicoPorId(@PathVariable int id){
        Medico medico = medicoService.findById(id);
        if(medico != null){
            return ResponseEntity.ok(medico);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Médico com o id: " + id + " nao existe nos nossos registros");
        }
    }

    @PutMapping("/atualizarmedicos/{id}")
    public ResponseEntity<?> alterarMedicoPorId(@PathVariable int id, @RequestBody Medico atualizarMedico) {
        Medico medico = medicoService.update(id, atualizarMedico);
        if (medico != null) {
            return ResponseEntity.ok(medico);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Médico com o id: " + id + " nao existe nos nossos registros");
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarMedicoPorId(@PathVariable int id) {
        Medico medico = medicoService.findById(id);
        if (medico != null) {
            medicoService.delete(medico);
            return ResponseEntity.ok("Médico com o ID " + id + " deletado com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Médico com o id " + id + " nao encontrado");
        }
    }
}
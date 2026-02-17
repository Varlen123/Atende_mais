package br.atende.atende.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.atende.atende.entity.Paciente;
import br.atende.atende.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PacienteService {
    
    private final PacienteRepository pacienteRepository; 

    public Paciente adicionarPaciente(Paciente paciente){
        return pacienteRepository.save(paciente);
    }

    public List<Paciente> listarPacientes(){
        return pacienteRepository.findAll();
    }

    public Paciente procurarPacientePorId(int id){
        return pacienteRepository.findById(id).orElse(null);
    }

    // Atualiza paciente e também endereço (se fornecido)
    public Paciente update(int id, Paciente pacienteAtualizado) {
        return pacienteRepository.findById(id)
            .map(paciente -> {
                paciente.setNome(pacienteAtualizado.getNome());
                paciente.setCpf(pacienteAtualizado.getCpf());
                paciente.setTelefone(pacienteAtualizado.getTelefone());

                if (pacienteAtualizado.getEndereco() != null) {
                    paciente.setEndereco(pacienteAtualizado.getEndereco());
                }

                return pacienteRepository.save(paciente);
            }).orElse(null);
    }

    public boolean delete(int id) {
        if (pacienteRepository.existsById(id)) {
            pacienteRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<Paciente> findByCpf(String cpf) {
        List<Paciente> paciente = pacienteRepository.findByCpf(cpf);
        return paciente.stream()
            .findFirst();
    }

    public List<Paciente> findByNome(String nome) {
        return pacienteRepository.findByNomeContainingIgnoreCase(nome);
    }
}

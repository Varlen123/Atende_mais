package br.atende.atende.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.atende.atende.entity.Atendimento;
import br.atende.atende.entity.Medico;
import br.atende.atende.entity.Paciente;
import br.atende.atende.repository.AtendimentoRepository;
import br.atende.atende.repository.MedicoRepository;
import br.atende.atende.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AtendimentoService {
    
        private final AtendimentoRepository atendimentoRepository;
        private final MedicoRepository medicoRepository;
        private final PacienteRepository pacienteRepository;

    
        public Atendimento criarAtendimento (int pacienteId, int medicoId, Atendimento atendimento) {
            Optional<Paciente> pacienteOpt = pacienteRepository.findById(pacienteId);
            Optional<Medico> medicoOpt = medicoRepository.findById(medicoId);

            if(pacienteOpt.isPresent() && medicoOpt.isPresent()){
                atendimento.setPaciente(pacienteOpt.get());
                atendimento.setMedico(medicoOpt.get());
                return atendimentoRepository.save(atendimento);
            }
            else {
            throw new RuntimeException("Paciente ou Médico não encontrado");
        }
    }

        public List<Atendimento> listarAtendimentos(){
            return atendimentoRepository.findAll();
        }

        public Optional<Atendimento> buscarAtendimentoPorId(int id){
            return atendimentoRepository.findById(id);
        }

        public boolean deletar(int id) {
        if (atendimentoRepository.existsById(id)) {
            atendimentoRepository.deleteById(id);
            return true;
        }
        return false;
    }

        public List<Atendimento> buscarPorPaciente(int pacienteId) {
        return atendimentoRepository.findByPacienteId(pacienteId);
    }

        public List<Atendimento> buscarPorMedico(int medicoId) {
        return atendimentoRepository.findByMedicoId(medicoId);
    }
}

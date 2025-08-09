package br.atende.atende.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.atende.atende.controller.request.AtendimentoRequest;
import br.atende.atende.entity.Atendimento;
import br.atende.atende.entity.Medico;
import br.atende.atende.entity.Paciente;
import br.atende.atende.mapper.AtendimentoMapper;
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

    
        public Atendimento criarAtendimento(AtendimentoRequest request) {
    Paciente paciente = pacienteRepository.findById(request.pacienteId())
        .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

    Medico medico = medicoRepository.findById(request.medicoId())
        .orElseThrow(() -> new RuntimeException("Médico não encontrado"));

    Atendimento atendimento = Atendimento.builder()
        .data(request.data())
        .paciente(paciente)
        .medico(medico)
        .build();

    return atendimentoRepository.save(atendimento);
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

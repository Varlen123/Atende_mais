package br.atende.atende.service;

import br.atende.atende.controller.request.ConsultaRequest;
import br.atende.atende.entity.Consulta;
import br.atende.atende.entity.Medico;
import br.atende.atende.entity.Paciente;
import br.atende.atende.repository.ConsultaRepository;
import br.atende.atende.repository.MedicoRepository;
import br.atende.atende.repository.PacienteRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final PacienteRepository pacienteRepository;
    private final MedicoRepository medicoRepository;

    public ConsultaService(ConsultaRepository consultaRepository,
                           PacienteRepository pacienteRepository,
                           MedicoRepository medicoRepository) {
        this.consultaRepository = consultaRepository;
        this.pacienteRepository = pacienteRepository;
        this.medicoRepository = medicoRepository;
    }

    public Consulta marcarConsulta(ConsultaRequest request) {
        Optional<Paciente> pacienteOpt = pacienteRepository.findById(request.getPacienteId());
        Optional<Medico> medicoOpt = medicoRepository.findById(request.getMedicoId());

        if (pacienteOpt.isEmpty() || medicoOpt.isEmpty()) {
            throw new RuntimeException("Paciente ou Médico não encontrado");
        }

        Consulta consulta = new Consulta();
        consulta.setPaciente(pacienteOpt.get());
        consulta.setMedico(medicoOpt.get());
        consulta.setDataConsulta(request.getDataConsulta());
        consulta.setHorarioConsulta(request.getHorarioConsulta());
        consulta.setObservacoes(request.getObservacoes());

        return consultaRepository.save(consulta);
    }
}

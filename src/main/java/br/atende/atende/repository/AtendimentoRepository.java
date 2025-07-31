package br.atende.atende.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.atende.atende.entity.Atendimento;
import br.atende.atende.entity.Medico;
import br.atende.atende.entity.Paciente;

public interface AtendimentoRepository extends JpaRepository<Atendimento, Integer>{

        List<Atendimento> findByMedico(Medico medico);
        List<Atendimento> findByPaciente(Paciente paciente);
}
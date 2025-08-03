package br.atende.atende.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.atende.atende.entity.Atendimento;;

public interface AtendimentoRepository extends JpaRepository<Atendimento, Integer>{

        List<Atendimento> findByPacienteId(int pacienteId);
        List<Atendimento> findByMedicoId(int medicoId);

}
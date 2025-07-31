package br.atende.atende.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.atende.atende.entity.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Integer> {
    List<Paciente> findByCpf(String cpf);
    List<Paciente> findByNomeContainingIgnoreCase(String nome);
}

package br.atende.atende.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.atende.atende.entity.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Integer> {
      List<Medico> findByNomeContainingIgnoreCase(String nome);
      Optional<Medico> findByCrm(String crm);
}
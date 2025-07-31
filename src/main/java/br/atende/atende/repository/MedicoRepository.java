package br.atende.atende.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.atende.atende.entity.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Integer> {

}
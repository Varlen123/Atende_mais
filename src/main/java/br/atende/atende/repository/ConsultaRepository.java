package br.atende.atende.repository;

import br.atende.atende.entity.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ConsultaRepository extends JpaRepository<Consulta, Integer> {
    
}

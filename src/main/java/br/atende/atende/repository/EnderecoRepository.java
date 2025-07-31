package br.atende.atende.repository;

import br.atende.atende.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
    
    Endereco findByCep(String cep);
}

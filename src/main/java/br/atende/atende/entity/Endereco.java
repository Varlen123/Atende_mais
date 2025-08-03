package br.atende.atende.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;


@Builder
@Entity
@Data
@Table(name = "endereco")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String numero;

    private String bairro;

    @Column(length = 8, nullable = false)
    private String cep;

    @OneToOne(mappedBy = "endereco")
    private Paciente paciente;
}

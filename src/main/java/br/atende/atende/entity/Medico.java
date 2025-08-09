package br.atende.atende.entity;

import java.util.List;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Entity
@Getter
@Setter
@Table(name = "medico")

public class Medico{

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length =100, nullable = false)
    private String nome;

    @Column(length =10, nullable = false)
    private String crm;

    @Column(length = 100, nullable = false)
    private String especialidade;

    @OneToMany(mappedBy = "medico")
    private List<Atendimento> atendimentos;

    @OneToMany(mappedBy = "medico")
    private List<Consulta> consultas;

}
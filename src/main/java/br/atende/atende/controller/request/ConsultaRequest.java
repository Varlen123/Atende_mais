package br.atende.atende.controller.request;

import lombok.Data;

import java.util.Date;

@Data
public class ConsultaRequest {
    private Integer medicoId;
    private Integer pacienteId;
    private String observacoes;
    private Date dataConsulta;
    private String horarioConsulta;
}

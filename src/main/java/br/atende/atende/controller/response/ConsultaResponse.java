package br.atende.atende.controller.response;

import lombok.Builder;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ConsultaResponse {
    private Long id;
    private String medicoNome;
    private String pacienteNome;
    private String observacoes;
    private Date dataConsulta;
    private String horarioConsulta;
}
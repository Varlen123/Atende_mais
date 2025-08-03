package br.atende.atende.controller.request;

import lombok.Builder;

@Builder
public record PacienteRequest (
    String nome,
    String cpf
) {
    
}

package br.atende.atende.controller.request;

import lombok.Builder;

@Builder
public record MedicoRequest( String nome, String crm, String especialidade) {
    
}

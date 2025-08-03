package br.atende.atende.controller.response;

import lombok.Builder;

@Builder
public record MedicoResponse(int id, String nome, String crm, String especialidade) {
}

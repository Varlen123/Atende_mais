package br.atende.atende.controller.response;

import lombok.Builder;

@Builder
public record EnderecoResponse (
    int id,
    String numero,
    String bairro,
    String cep
) {
    }

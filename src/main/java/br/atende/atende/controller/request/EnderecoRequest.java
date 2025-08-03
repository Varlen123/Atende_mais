package br.atende.atende.controller.request;

import lombok.Builder;

@Builder

public record EnderecoRequest(
    String numero,
    String bairro,
    String cep
) {}


package br.atende.atende.controller.response;

import lombok.Builder;

@Builder
public record PacienteResponse(
    int id,
    String nome,
    String cpf,
    String telefone,
    EnderecoResponse endereco) {
    
}

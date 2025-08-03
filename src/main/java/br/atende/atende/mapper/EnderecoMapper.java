package br.atende.atende.mapper;

import br.atende.atende.controller.request.EnderecoRequest;
import br.atende.atende.controller.response.EnderecoResponse;
import br.atende.atende.entity.Endereco;
import lombok.experimental.UtilityClass;

@UtilityClass
public class EnderecoMapper {

    public static Endereco toEndereco(EnderecoRequest request) {
        return Endereco.builder()
                .numero(request.numero())
                .bairro(request.bairro())
                .cep(request.cep())
                .build();
    }

    public static EnderecoResponse toEnderecoResponse(Endereco endereco) {
        return EnderecoResponse.builder()
                .id(endereco.getId())
                .numero(endereco.getNumero())
                .bairro(endereco.getBairro())
                .cep(endereco.getCep())
                .build();
    }
}

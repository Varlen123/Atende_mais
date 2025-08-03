package br.atende.atende.mapper;

import br.atende.atende.controller.request.PacienteRequest;
import br.atende.atende.controller.response.PacienteResponse;
import br.atende.atende.entity.Paciente;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PacienteMapper {
    
    public static Paciente toPaciente(PacienteRequest pacienteRequest) {
        return Paciente.builder()
                .nome(pacienteRequest.nome())
                .cpf(pacienteRequest.cpf())
                .build();
    }

    public static PacienteResponse toPacienteResponse(Paciente paciente) {
        return PacienteResponse.
        builder()
                .id(paciente.getId())
                .nome(paciente.getNome())
                .cpf(paciente.getCpf())
                .telefone(paciente.getTelefone())
                .build();
    }
}

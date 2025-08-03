package br.atende.atende.mapper;

import br.atende.atende.controller.request.AtendimentoRequest;
import br.atende.atende.controller.response.AtendimentoResponse;

import br.atende.atende.entity.Atendimento;
import br.atende.atende.entity.Medico;
import br.atende.atende.entity.Paciente;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AtendimentoMapper {

    public Atendimento toAtendimento(AtendimentoRequest request, Paciente paciente, Medico medico) {
        return Atendimento.builder()
                .data(request.data())
                .paciente(paciente)
                .medico(medico)
                .build();
    }

    public AtendimentoResponse toAtendimentoResponse(Atendimento atendimento) {
        return AtendimentoResponse.builder()
                .id(atendimento.getId())
                .data(atendimento.getData())
                .pacienteId(atendimento.getPaciente().getId())
                .nomePaciente(atendimento.getPaciente().getNome())
                .medicoId(atendimento.getMedico().getId())
                .nomeMedico(atendimento.getMedico().getNome())
                .build();
    }
}

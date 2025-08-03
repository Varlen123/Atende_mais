package br.atende.atende.mapper;

import br.atende.atende.controller.request.MedicoRequest;
import br.atende.atende.controller.response.MedicoResponse;
import br.atende.atende.entity.Medico;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MedicoMapper {
    public static Medico toMedico(MedicoRequest medicoRequest) {
        return Medico.builder()
                .nome(medicoRequest.nome())
                .crm(medicoRequest.crm())
                .especialidade(medicoRequest.especialidade())
                .build();
    }

    public static MedicoResponse toMedicoResponse(Medico medico) {
        return MedicoResponse.builder()
                .id(medico.getId())
                .nome(medico.getNome())
                .crm(medico.getCrm())
                .especialidade(medico.getEspecialidade())
                .build();
    }
}

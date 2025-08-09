package br.atende.atende.mapper;

import br.atende.atende.controller.request.ConsultaRequest;
import br.atende.atende.controller.response.ConsultaResponse;
import br.atende.atende.entity.Consulta;

public class ConsultaMapper {


    public static ConsultaResponse toConsultaResponse(Consulta consulta) {
        return ConsultaResponse.builder()
                .id(consulta.getId())
                .medicoNome(consulta.getMedico().getNome())
                .pacienteNome(consulta.getPaciente().getNome())
                .dataConsulta(consulta.getDataConsulta())
                .horarioConsulta(consulta.getHorarioConsulta())
                .observacoes(consulta.getObservacoes())
                .build();
    }

}
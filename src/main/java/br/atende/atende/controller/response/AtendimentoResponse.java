package br.atende.atende.controller.response;

import java.time.LocalDateTime;
import lombok.Builder;

@Builder

public record AtendimentoResponse(
    int id,
    LocalDateTime data,
    int pacienteId,
    String nomePaciente,
    int medicoId,
    String nomeMedico
) {}

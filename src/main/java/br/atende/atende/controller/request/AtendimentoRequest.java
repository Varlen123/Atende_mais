package br.atende.atende.controller.request;

import java.time.LocalDateTime;
import lombok.Builder;

@Builder

public record AtendimentoRequest(
    LocalDateTime data,
    int pacienteId,
    int medicoId
) {}

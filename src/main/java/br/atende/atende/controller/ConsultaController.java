package br.atende.atende.controller;

import br.atende.atende.controller.request.ConsultaRequest;
import br.atende.atende.controller.response.ConsultaResponse;
import br.atende.atende.entity.Consulta;
import br.atende.atende.mapper.ConsultaMapper;
import br.atende.atende.service.ConsultaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    private final ConsultaService consultaService;

    public ConsultaController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    @PostMapping("/marcar")
    public ResponseEntity<ConsultaResponse> marcarConsulta(@RequestBody ConsultaRequest request) {
        Consulta consulta = consultaService.marcarConsulta(request);
        return ResponseEntity.ok(ConsultaMapper.toConsultaResponse(consulta));
    }
}

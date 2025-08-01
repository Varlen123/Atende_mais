package br.atende.atende.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.atende.atende.entity.Atendimento;
import br.atende.atende.entity.Medico;
import br.atende.atende.repository.AtendimentoRepository;
import br.atende.atende.repository.MedicoRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Data

public class MedicoService {
    
    private final MedicoRepository medicoRepository;

    private final AtendimentoRepository atendimentoRepository;

    public List<Medico> listaMedicos(){
        List<Medico> medicos = medicoRepository.findAll();
        return medicos;
    }

    public Medico procurarMedicosPorId(int id){
        return medicoRepository.findById(id).orElse(null);
    }

    public Medico save(Medico medicoAdicionar){
        return medicoRepository.save(medicoAdicionar);
    }

     public Medico atualizar(int id, Medico medicoAtualizado) {
        Medico medico = procurarMedicosPorId(id);
        medico.setNome(medicoAtualizado.getNome());
        medico.setCrm(medicoAtualizado.getCrm());
        return medicoRepository.save(medico);
    }

    public void delete(Medico medicoDelete){
        medicoRepository.delete(medicoDelete);
    }

     public List<Medico> buscarPorNome(String nome) {
        return medicoRepository.findByNomeContainingIgnoreCase(nome);
    }

    public Medico buscarPorCrm(String crm) {
        return medicoRepository.findByCrm(crm)
                .orElseThrow(() -> new RuntimeException("CRM não encontrado"));
    }

    public List<Atendimento> listarAtendimentosDoMedico(int medicoId) {
    Medico medico = procurarMedicosPorId(medicoId);
    return atendimentoRepository.findByMedicoId(medico.getId());
    }
    
}

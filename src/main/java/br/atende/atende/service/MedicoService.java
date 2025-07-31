package br.atende.atende.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.atende.atende.entity.Medico;
import br.atende.atende.repository.MedicoRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Data

public class MedicoService {
    
    private final MedicoRepository medicoRepository;

    public List<Medico> findall(){
        List<Medico> medicos = medicoRepository.findAll();
        return medicos;
    }

    public Medico findById(int id){
        return medicoRepository.findById(id).orElse(null);
    }

    public Medico save(Medico medicoAdicionar){
        return medicoRepository.save(medicoAdicionar);
    }

    public Medico update(Medico medicoUpdate){
        return medicoRepository.save(medicoUpdate);
    }

    public void delete(Medico medicoDelete){
        medicoRepository.delete(medicoDelete);
    }
}

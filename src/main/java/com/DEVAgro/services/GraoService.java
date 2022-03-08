package com.DEVAgro.services;

import com.DEVAgro.models.Grao;
import com.DEVAgro.repositories.GraoRepository;
import com.DEVAgro.services.exceptions.GraoNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GraoService {

    @Autowired
    private GraoRepository graoRepository;

    public List<Grao> listar(){
        return graoRepository.findAll();
    }

    public Grao salvar(Grao grao) {
        grao.setId(null);
        return graoRepository.save(grao);
    }

    public Grao buscar(Long id) {
        Grao grao = graoRepository.findById(id).orElse(null);

        if (grao == null) {
            throw new GraoNaoEncontradoException("Grao nao encontrado.");
        }
        return grao;
    }

    public void atualizar(Grao grao) {
        verificaExistencia(grao);
        graoRepository.save(grao);
    }

    public void deletar(Long id){
        try {
            graoRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new GraoNaoEncontradoException("Grao nao encontrado.");
        }
    }
    public void verificaExistencia(Grao grao) {
        buscar(grao.getId());
    }
}

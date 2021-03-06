package com.DEVAgro.services;

import com.DEVAgro.models.Fazenda;
import com.DEVAgro.repositories.FazendaRepository;
import com.DEVAgro.services.dto.FazendaDto;
import com.DEVAgro.services.exceptions.FazendaNaoEncontradaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service
public class FazendaService {

    @Autowired
    FazendaRepository fazendaRepository;

    public List<Fazenda> listar() {
        return fazendaRepository.findAll();
    }

    public Fazenda salvar(FazendaDto fazendaDto) throws ParseException {
//        if (fazenda.getId() != null) {
//            Fazenda f = fazendaRepository.getById(fazenda.getId());
//
//            if(f != null) {
//                throw new FazendaNaoEncontradaException("Fazenda não encontrada.");
//            }
//        }
        return fazendaRepository.save(fazendaDto.converter());
    }

    public Fazenda buscar(Long id) {
        Fazenda fazenda = fazendaRepository.findById(id).orElse(null);

        if (fazenda == null) {
            throw new FazendaNaoEncontradaException("Fazenda não encontrada.");
        }
        return fazenda;
    }

    public void atualizar(Fazenda fazenda) {
        verificaExistencia(fazenda);
        fazendaRepository.save(fazenda);
    }

    public void deletar(Long id) {
        try {
            fazendaRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new FazendaNaoEncontradaException("Fazenda não encontrada");
        }
    }

    public void aumentaEstoque(Fazenda fazenda, Double valor) {
        verificaExistencia(fazenda);
        fazenda.setValor(valor);
        fazenda.setQuantidadeEstoque(fazenda.aumentaEstoque(valor));
        fazendaRepository.save(fazenda);


    }

    public void diminuiEstoque(Fazenda fazenda, Double valor) {
        verificaExistencia(fazenda);
        fazenda.setValor(valor);
        fazenda.setQuantidadeEstoque(fazenda.diminuiEstoque(valor));
        fazendaRepository.save(fazenda);


    }

//    public List<Fazenda> mostra(){
//        return fazendaRepository.findByFazenda();
//    }

    public void verificaExistencia(Fazenda fazenda) {
        buscar(fazenda.getId());
    }
}

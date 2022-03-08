package com.DEVAgro.services;


import com.DEVAgro.models.Funcionario;
import com.DEVAgro.repositories.FuncionarioRepository;
import com.DEVAgro.services.exceptions.FuncionarioExistenteException;
import com.DEVAgro.services.exceptions.FuncionarioNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public List<Funcionario> listar (){
        return funcionarioRepository.findAll();
    }

    public Funcionario salvar(Funcionario funcionario) {
        if (funcionario.getId() != null) {
            Optional<Funcionario> fun = funcionarioRepository.findById(funcionario.getId());

            if (fun != null) {
                throw new FuncionarioExistenteException("Funcionario ja cadastrado.");
             }
        }
        return funcionarioRepository.save(funcionario);
    }

    public Funcionario buscar(Long id) {
        Funcionario funcionario = funcionarioRepository.findById(id).orElse(null);
        if (funcionario == null) {
            throw new FuncionarioExistenteException("Funcionário não encontrado");
        }
        return funcionario;
    }

    public void atualizar(Funcionario funcionario) {
        verificaExistencia(funcionario);
        funcionarioRepository.save(funcionario);
    }

    public void deletar(Long id) {
        try {
            funcionarioRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new FuncionarioNaoEncontradoException("Funcionário não existe.");
        }
    }
    public void verificaExistencia(Funcionario funcionario) {
        buscar(funcionario.getId());
    }
}

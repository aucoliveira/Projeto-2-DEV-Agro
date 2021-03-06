package com.DEVAgro.services;

import com.DEVAgro.models.Empresa;
import com.DEVAgro.models.Fazenda;
import com.DEVAgro.models.Funcionario;
import com.DEVAgro.models.Grao;
import com.DEVAgro.repositories.EmpresaRepository;
import com.DEVAgro.repositories.FazendaRepository;
import com.DEVAgro.repositories.FuncionarioRepository;
import com.DEVAgro.repositories.GraoRepository;
import com.DEVAgro.services.dto.GraoSummaryDto;
import com.DEVAgro.services.exceptions.EmpresaNaoEncontradaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaQuery;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private FazendaRepository fazendaRepository;

    @Autowired
    private GraoRepository graoRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    // CRUD da Empresa
    public List<Empresa> listar() {
        return empresaRepository.findAll();
    }

    public Empresa buscar(Long id) {
        Empresa empresa = empresaRepository.findById(id).orElse(null);
        if (empresa == null) {
            throw new EmpresaNaoEncontradaException("Essa empresa não está registrada");
        }
        return empresa;
    }

    public Empresa salvar(Empresa empresa) {
        empresa.setId(null);
        return empresaRepository.save(empresa);
    }

    public void deletar(Long id) {
        try {
            empresaRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EmpresaNaoEncontradaException("Essa empresa não está cadastrada");
        }
    }

    public void atualizar(Empresa empresa) {
        verificaExistencia(empresa);
        empresaRepository.save(empresa);
    }

    private void verificaExistencia(Empresa empresa) {
        buscar(empresa.getId());
    }

    // Fim do CRUD

    // Um endpoint que retorna a lista de fazendas de uma empresa.
    public List<Fazenda> listarFazenda(Long empresaId) {
        Empresa empresa = buscar(empresaId);

        return empresa.getFazenda();
    }

    // Um endpoint que retorna a quantidade de fazendas de uma empresa.
    public Integer qtdeFazendasEmpresa(Long id) {
        Empresa empresa = empresaRepository.findById(id).orElse(null);
        if (empresa == null) {
            throw new EmpresaNaoEncontradaException("Empresa não encontrada");
        }
        return empresa.getFazenda().size();

    }

    public List<Grao> listarGrao(Long empresaId) {
            Empresa empresa = buscar(empresaId);

            return empresa.getGrao();
    }

    public List<Funcionario> listarFuncionario(Long empresaId) {
        Empresa empresa = buscar(empresaId);

        return empresa.getFuncionario();
    }

    public Integer qtdeFuncionarioEmpresa(Long id) {
        Empresa empresa = empresaRepository.findById(id).orElse(null);
        if (empresa == null) {
            throw new EmpresaNaoEncontradaException("Empresa não encontrada");
        }
        return empresa.getFuncionario().size();

    }

}


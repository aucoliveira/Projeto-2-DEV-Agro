package com.DEVAgro.services;

import com.DEVAgro.models.Empresa;
import com.DEVAgro.models.Fazenda;
import com.DEVAgro.repositories.EmpresaRepository;
import com.DEVAgro.repositories.FazendaRepository;
import com.DEVAgro.repositories.FuncionarioRepository;
import com.DEVAgro.repositories.GraoRepository;
import com.DEVAgro.services.exceptions.EmpresaNaoEncontradaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

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

    public void atulizar(Empresa empresa) {
        verificaExistencia(empresa);
        empresaRepository.save(empresa);
    }

    private void verificaExistencia(Empresa empresa) {
        buscar(empresa.getId());
    }

    // Fim do CRUD

    // Um endpoint que retorna a lista de fazendas de uma empresa.
    public Empresa ListaFazendasEmpresa(Long id) {
        Empresa empresa = empresaRepository.findById(id).orElse(null);
        if (empresa == null) {
            throw new EmpresaNaoEncontradaException("Empresa não encontrada");
        }
        return empresa;

    }

    // Um endpoint que retorna a quantidade de fazendas de uma empresa.
    public Integer qtdeFazendasEmpresa(Long id) {
        Empresa empresa = empresaRepository.findById(id).orElse(null);
        if (empresa == null) {
            throw new EmpresaNaoEncontradaException("Empresa não encontrada");
        }
        return empresa.getFazenda().size();

    }
    /*
        Um endpoint que retorna uma lista de fazendas de uma empresa, onde cada elemento da lista
        deve ter 3 atributos: ID da fazenda, nome da fazenda e a data prevista da próxima colheita
        (considerando a data da última colheita e o tempo médio de colheita do grão associado a essa fazenda).
    */
    public Empresa ListaFazendasEmpresaInformacoes(Long id) {
        Empresa empresa = empresaRepository.findById(id).orElse(null);
        if (empresa == null) {
            throw new EmpresaNaoEncontradaException("Empresa não encontrada");
        }
        return empresa;

    }

    // Adicionando uma fazenda
    public Fazenda adicionarFazenda(Long empresaId, Fazenda fazenda) {
        Empresa empresa = buscar(empresaId);
        fazenda.setEmpresa(empresa);

        return fazendaRepository.save(fazenda);
    }

}
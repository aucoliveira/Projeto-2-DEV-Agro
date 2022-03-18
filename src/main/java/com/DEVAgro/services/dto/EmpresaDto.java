package com.DEVAgro.services.dto;

import com.DEVAgro.models.Empresa;
import com.DEVAgro.models.Fazenda;
import com.DEVAgro.models.Funcionario;
import com.DEVAgro.models.Grao;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EmpresaDto {

    private String nome;
    private String cnpj;
    private String endereco;
    private List<Fazenda> fazenda;
    private List<Funcionario> funcionario;
    private List<Grao> grao;

    public Empresa converter(){
        Empresa empresa = new Empresa();

        empresa.setNome(nome);
        empresa.setCnpj(cnpj);
        empresa.setEndereco(endereco);
        empresa.setFazenda(fazenda);
        empresa.setFuncionario(funcionario);
        empresa.setGrao(grao);

        return empresa;
    }
}

package com.DEVAgro.services.dto;

import com.DEVAgro.models.Empresa;
import com.DEVAgro.models.Funcionario;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FuncionarioDto {


    private String nome;
    private String sobrenome;
    private String cpf;
    private String endereco;
    private String telefone;
    private String sexo;
    private String dataNascimento;
    private String dataContratacao;
    private Empresa empresa;

    public Funcionario converter() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Funcionario funcionario =  new Funcionario();
        funcionario.setNome(nome);
        funcionario.setSobrenome(sobrenome);
        funcionario.setEndereco(endereco);
        funcionario.setTelefone(telefone);
        funcionario.setSexo(sexo);
        funcionario.setDataNascimento(formatter.parse(dataNascimento));
        funcionario.setDataContratacao(formatter.parse(dataContratacao));
        funcionario.setEmpresa(empresa);

        return funcionario;
    }
}

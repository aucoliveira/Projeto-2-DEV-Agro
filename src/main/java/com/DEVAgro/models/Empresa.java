package com.DEVAgro.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@Entity
public class Empresa {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank(message = "O nome da empresa é obrigatória.")
    private String nome;

    @NotBlank(message = "CNPJ é obrigatório")
    @CNPJ(message = "CNPJ inválido.")
    private String cnpj;

    @NotBlank(message = "O endereço é obrigatório.")
    private String endereco;

    @OneToMany(mappedBy = "empresa")
    private List<Fazenda> fazenda;

    @OneToMany(mappedBy = "empresa")
    private List<Funcionario> funcionario;

    @OneToMany(mappedBy = "empresa")
    private List<Grao> grao;

    public Empresa(){}

    public Empresa(String nome){
        this.nome = nome;
    }



}

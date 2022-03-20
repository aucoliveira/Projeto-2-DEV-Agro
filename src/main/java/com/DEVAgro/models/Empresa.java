package com.DEVAgro.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
//@Table(name = "empresa")
public  class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   @NotBlank(message = "O nome da empresa é obrigatória.")
    private String nome;

   @NotBlank(message = "CNPJ é obrigatório")
    @CNPJ(message = "CNPJ inválido.")
    private String cnpj;

   @NotBlank(message = "O endereço é obrigatório.")
    private String endereco;

    @OneToMany(mappedBy = "empresa")
    @JsonIgnore
    private List<Fazenda> fazenda;

    @OneToMany(mappedBy = "empresa")
    @JsonIgnore
    private List<Funcionario> funcionario;

    @OneToMany(mappedBy = "empresa")
    @JsonIgnore
    private List<Grao> grao;

    public Empresa(){}

    public Empresa(String nome){
        this.nome = nome;
    }



}

package com.DEVAgro.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Setter
@Getter
@Entity
public class Fazenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@NotBlank(message = "O nome da fazenda é obrigatório.")
    private String nome;

    //@NotBlank(message = "O endereço é obrigatório.")
    private String endereco;

    //@NotBlank(message = "A quantidade de estoque é obrigatória.")
    private Double quantidadeEstoque;

    //@NotBlank(message = "O tipo de grão é obrigatório.")
    @OneToOne(mappedBy = "empresa")
    @JsonInclude
    @Transient
    private Grao grao;

    //@NotBlank(message = "Esse campo é obrigatório.")
    @ManyToOne
    @JoinColumn(name = "EMPRESA_ID")
    @JsonInclude
    private Empresa empresa;

    //@NotBlank(message = "Esse campo é Obrigatório.")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date ultimaColheita;

    private LocalDate proximaColheita ;


    private Double valor;
    public Double aumentaEstoque(Double valor) {

        return this.quantidadeEstoque += valor;
    }

    public Double diminuiEstoque(Double valor) {

        return this.quantidadeEstoque -= valor;
    }

}

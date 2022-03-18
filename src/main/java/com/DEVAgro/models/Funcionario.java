package com.DEVAgro.models;

import com.DEVAgro.models.enums.Sexo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "funcionario")
public class Funcionario {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank(message = "Campo obrigatório.")
    private String nome;

    @NotBlank(message = "Campo obrigatório.")
    private String sobrenome;

    @NotBlank(message = "Campo obrigatório.")
    @CPF(message = "CPF inválido.")
    private String cpf;

    @NotBlank(message = "Campo obrigatório.")
    private String endereco;

    @NotBlank(message = "Campo obrigatório.")
    @Pattern(regexp = "(\\d{2}) \\d{4}-\\d{4}")
    private String telefone;

    @NotBlank(message = "Campo obrigatório.")
    private Sexo sexo;

    @NotBlank(message = "Campo obrigatório.")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataNascimento;

    @NotBlank(message = "Campo obrigatório.")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataContratacao;

    @NotBlank(message = "Campo obrigatório.")
    @ManyToOne(fetch = FetchType.LAZY) // Relação de vários funcionarios para uma empresa
    @JoinColumn(name = "EMPRESA_ID")
    @JsonIgnore
    private Empresa empresa;

}

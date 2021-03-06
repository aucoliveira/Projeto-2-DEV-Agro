package com.DEVAgro.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
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
//@Table(name = "funcionario")
public class Funcionario {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Campo obrigatório.")
    private String nome;

    @NotBlank(message = "Campo obrigatório.")
    private String sobrenome;

    @NotBlank(message = "Campo obrigatório.")
    @CPF(message = "CPF inválido.")
    @Pattern(regexp = "[0-9]{3}\\.?[0-9]{3}\\.?[0-9]{3}\\-?[0-9]{2}")
    private String cpf;

    @NotBlank(message = "Campo obrigatório.")
    private String endereco;

    @NotBlank(message = "Campo obrigatório.")
   @Pattern(regexp = "\\(\\d{2}\\) \\d{5}-\\d{4}")
    //@JsonFormat(pattern = "(\\d{2}) \\d{4}-\\d{4}")
    private String telefone;

    @NotBlank(message = "Campo obrigatório.")
    private String sexo;  //Cheguei a criar um enum para sexo, mas estava dando problema quando eu passava

    //@NotBlank(message = "Campo obrigatório.")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataNascimento;

    //@NotBlank(message = "Campo obrigatório.")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataContratacao;

   //@NotBlank(message = "Campo obrigatório.")
   @ManyToOne
   @JoinColumn(name = "EMPRESA_ID")
   @JsonInclude
   private Empresa empresa;
}

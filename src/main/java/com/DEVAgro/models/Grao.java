package com.DEVAgro.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "grao")
public class Grao {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank(message = " Campo obrigatório.")
    private String nome;

    //@NotBlank(message = "Campo obrigatório.")
    private Integer tempoMedioDeColheita; // esse recampo tem que ser em dias


    @ManyToOne
    @JoinColumn(name = "EMPRESA_ID")
    @JsonInclude
    private Empresa empresa;

//    //@NotBlank(message = "Campo obrigatório.")
//    @OneToOne
//    @JsonIgnore
//    @JoinColumn(name = "FAZENDA_ID")
//    private Fazenda fazenda;


}

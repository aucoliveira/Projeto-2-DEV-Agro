package com.DEVAgro.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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

    @NotBlank(message = "Campo obrigatório.")
    private int tempoMedioDeColheita; // esse recampo tem que ser em dias

    @ManyToOne(fetch = FetchType.LAZY) // vários graos para uma empresa
    @JoinColumn(name = "EMPRESA_ID")
    @JsonIgnore
    private Empresa empresa;

    @NotBlank(message = "Campo obrigatório.")
    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "FAZENDA_ID")
    private Fazenda fazenda;


}

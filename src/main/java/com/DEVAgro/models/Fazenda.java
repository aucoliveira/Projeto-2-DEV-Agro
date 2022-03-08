package com.DEVAgro.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static javax.persistence.FetchType.LAZY;

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
    private int quantidadeEstoque;

    //@NotBlank(message = "O tipo de grão é obrigatório.")
    @OneToOne(mappedBy ="fazenda") // Relação de uma fazenda para grao.
                                // obs: Verificar na aula tira dúvida se não é OneToOne
    @JsonInclude
    private Grao grao;

    //@NotBlank(message = "Esse campo é obrigatório.")
    @OneToMany
    @JoinColumn(name = "EMPRESA_ID")
    @JsonInclude
    private Empresa empresa;

    //@NotBlank(message = "Esse campo é Obrigatório.")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date ultimaColheita;


    public Calendar proximaColheita(){
        Calendar proximaColheita = new GregorianCalendar();
        proximaColheita.setTime(ultimaColheita);
        proximaColheita.add(Calendar.DAY_OF_MONTH,grao.getTempoMedioDeColheita());
        return proximaColheita;
    }


}

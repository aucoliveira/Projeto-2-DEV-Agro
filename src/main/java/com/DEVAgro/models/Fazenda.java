package com.DEVAgro.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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
    private Double quantidadeEstoque = Double.valueOf(0);

    //@NotBlank(message = "O tipo de grão é obrigatório.")
    @OneToOne
    @JsonInclude
    private Grao grao;

    //@NotBlank(message = "Esse campo é obrigatório.")
    @ManyToOne
    @JoinColumn(name = "EMPRESA_ID")
    @JsonInclude
    private Empresa empresa;

    //@NotBlank(message = "Esse campo é Obrigatório.")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date ultimaColheita;

    private Date proximaColheita ;

    public void setProximaColheita(Date proximaColheita) {

        this.proximaColheita = proximaColheitaCalcula(grao).getTime();
    }

    private Double estoque;
    public void setQuantidadeEstoque(Double estoque){

        this.quantidadeEstoque += estoque;
    }

    public Calendar proximaColheitaCalcula(Grao grao){
        Calendar proximaColheita = new GregorianCalendar();
        proximaColheita.setTime(ultimaColheita);
        System.out.println(grao.getTempoMedioDeColheita());
        proximaColheita.add(Calendar.DAY_OF_MONTH,grao.getTempoMedioDeColheita());
        return proximaColheita;
    }


}

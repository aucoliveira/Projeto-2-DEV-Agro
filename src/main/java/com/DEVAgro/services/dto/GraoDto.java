package com.DEVAgro.services.dto;

import com.DEVAgro.models.Empresa;
import com.DEVAgro.models.Fazenda;
import com.DEVAgro.models.Grao;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GraoDto {

    private Long id;

    private String nome;

    private Integer tempoMedioDeColheita; // esse recampo tem que ser em dias

    private Empresa empresa;

   // private Fazenda fazenda;

    public GraoDto(){}

    public GraoDto(Long id, String nome,Integer tempoMedioDeColheita, Empresa empresa) {
        this.id= id;
        this.nome = nome;
        this.tempoMedioDeColheita = tempoMedioDeColheita;
        this.empresa = empresa;

    }

    public Grao converter(){
        Grao grao = new Grao();
       //grao.setId(id);
        grao.setNome(nome);
        grao.setEmpresa(empresa);
        grao.setTempoMedioDeColheita(tempoMedioDeColheita);
        //grao.setFazenda(fazenda);

        return grao;
    }

}

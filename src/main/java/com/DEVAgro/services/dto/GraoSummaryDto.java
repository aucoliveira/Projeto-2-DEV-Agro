package com.DEVAgro.services.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class GraoSummaryDto {

    private String nome;
    private Double qtdeEstoque;

    public String toString(){
        return this.nome + this.qtdeEstoque;
    }

}

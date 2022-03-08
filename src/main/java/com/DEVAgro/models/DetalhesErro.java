package com.DEVAgro.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DetalhesErro {

    private String titulo;

    private Long status;

    private Long timestamp;

    private String mensagemDesenvolvedor;
}

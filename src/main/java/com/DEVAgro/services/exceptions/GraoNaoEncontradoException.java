package com.DEVAgro.services.exceptions;

public class GraoNaoEncontradoException extends RuntimeException {

    private static final long serialVersionUID = 1869300553614629710L;

    public GraoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public GraoNaoEncontradoException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}

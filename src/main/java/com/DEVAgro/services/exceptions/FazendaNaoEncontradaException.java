package com.DEVAgro.services.exceptions;

public class FazendaNaoEncontradaException extends RuntimeException {

    private static final long serialVersionUID = 1869300553614629710L;

    public FazendaNaoEncontradaException(String mensagem) {
        super(mensagem);
    }

    public FazendaNaoEncontradaException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}

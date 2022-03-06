package com.DEVAgro.services.exceptions;

public class EmpresaNaoEncontradaException extends RuntimeException {

    private static final long serialVersionUID = 1869300553614629710L;

    public EmpresaNaoEncontradaException(String mensagem) {
        super(mensagem);
    }

    public EmpresaNaoEncontradaException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}

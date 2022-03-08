package com.DEVAgro.services.exceptions;

public class FuncionarioNaoEncontradoException extends RuntimeException {

    private static final long serialVersionUID = 1869300553614629710L;

    public FuncionarioNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public FuncionarioNaoEncontradoException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}

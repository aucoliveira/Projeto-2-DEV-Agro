package com.DEVAgro.services.exceptions;

public class FuncionarioExistenteException extends RuntimeException {

    private static final long serialVersionUID = 1869300553614629710L;
    public FuncionarioExistenteException(String mensagem) {
        super(mensagem);
    }

    public FuncionarioExistenteException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}

package com.DEVAgro.services.exceptions;

public class GraoExistenteException extends RuntimeException {

    private static final long serialVersionUID = 1869300553614629710L;
    public GraoExistenteException(String mensagem) {
        super(mensagem);
    }

    public GraoExistenteException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}

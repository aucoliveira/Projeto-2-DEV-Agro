package com.DEVAgro.services.exceptions;

public class EmpresaExistenteException extends RuntimeException {

    private static final long serialVersionUID = 1869300553614629710L;
    public EmpresaExistenteException(String mensagem) {
        super(mensagem);
    }

    public EmpresaExistenteException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}

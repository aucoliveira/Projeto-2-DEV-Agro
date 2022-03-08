package com.DEVAgro.handler;

import com.DEVAgro.models.DetalhesErro;
import com.DEVAgro.services.exceptions.EmpresaExistenteException;
import com.DEVAgro.services.exceptions.EmpresaNaoEncontradaException;
import com.DEVAgro.services.exceptions.FazendaNaoEncontradaException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(FazendaNaoEncontradaException.class)
    public ResponseEntity<DetalhesErro> handlerEmpresaNaoEncontradaException
            (FazendaNaoEncontradaException e, HttpServletRequest request) {

        DetalhesErro erro = new DetalhesErro();
        erro.setStatus(404l);
        erro.setTitulo("Produto não encontrado");
        erro.setMensagemDesenvolvedor("http://erros.dev-agro.com/404");
        erro.setTimestamp(System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(EmpresaExistenteException.class)
    public ResponseEntity<DetalhesErro> handlerEmpresaExistenteException
            (EmpresaExistenteException e, HttpServletRequest request) {

        DetalhesErro erro = new DetalhesErro();
        erro.setStatus(404l);
        erro.setTitulo("Fazenda já existe");
        erro.setMensagemDesenvolvedor("http://erros.dev-agro.com/409");
        erro.setTimestamp(System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
    }


    @ExceptionHandler(EmpresaNaoEncontradaException.class)
    public ResponseEntity<DetalhesErro> handlerAutorNaoEncontradoException
            (FazendaNaoEncontradaException e, HttpServletRequest request) {

        DetalhesErro erro = new DetalhesErro();
        erro.setStatus(404l);
        erro.setTitulo("Fazenda não encontrado.");
        erro.setMensagemDesenvolvedor("http://erros.dev-agro.com/409");
        erro.setTimestamp(System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<DetalhesErro> handlerDataIntegrityViolationException
            (DataIntegrityViolationException e, HttpServletRequest request) {

        DetalhesErro erro = new DetalhesErro();
        erro.setStatus(400l);
        erro.setTitulo("Requisição invalida.");
        erro.setMensagemDesenvolvedor("http://erros.dev-agro.com/400");
        erro.setTimestamp(System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

}

package com.DEVAgro.services.dto;

import com.DEVAgro.models.Empresa;
import com.DEVAgro.models.Fazenda;
import com.DEVAgro.models.Grao;
import lombok.Getter;
import lombok.Setter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Setter
@Getter
public class FazendaDto {

    private Long id;
    private String nome;
    private String endereco;
    private Double quantidadeEstoque;
    private Grao grao;
    private Empresa empresa;
    private String ultimaColheita;
    private LocalDate proximaColheita;


    public Fazenda converter() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Fazenda fazenda = new Fazenda();
        fazenda.setNome(nome);
        fazenda.setEndereco(endereco);
        fazenda.setEmpresa(empresa);
        fazenda.setQuantidadeEstoque(quantidadeEstoque);
        fazenda.setGrao(grao);
        fazenda.setUltimaColheita(formatter.parse(ultimaColheita));
        fazenda.setProximaColheita(LocalDate.parse(ultimaColheita, DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        System.out.println(fazenda.getProximaColheita());

        return fazenda;
    }



}
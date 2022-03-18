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
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    public Fazenda converter() throws ParseException {
        Date date = formatter.parse(ultimaColheita);
        Fazenda fazenda = new Fazenda();
        fazenda.setNome(nome);
        fazenda.setEndereco(endereco);
        fazenda.setEmpresa(empresa);
        fazenda.setQuantidadeEstoque(quantidadeEstoque);
        fazenda.setGrao(grao);
        fazenda.setUltimaColheita(formatter.parse(ultimaColheita));
        fazenda.setProximaColheita(LocalDate.now().plusDays(grao.getTempoMedioDeColheita()));

        return fazenda;
    }



}

package com.DEVAgro.services.dto;

import com.DEVAgro.models.Empresa;
import com.DEVAgro.models.Fazenda;
import com.DEVAgro.models.Grao;
import lombok.Getter;
import lombok.Setter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
    //private Date proximaColheita;
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    public Fazenda converter() throws ParseException {
        Fazenda fazenda = new Fazenda();
        fazenda.setNome(nome);
        fazenda.setEndereco(endereco);
        fazenda.setEmpresa(empresa);
        fazenda.setGrao(grao);
        fazenda.setQuantidadeEstoque(quantidadeEstoque);
        fazenda.setUltimaColheita(formatter.parse(ultimaColheita));
        fazenda.setProximaColheita(proximaColheita().getTime());

        return fazenda;
    }

    public Calendar proximaColheita() throws ParseException {
        Calendar proximaColheita = new GregorianCalendar();

        proximaColheita.setTime(formatter.parse(ultimaColheita));

        proximaColheita.add(Calendar.DAY_OF_MONTH,grao.getTempoMedioDeColheita());
        return proximaColheita;
    }
}

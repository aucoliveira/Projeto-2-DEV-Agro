package com.DEVAgro.services.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class FazendaSummaryDto {

    private Long id;
    private String nome;
    private LocalDate proximaColheita;

}

package com.DEVAgro.services;

import com.DEVAgro.repositories.FazendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FazendaService {

    @Autowired
    FazendaRepository fazendaRepository;
}

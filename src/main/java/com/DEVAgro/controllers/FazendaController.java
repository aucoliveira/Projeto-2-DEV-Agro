package com.DEVAgro.controllers;

import com.DEVAgro.models.Fazenda;
import com.DEVAgro.services.FazendaService;
import com.DEVAgro.services.dto.FazendaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.text.ParseException;
import java.util.List;

@Controller
@RequestMapping("/fazendas")
public class FazendaController {

    @Autowired
    private  FazendaService fazendaService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Fazenda>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(fazendaService.listar());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> salvar(@Valid @RequestBody FazendaDto fazendaDto) throws ParseException {
       Fazenda fazenda  = fazendaService.salvar(fazendaDto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(fazenda.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Fazenda> buscar(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(fazendaService.buscar(id));
    }

    @RequestMapping(value = "/{id}/atualizar", method = RequestMethod.PUT)
    public ResponseEntity<Void> atualizar(@Valid @RequestBody Fazenda fazenda, @PathVariable("id") Long id) {
        fazenda.setId(id);
        fazendaService.atualizar(fazenda);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<Void> deletar(Long id) {
        fazendaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
    //Um endpoint para registrar colheita em uma fazenda, que aumenta o estoque de grãos daquela fazenda.
    @RequestMapping(value = "/{id}/aumentaEstoque", method = RequestMethod.PUT)
    public ResponseEntity<Void> aumentaEstoque(@RequestBody Fazenda fazenda,
                                                @PathVariable("id") Long id) {
        fazenda.setId(id);
        fazendaService.aumentaEstoque(fazenda, fazenda.getValor());
        return ResponseEntity.noContent().build();

    }

    @RequestMapping(value = "/{id}/diminuiEstoque", method = RequestMethod.PUT)
    public ResponseEntity<Void> diminuiEstoque(@RequestBody Fazenda fazenda,
                                                @PathVariable("id") Long id) {
        fazenda.setId(id);
        fazendaService.diminuiEstoque(fazenda, fazenda.getValor());
        return ResponseEntity.noContent().build();

    }

}

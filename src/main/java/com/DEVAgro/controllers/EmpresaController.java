package com.DEVAgro.controllers;

import com.DEVAgro.models.Empresa;
import com.DEVAgro.services.EmpresaService;

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
import java.util.List;

@Controller
@RequestMapping("/empresas")
public class EmpresaController {

    // endpoint do CRUD
    @Autowired
    private EmpresaService empresaService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Empresa>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(empresaService.listar());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> salvar(@Valid @RequestBody Empresa empresa) {
        empresa = empresaService.salvar(empresa);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(empresa.getId()).toUri();
       return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> buscar(@PathVariable("id") Long id) {
        Empresa empresa = empresaService.buscar(id);

        return ResponseEntity.status(HttpStatus.OK).body(empresa);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
        empresaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
     // fim dos endpoint do CRUD

    //Um endpoint que retorna a lista de fazendas de uma empresa.
    //@RequestMapping(value = "/{id}/")

}

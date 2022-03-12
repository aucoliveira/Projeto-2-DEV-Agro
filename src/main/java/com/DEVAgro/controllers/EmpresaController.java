package com.DEVAgro.controllers;

import com.DEVAgro.models.Empresa;
import com.DEVAgro.models.Fazenda;
import com.DEVAgro.services.EmpresaService;

import com.DEVAgro.services.FazendaService;
import org.apache.coyote.Response;
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
import java.util.stream.Stream;

@Controller
@RequestMapping("/empresas")
public class EmpresaController {

    // endpoint do CRUD
    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private FazendaService fazendaService;

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

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> atualizar(@RequestBody Empresa empresa, @PathVariable("id") Long id) {
        empresa.setId(id);

        empresaService.atulizar(empresa);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
        empresaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
     // fim dos endpoint do CRUD

    //Um endpoint que retorna a lista de fazendas de uma empresa.
    @RequestMapping(value = "/{id}/listaFazenda", method = RequestMethod.GET)
    public ResponseEntity<List<Fazenda>> listarFazendaEmpresa(@Valid @PathVariable("id")
                                                                          Long empresaId) {
        List<Fazenda> fazenda = empresaService.listarFazenda(empresaId);

        return ResponseEntity.status(HttpStatus.OK).body(fazenda);
    }

    //Um endpoint que retorna a quantidade de fazendas de uma empresa
    @RequestMapping(value = "/{id}/qtdefazenda", method = RequestMethod.GET)
    public ResponseEntity<?> quantidadeFazenda(@Valid @PathVariable("id") Long id) {

        return ResponseEntity.status(HttpStatus.OK).body(empresaService.qtdeFazendasEmpresa(id));
    }

    @RequestMapping(value = "/{id}/adicionarFazenda", method = RequestMethod.POST)
    public ResponseEntity<Void> adicionarFazenda(@Valid @PathVariable("id") Long empresaId,
                                                 @RequestBody Fazenda fazenda) {
        empresaService.adicionarFazenda(empresaId, fazenda);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();

        return ResponseEntity.created(uri).build();
    }
}

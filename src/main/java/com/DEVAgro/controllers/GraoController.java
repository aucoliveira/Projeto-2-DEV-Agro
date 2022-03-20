package com.DEVAgro.controllers;

import com.DEVAgro.models.Grao;
import com.DEVAgro.services.GraoService;
import com.DEVAgro.services.dto.GraoDto;
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
@RequestMapping("/grao")
public class GraoController {

    @Autowired
    private GraoService graoService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Grao>> listar(){
        return ResponseEntity.status(HttpStatus.OK).body(graoService.listar());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> salvar(@Valid @RequestBody GraoDto graoDto) {
        Grao grao = graoService.salvar(graoDto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(grao.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> buscar(@PathVariable("id") Long id) {
        Grao grao = graoService.buscar(id);

        return ResponseEntity.status(HttpStatus.OK).body(grao);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
        graoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> atualizar(@RequestBody Grao grao,
                                          @PathVariable("id") Long id) {
        grao.setId(id);
        graoService.atualizar(grao);
        return ResponseEntity.noContent().build();
    }


}

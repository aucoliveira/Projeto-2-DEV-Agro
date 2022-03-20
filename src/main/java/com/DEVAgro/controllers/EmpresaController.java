package com.DEVAgro.controllers;

import com.DEVAgro.models.Empresa;
import com.DEVAgro.models.Fazenda;
import com.DEVAgro.models.Funcionario;
import com.DEVAgro.models.Grao;
import com.DEVAgro.repositories.EmpresaRepository;
import com.DEVAgro.repositories.FazendaRepository;
import com.DEVAgro.repositories.GraoRepository;
import com.DEVAgro.services.EmpresaService;

import com.DEVAgro.services.FazendaService;
import com.DEVAgro.services.GraoService;
import com.DEVAgro.services.dto.*;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {

    // endpoint do CRUD
    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private FazendaService fazendaService;

    @Autowired
    private GraoService graoService;

    @Autowired
    private FazendaRepository fazendaRepository;

    @Autowired
    private GraoRepository graoRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

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
        empresaService.atualizar(empresa);
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

    // Um endpoint que retorna a lista de grãos de uma empresa.
    @RequestMapping(value = "/{id}/listaGrao", method = RequestMethod.GET)
    public ResponseEntity<List<Grao>> listarGraoEmpresa(@Valid @PathVariable("id")
                                                                      Long empresaId) {
        List<Grao> grao = empresaService.listarGrao(empresaId);

        return ResponseEntity.status(HttpStatus.OK).body(grao);
    }


    @RequestMapping(value = "/{id}/listaFuncionario", method = RequestMethod.GET)
    public ResponseEntity<List<Funcionario>> listarFuncionarioEmpresa(@Valid @PathVariable("id")
                                                                Long empresaId) {

        List<Funcionario> funcionario = empresaService.listarFuncionario(empresaId);

        return ResponseEntity.status(HttpStatus.OK).body(funcionario);
    }

    @RequestMapping(value = "/{id}/qtdefuncionario", method = RequestMethod.GET)
    public ResponseEntity<?> quantidadeFuncionario(@Valid @PathVariable("id") Long id) {

        return ResponseEntity.status(HttpStatus.OK).body(empresaService.qtdeFuncionarioEmpresa(id));
    }

    @RequestMapping(value = "/{id}/mostraFazenda", method = RequestMethod.GET)
    public ResponseEntity<?> mostra(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(fazendaRepository.findAll()
                .stream()
                .map(this::fazendaDto)
                .collect(Collectors.toList()));
    }

    @RequestMapping(value = "/{id}/mostraGrao", method = RequestMethod.GET)
    public ResponseEntity<?> mostraGrao(@PathVariable("id") Long id) {
//
        return ResponseEntity.status(HttpStatus.OK).body(fazendaRepository.findAll()
                .stream()
                .map(this::mostraG)
                .sorted(Comparator.comparingDouble(GraoSummaryDto::getQtdeEstoque))
                .filter( distinctByKey(g -> g.getNome() + " " + g.getQtdeEstoque()) )
                .collect(Collectors.toList()));

    }


    private FazendaSummaryDto fazendaDto(Fazenda fazenda) {
        var fazendaDto = new FazendaSummaryDto();
        fazendaDto.setId(fazenda.getId());
        fazendaDto.setNome(fazenda.getNome());
        fazendaDto.setProximaColheita(fazenda.getProximaColheita()
                .plusDays(fazenda.getGrao().getTempoMedioDeColheita()));

        return fazendaDto;
    }
//    private GraoSummaryDto toGraoDto(Grao grao) {
//        var graoDto =  new GraoSummaryDto();
//        graoDto.setNome(grao.getNome());
//
//        System.out.println("Aparece");
//        //graoDto.setQtdeEstoque(grao.getFazenda().getQuantidadeEstoque());
//
//        return graoDto;
//    }

    public GraoSummaryDto mostraG(Fazenda fazenda){
        GraoSummaryDto graoSummaryDto = new GraoSummaryDto();
        graoSummaryDto.setNome(fazenda.getGrao().getNome());
        graoSummaryDto.setQtdeEstoque(fazendaRepository.valor(Math.toIntExact(fazenda.getGrao().getId())));

        return graoSummaryDto;
    }
    // Peguei em um site
    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor)
    {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}

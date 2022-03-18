package com.DEVAgro.repositories;

import com.DEVAgro.models.Fazenda;
import com.DEVAgro.models.Grao;
import com.DEVAgro.services.dto.FazendaDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FazendaRepository extends JpaRepository<Fazenda, Long> {

    //List<Fazenda> findFazendaByGrao_idOrderByQuantidadeEstoque(Long id);

    //List<Fazenda> findFazendaByGrao_idOrderByQuantidadeEstoque(Long id);

    @Query(value = "select grao.nome, fazenda.quantidade_estoque from grao, fazenda where grao.id = ? order by quantidade_estoque",
            nativeQuery = true)
    List<Object> listGrao(long id);

//    @Query(value = "SELECT fazenda.id, fazenda.nome, fazenda.proxima_colheita, fazenda.empresa_id, grao.empresa_id, grao.id, grao.tempo_medio_de_colheita \n" +
//            "\tFROM fazenda, grao\n" +
//            "\tWHERE fazenda.empresa_id = grao.empresa_id;", nativeQuery = true)
//    List<Fazenda> findByFazenda();
}

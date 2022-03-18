package com.DEVAgro.repositories;

import com.DEVAgro.models.Fazenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FazendaRepository extends JpaRepository<Fazenda, Long> {

    List<Fazenda> findFazendaByGrao_idOrderByQuantidadeEstoque(Long id);

//    @Query(value = "SELECT fazenda.id, fazenda.nome, fazenda.proxima_colheita, fazenda.empresa_id, grao.empresa_id, grao.id, grao.tempo_medio_de_colheita \n" +
//            "\tFROM fazenda, grao\n" +
//            "\tWHERE fazenda.empresa_id = grao.empresa_id;", nativeQuery = true)
//    List<Fazenda> findByFazenda();
}

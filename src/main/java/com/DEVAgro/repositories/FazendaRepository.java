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



//    @Query(value = "select grao.nome, fazenda.quantidade_estoque from grao, fazenda where grao_id = ? order by quantidade_estoque",
//            nativeQuery = true)
//    List<Object> listGrao(long id);


    @Query(value = "select sum(quantidade_estoque)  \n" +
            "from fazenda where grao_id= ?", nativeQuery = true)
    Double valor(int v);
}

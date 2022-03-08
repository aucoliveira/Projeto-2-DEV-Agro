package com.DEVAgro.repositories;

import com.DEVAgro.models.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

    @Query("select e from Empresa e where e.fazenda = ?1")
    List<Empresa> findByFazenda(String nome);
}

package com.worklink.todosimple.vaga.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.worklink.todosimple.vaga.model.Vaga;
import com.worklink.todosimple.vaga.DTO.VagasPorEmpresaDTO;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VagaRepository extends JpaRepository<Vaga, Long> {

    List<Vaga> findByEmpresa_Cnpj(String cnpj);

    @Query("SELECT new com.worklink.todosimple.vaga.DTO.VagasPorEmpresaDTO(v.empresa.nome, COUNT(v)) " +
           "FROM Vaga v GROUP BY v.empresa.nome")
    List<VagasPorEmpresaDTO> contarVagasPorEmpresa();
}

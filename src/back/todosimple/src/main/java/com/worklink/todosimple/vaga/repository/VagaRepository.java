package com.worklink.todosimple.vaga.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.worklink.todosimple.vaga.model.Vaga;
import com.worklink.todosimple.vaga.DTO.VagasPorEmpresaDTO;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VagaRepository extends JpaRepository<Vaga, Long> {

    // Método para buscar vagas pelo CNPJ da empresa
    List<Vaga> findByEmpresa_Cnpj(String cnpj);
    List<Vaga> findByStatus(String status); // Novo método para buscar por status
}

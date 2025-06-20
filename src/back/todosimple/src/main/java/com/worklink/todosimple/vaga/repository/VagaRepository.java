package com.worklink.todosimple.vaga.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.worklink.todosimple.vaga.model.Vaga;
import java.util.List;

public interface VagaRepository extends JpaRepository<Vaga, Long> {
    List<Vaga> findByEmpresa_Cnpj(String cnpj);
    List<Vaga> findByStatus(String status); // Novo método para buscar por status
}

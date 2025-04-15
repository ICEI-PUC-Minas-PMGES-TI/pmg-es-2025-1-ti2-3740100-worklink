package com.worklink.todosimple.vaga.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.worklink.todosimple.vaga.model.Vaga;

public interface VagaRepository extends JpaRepository<Vaga, Long> {
    // Aqui você pode adicionar métodos personalizados se precisar no futuro
}

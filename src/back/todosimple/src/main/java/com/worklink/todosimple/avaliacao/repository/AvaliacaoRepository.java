package com.worklink.todosimple.avaliacao.repository;

import com.worklink.todosimple.avaliacao.model.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Integer> {
    Optional<Avaliacao> findByEmpresaIdAndCandidatoId(Integer empresaId, Integer candidatoId);
}
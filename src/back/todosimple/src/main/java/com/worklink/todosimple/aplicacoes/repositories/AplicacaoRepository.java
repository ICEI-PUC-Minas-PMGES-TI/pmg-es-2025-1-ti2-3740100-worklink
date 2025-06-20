package com.worklink.todosimple.aplicacoes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.worklink.todosimple.aplicacoes.model.Aplicacao;

import java.util.List;

@Repository
public interface AplicacaoRepository extends JpaRepository<Aplicacao, Long> {
    // Buscar aplicações por CPF do candidato
    List<Aplicacao> findByCandidatoCpf(String cpf);

    // Buscar aplicações por ID da vaga
    List<Aplicacao> findByVagaId(Integer idVaga);

    boolean existsByVagaIdAndCandidatoId(Long vagaId, Long candidatoId);

    boolean existsByVagaIdAndCandidatoCpf(Long vagaId, String cpf);

    @Transactional
    @Modifying
    void deleteByVaga_Id(Long vagaId);
}
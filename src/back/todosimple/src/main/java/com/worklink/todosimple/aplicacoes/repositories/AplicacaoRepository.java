package com.worklink.todosimple.aplicacoes.repositories;

import com.worklink.todosimple.aplicacoes.model.Aplicacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AplicacaoRepository extends JpaRepository<Aplicacao, Integer> {

    // Buscar aplicações por CPF do candidato
    List<Aplicacao> findByCandidatoCpf(String cpf);

    // Buscar aplicações por ID da vaga
    List<Aplicacao> findByVagaId(Integer idVaga);
}
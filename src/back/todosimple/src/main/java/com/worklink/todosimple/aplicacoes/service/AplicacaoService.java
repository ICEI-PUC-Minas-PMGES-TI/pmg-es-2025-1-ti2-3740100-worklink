package com.worklink.todosimple.aplicacoes.service;

import com.worklink.todosimple.aplicacoes.model.Aplicacao;
import com.worklink.todosimple.aplicacoes.repositories.AplicacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AplicacaoService {

    @Autowired
    private AplicacaoRepository aplicacaoRepository;

    // Salvar uma nova aplicação
    public Aplicacao salvar(Aplicacao aplicacao) {
        return aplicacaoRepository.save(aplicacao);
    }

    // Atualizar o status de uma aplicação existente
    public Aplicacao atualizarStatus(Integer id, String novoStatus) {
        Aplicacao aplicacao = aplicacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aplicação não encontrada com o ID: " + id));
        aplicacao.setStatus(novoStatus);
        return aplicacaoRepository.save(aplicacao);
    }

    // Listar todas as aplicações
    public List<Aplicacao> listarTodas() {
        return aplicacaoRepository.findAll();
    }

    // Listar aplicações por CPF do candidato
    public List<Aplicacao> listarPorCandidato(String cpf) {
        return aplicacaoRepository.findByCandidatoCpf(cpf);
    }

    // Listar aplicações por ID da vaga
    public List<Aplicacao> listarPorVaga(Integer idVaga) {
        return aplicacaoRepository.findByVagaId(idVaga);
    }
}
package com.worklink.todosimple.avaliacao.controller;

import com.worklink.todosimple.aplicacoes.model.Aplicacao;
import com.worklink.todosimple.avaliacao.model.Avaliacao;
import com.worklink.todosimple.avaliacao.repository.AvaliacaoRepository;
import com.worklink.todosimple.aplicacoes.repositories.AplicacaoRepository; // Adicione este import
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/avaliacoes")
@CrossOrigin(origins = "*")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private AplicacaoRepository aplicacaoRepository; // Adicione esta linha

    // Criar uma nova avaliação
    @PostMapping
    public ResponseEntity<?> criarAvaliacao(@RequestBody Avaliacao avaliacao) {
        // Verifica se já existe avaliação desse candidato para essa empresa
        boolean jaAvaliou = avaliacaoRepository
            .findAll()
            .stream()
            .anyMatch(a -> 
                a.getEmpresa().getId().equals(avaliacao.getEmpresa().getId()) &&
                a.getCandidato().getId().equals(avaliacao.getCandidato().getId())
            );
        if (jaAvaliou) {
            return ResponseEntity.status(409).body("Você já avaliou esta empresa.");
        }

        Avaliacao novaAvaliacao = avaliacaoRepository.save(avaliacao);
        return ResponseEntity.status(201).body(novaAvaliacao);
    }

    // Listar todas as avaliações
    @GetMapping
    public ResponseEntity<List<Avaliacao>> listarAvaliacoes() {
        return ResponseEntity.ok(avaliacaoRepository.findAll());
    }

    // Buscar avaliação por ID
    @GetMapping("/{id}")
    public ResponseEntity<Avaliacao> buscarAvaliacaoPorId(@PathVariable Integer id) {
        return avaliacaoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/candidato/{cpf}")
    public ResponseEntity<List<Avaliacao>> listarPorCandidato(@PathVariable String cpf) {
        List<Avaliacao> avals = avaliacaoRepository.findAll().stream()
            .filter(a -> a.getCandidato() != null && cpf.equals(a.getCandidato().getCpf()))
            .toList();
        return ResponseEntity.ok(avals);
    }

}
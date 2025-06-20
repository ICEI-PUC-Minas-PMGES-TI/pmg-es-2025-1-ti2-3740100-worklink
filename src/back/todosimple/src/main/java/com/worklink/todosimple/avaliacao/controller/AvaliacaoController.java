package com.worklink.todosimple.avaliacao.controller;

import com.worklink.todosimple.avaliacao.model.Avaliacao;
import com.worklink.todosimple.avaliacao.repository.AvaliacaoRepository;
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

    // Criar uma nova avaliação
    @PostMapping
    public ResponseEntity<Avaliacao> criarAvaliacao(@RequestBody Avaliacao avaliacao) {
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
}
package com.worklink.todosimple.aplicacoes.controller;

import com.worklink.todosimple.aplicacoes.model.Aplicacao;
import com.worklink.todosimple.aplicacoes.service.AplicacaoService;
import com.worklink.todosimple.aplicacoes.repositories.AplicacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aplicacoes")
@CrossOrigin(origins = "*")
public class AplicacaoController {

    @Autowired
    private AplicacaoService aplicacaoService;

    @Autowired
    private AplicacaoRepository aplicacaoRepository;

    // 1. Criar uma nova aplicação (inscrição do candidato em uma vaga)
    @PostMapping
    public ResponseEntity<Aplicacao> criarAplicacao(@RequestBody Aplicacao aplicacao) {
        aplicacao.setStatus("Em Análise"); // Define o status inicial
        Aplicacao novaAplicacao = aplicacaoService.salvar(aplicacao);
        return ResponseEntity.ok(novaAplicacao);
    }

    // 2. Atualizar o status de uma aplicação
    @PutMapping("/{id}/status")
    public ResponseEntity<Aplicacao> atualizarStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        Aplicacao aplicacaoAtualizada = aplicacaoService.atualizarStatus(id, status);
        return ResponseEntity.ok(aplicacaoAtualizada);
    }

    // 3. Listar todas as aplicações
    @GetMapping
    public ResponseEntity<List<Aplicacao>> listarTodas() {
        List<Aplicacao> aplicacoes = aplicacaoService.listarTodas();
        return ResponseEntity.ok(aplicacoes);
    }

    // 4. Buscar aplicações por CPF do candidato
    @GetMapping("/candidato/{cpf}")
    public ResponseEntity<List<Aplicacao>> listarPorCandidato(@PathVariable String cpf) {
        List<Aplicacao> aplicacoes = aplicacaoService.listarPorCandidato(cpf);
        return ResponseEntity.ok(aplicacoes);
    }

    // 5. Buscar aplicações por ID da vaga
    @GetMapping("/vaga/{idVaga}")
    public ResponseEntity<List<Aplicacao>> listarPorVaga(@PathVariable Integer idVaga) {
        List<Aplicacao> aplicacoes = aplicacaoService.listarPorVaga(idVaga);
        return ResponseEntity.ok(aplicacoes);
    }

    // Verificar se o CPF do candidato já está inscrito na vaga
    @GetMapping("/verificar-inscricao")
    public ResponseEntity<Boolean> verificarInscricao(@RequestParam Long vagaId, @RequestParam String cpf) {
        boolean inscrito = aplicacaoRepository.existsByVagaIdAndCandidatoCpf(vagaId, cpf);
        return ResponseEntity.ok(inscrito);
    }
}
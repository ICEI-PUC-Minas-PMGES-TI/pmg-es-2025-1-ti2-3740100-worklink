package com.worklink.todosimple.aplicacoes.controller;

import com.worklink.todosimple.aplicacoes.model.Aplicacao;
import com.worklink.todosimple.aplicacoes.service.AplicacaoService;
import com.worklink.todosimple.aplicacoes.repositories.AplicacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    // 6. Upload do teste-resposta do candidato
    @PostMapping("/{id}/teste-resposta")
    public ResponseEntity<?> uploadTesteResposta(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file) {
        try {
            // Caminho onde os arquivos serão salvos (ajuste conforme sua estrutura)
            String pastaUpload = "uploads/testes_resposta";
            Files.createDirectories(Paths.get(pastaUpload));

            // Gera um nome único para o arquivo
            String nomeArquivo = id + "_" + StringUtils.cleanPath(file.getOriginalFilename());
            Path caminhoArquivo = Paths.get(pastaUpload).resolve(nomeArquivo);

            // Salva o arquivo no disco
            Files.copy(file.getInputStream(), caminhoArquivo);

            // Atualiza o campo testeResposta da aplicação
            Aplicacao aplicacao = aplicacaoService.buscarPorId(id);
            aplicacao.setTesteResposta("/" + pastaUpload + "/" + nomeArquivo);
            aplicacaoService.salvar(aplicacao);

            return ResponseEntity.ok("Arquivo enviado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao enviar arquivo: " + e.getMessage());
        }
    }
}
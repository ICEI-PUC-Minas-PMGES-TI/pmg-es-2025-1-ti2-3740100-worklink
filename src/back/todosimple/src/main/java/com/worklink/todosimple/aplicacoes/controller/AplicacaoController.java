package com.worklink.todosimple.aplicacoes.controller;

import com.worklink.todosimple.aplicacoes.model.Aplicacao;
import com.worklink.todosimple.aplicacoes.service.AplicacaoService;
import com.worklink.todosimple.aplicacoes.repositories.AplicacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
    public ResponseEntity<?> atualizarStatus(@PathVariable Long id, @RequestParam String status) {
        try {
            Aplicacao aplicacao = aplicacaoService.buscarPorId(id);
            if (aplicacao == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aplicação não encontrada.");
            }

            aplicacao.setStatus(status);
            aplicacaoService.salvar(aplicacao);

            return ResponseEntity.ok(aplicacao);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar status: " + e.getMessage());
        }
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
            // Busca a candidatura pelo ID
            Aplicacao aplicacao = aplicacaoService.buscarPorId(id);
            if (aplicacao == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Candidatura não encontrada.");
            }

            // Define o nome do arquivo com o padrão "Teste{Candidato}-{Vaga}"
            String candidatoNome = aplicacao.getCandidato().getNome().replaceAll("\\s+", "");
            String vagaTitulo = aplicacao.getVaga().getTitulo().replaceAll("\\s+", "");
            String nomeArquivo = String.format("Teste%s-%s.pdf", candidatoNome, vagaTitulo);

            // Define o caminho para salvar o arquivo
            String pastaUpload = "src/back/todosimple/src/main/resources/static/uploads/testes_resposta";
            Files.createDirectories(Paths.get(pastaUpload));
            Path caminhoArquivo = Paths.get(pastaUpload).resolve(nomeArquivo);

            // Salva o arquivo no disco
            Files.copy(file.getInputStream(), caminhoArquivo, StandardCopyOption.REPLACE_EXISTING);

            // Atualiza o campo testeResposta da aplicação
            aplicacao.setTesteResposta("/uploads/testes_resposta/" + nomeArquivo);
            aplicacaoService.salvar(aplicacao);

            return ResponseEntity.ok("Arquivo enviado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao enviar arquivo: " + e.getMessage());
        }
    }

    // 7. Atualizar feedback da aplicação
    @PutMapping("/{id}/feedback")
    public ResponseEntity<?> atualizarFeedback(@PathVariable Long id, @RequestBody String feedback) {
        try {
            Aplicacao aplicacao = aplicacaoService.buscarPorId(id);
            if (aplicacao == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aplicação não encontrada.");
            }

            aplicacao.setFeedback(feedback);
            aplicacaoService.salvar(aplicacao);

            return ResponseEntity.ok("Feedback atualizado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar feedback: " + e.getMessage());
        }
    }

    // Buscar uma aplicação por ID
    @GetMapping("/{id}")
    public ResponseEntity<Aplicacao> buscarPorId(@PathVariable Long id) {
        Aplicacao aplicacao = aplicacaoService.buscarPorId(id);
        if (aplicacao == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(aplicacao);
    }

    @GetMapping("/indicadores/avaliacao-por-empresa")
    public ResponseEntity<Double> getPercentualCandidaturasAvaliadas() {
        List<Aplicacao> aplicacoes = aplicacaoRepository.findAll();
        if (aplicacoes.isEmpty()) return ResponseEntity.ok(0.0);

        long avaliadas = aplicacoes.stream()
            .filter(a -> a.getStatus() != null && !a.getStatus().equalsIgnoreCase("Pendente"))
            .count();

        double percentual = (avaliadas * 100.0) / aplicacoes.size();
        return ResponseEntity.ok(percentual);
    }
}
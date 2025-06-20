package com.worklink.todosimple.vaga.controller;

import com.worklink.todosimple.vaga.model.Vaga;
import com.worklink.todosimple.vaga.repository.VagaRepository;
import com.worklink.todosimple.cadastro.models.Empresa;
import com.worklink.todosimple.cadastro.repositories.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.domain.Sort;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.io.IOException;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/vagas")
public class VagaController {

    @Autowired
    private VagaRepository vagaRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private com.worklink.todosimple.aplicacoes.repositories.AplicacaoRepository aplicacaoRepository;

    @Autowired
    // GET todas as vagas
    @GetMapping
    public List<Vaga> getAllVagas() {
        return vagaRepository.findAll(Sort.by(Sort.Direction.DESC, "dataCriacao"));
    }

    // GET vaga por ID
    @GetMapping("/{id}")
    public ResponseEntity<Vaga> getVagaByID(@PathVariable Long id) {
        Optional<Vaga> vaga = vagaRepository.findById(id);
        return vaga.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // GET vagas por CNPJ
    @GetMapping("/empresa/cnpj/{cnpj}")
    public ResponseEntity<List<Vaga>> listarVagasPorCnpj(@PathVariable String cnpj) {
        List<Vaga> vagas = vagaRepository.findByEmpresa_Cnpj(cnpj);
        return ResponseEntity.ok(vagas);
    }

    // GET vagas por status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Vaga>> listarVagasPorStatus(@PathVariable String status) {
        List<Vaga> vagas = vagaRepository.findByStatus(status);
        return ResponseEntity.ok(vagas);
    }

    // POST criar vaga
    @PostMapping
    public ResponseEntity<Vaga> createVaga(@RequestBody Map<String, Object> vagaMap) {
        String cnpj = (String) vagaMap.get("cnpj");
        if (cnpj == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        Empresa empresa = empresaRepository.findByCnpj(cnpj);
        if (empresa == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        Vaga vaga = new Vaga();
        vaga.setTitulo((String) vagaMap.get("titulo"));
        vaga.setDescricao((String) vagaMap.get("descricao"));
        vaga.setBeneficios((String) vagaMap.get("beneficios"));
        vaga.setSalario(vagaMap.get("salario") != null ? Double.parseDouble(vagaMap.get("salario").toString()) : 0);

        // Conversão da data
        try {
            String dataFinalStr = (String) vagaMap.get("dataFinal");
            if (dataFinalStr != null && !dataFinalStr.isEmpty()) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date dataFinal = sdf.parse(dataFinalStr);
                vaga.setDataFinal(dataFinal);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        vaga.setTipoContrato((String) vagaMap.get("tipoContrato"));
        vaga.setModalidade((String) vagaMap.get("modalidade"));
        vaga.setEmpresa(empresa);

        // Definir data de criação
        vaga.setDataCriacao(new Date());

        // Definir status (opcional, padrão "Aberta")
        String status = (String) vagaMap.get("status");
        vaga.setStatus("Aberta"); // Sempre começa como "Aberta"

        Vaga savedVaga = vagaRepository.save(vaga);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedVaga);
    }

    // PUT atualizar vaga
    @PutMapping("/{id}")
    public ResponseEntity<Vaga> updateVaga(@PathVariable Long id, @RequestBody Map<String, Object> vagaMap) {
        return vagaRepository.findById(id)
            .map(vaga -> {
                vaga.setTitulo((String) vagaMap.get("titulo"));
                vaga.setDescricao((String) vagaMap.get("descricao"));
                vaga.setBeneficios((String) vagaMap.get("beneficios"));
                vaga.setSalario(vagaMap.get("salario") != null ? Double.parseDouble(vagaMap.get("salario").toString()) : 0);

                // Atualiza dataFinal
                try {
                    String dataFinalStr = (String) vagaMap.get("dataFinal");
                    if (dataFinalStr != null && !dataFinalStr.isEmpty()) {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        vaga.setDataFinal(sdf.parse(dataFinalStr));
                    }
                } catch (Exception e) {
                    // Se der erro, mantém a data antiga
                }

                vaga.setTipoContrato((String) vagaMap.get("tipoContrato"));
                vaga.setModalidade((String) vagaMap.get("modalidade"));

                // Atualiza empresa se vier no body
                if (vagaMap.get("empresa") instanceof Map empresaMap) {
                    String cnpj = (String) empresaMap.get("cnpj");
                    if (cnpj != null) {
                        Empresa empresa = empresaRepository.findByCnpj(cnpj);
                        if (empresa != null) vaga.setEmpresa(empresa);
                    }
                }

                // Atualiza status SEM checar vazio/nulo
                if (vagaMap.containsKey("status")) {
                    vaga.setStatus((String) vagaMap.get("status"));
                }

                vagaRepository.save(vaga);
                return ResponseEntity.ok(vaga);
            })
            .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // DELETE vaga
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVaga(@PathVariable Long id) {
        // Deleta todas as candidaturas associadas à vaga
        aplicacaoRepository.deleteByVaga_Id(id);

        // Agora deleta a vaga
        vagaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // POST enviar teste
    @PostMapping("/{id}/teste")
    public ResponseEntity<?> enviarTeste(@PathVariable Long id, @RequestParam(value = "arquivoTeste", required = false) MultipartFile arquivoTeste) {
        try {
            // Busca a vaga pelo ID
            Vaga vaga = vagaRepository.findById(id).orElseThrow(() -> new RuntimeException("Vaga não encontrada"));

            // Verifica se o arquivo foi enviado
            if (arquivoTeste != null && !arquivoTeste.isEmpty()) {
                String diretorio = "src/back/todosimple/src/main/resources/static/uploads/testes/";
                String nomeArquivo = UUID.randomUUID() + "_" + arquivoTeste.getOriginalFilename();
                Path caminho = Paths.get(diretorio + nomeArquivo);
                Files.createDirectories(caminho.getParent());
                Files.write(caminho, arquivoTeste.getBytes());

                // Atualiza o caminho do teste na vaga
                vaga.setTeste("/uploads/testes/" + nomeArquivo);
            } else {
                vaga.setTeste(null); // Caso nenhum arquivo seja enviado
            }

            vagaRepository.save(vaga);
            return ResponseEntity.ok("Teste atualizado com sucesso!");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao salvar o arquivo de teste: " + e.getMessage());
        }
    }
}

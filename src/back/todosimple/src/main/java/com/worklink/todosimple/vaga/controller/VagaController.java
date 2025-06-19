package com.worklink.todosimple.vaga.controller;

import com.worklink.todosimple.vaga.DTO.VagasPorEmpresaDTO;
import com.worklink.todosimple.vaga.DTO.CandidaturasPorVagaDTO;
import com.worklink.todosimple.vaga.model.Vaga;
import com.worklink.todosimple.vaga.repository.VagaRepository;
import com.worklink.todosimple.aplicacoes.repositories.AplicacaoRepository;
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
import java.util.stream.Collectors;
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
    private AplicacaoRepository aplicacaoRepository;

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

        vaga.setDataCriacao(new Date());

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

                try {
                    String dataFinalStr = (String) vagaMap.get("dataFinal");
                    if (dataFinalStr != null && !dataFinalStr.isEmpty()) {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        vaga.setDataFinal(sdf.parse(dataFinalStr));
                    }
                } catch (Exception e) {
                    // mantém data antiga se erro
                }

                vaga.setTipoContrato((String) vagaMap.get("tipoContrato"));
                vaga.setModalidade((String) vagaMap.get("modalidade"));

                if (vagaMap.get("empresa") instanceof Map empresaMap) {
                    String cnpj = (String) empresaMap.get("cnpj");
                    if (cnpj != null) {
                        Empresa empresa = empresaRepository.findByCnpj(cnpj);
                        if (empresa != null) vaga.setEmpresa(empresa);
                    }
                }

                vagaRepository.save(vaga);
                return ResponseEntity.ok(vaga);
            })
            .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // DELETE vaga
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVaga(@PathVariable Long id) {
        if (!vagaRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        vagaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // POST enviar teste técnico
    @PostMapping("/{id}/teste")
    public ResponseEntity<?> enviarTeste(@PathVariable Long id, @RequestParam(value = "arquivoTeste", required = false) MultipartFile arquivoTeste) {
        try {
            Vaga vaga = vagaRepository.findById(id).orElseThrow(() -> new RuntimeException("Vaga não encontrada"));

            if (arquivoTeste != null && !arquivoTeste.isEmpty()) {
                String diretorio = "src/back/todosimple/src/main/resources/static/uploads/testes/";
                String nomeArquivo = UUID.randomUUID() + "_" + arquivoTeste.getOriginalFilename();
                Path caminho = Paths.get(diretorio + nomeArquivo);
                Files.createDirectories(caminho.getParent());
                Files.write(caminho, arquivoTeste.getBytes());

                vaga.setTeste("/uploads/testes/" + nomeArquivo);
            } else {
                vaga.setTeste(null);
            }

            vagaRepository.save(vaga);
            return ResponseEntity.ok("Teste atualizado com sucesso!");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao salvar o arquivo de teste: " + e.getMessage());
        }
    }

    // GET vagas agrupadas por empresa
    @GetMapping("/indicadores/vagas-por-empresa")
    public ResponseEntity<List<VagasPorEmpresaDTO>> obterVagasPorEmpresa() {
        List<Vaga> todasVagas = vagaRepository.findAll();

        Map<String, Long> vagasPorEmpresa = todasVagas.stream()
            .filter(v -> v.getEmpresa() != null && v.getEmpresa().getNome() != null)
            .collect(Collectors.groupingBy(v -> v.getEmpresa().getNome(), Collectors.counting()));

        List<VagasPorEmpresaDTO> resultado = vagasPorEmpresa.entrySet().stream()
            .map(entry -> new VagasPorEmpresaDTO(entry.getKey(), entry.getValue()))
            .collect(Collectors.toList());

        return ResponseEntity.ok(resultado);
    }

    // GET indicador Candidaturas por Vaga - total geral
    @GetMapping("/indicadores/candidaturas-por-vaga")
    public ResponseEntity<CandidaturasPorVagaDTO> getCandidaturasPorVaga() {
        long totalVagas = vagaRepository.count();
        long totalCandidaturas = aplicacaoRepository.count();
        CandidaturasPorVagaDTO dto = new CandidaturasPorVagaDTO(totalVagas, totalCandidaturas);
        return ResponseEntity.ok(dto);
    }

    // GET indicador Candidaturas por Vaga para empresa específica via CNPJ
    @GetMapping("/indicadores/candidaturas-por-vaga/{cnpj}")
    public ResponseEntity<CandidaturasPorVagaDTO> getCandidaturasPorVagaPorEmpresa(@PathVariable String cnpj) {
        List<Vaga> vagasDaEmpresa = vagaRepository.findByEmpresa_Cnpj(cnpj);
        long totalVagas = vagasDaEmpresa.size();

        if (totalVagas == 0) {
            return ResponseEntity.ok(new CandidaturasPorVagaDTO(0, 0)); // evita divisão por zero
        }

        List<Long> idsDasVagas = vagasDaEmpresa.stream()
                .map(Vaga::getId)
                .collect(Collectors.toList());

        long totalCandidaturas = aplicacaoRepository.countByVagaIdIn(idsDasVagas);

        CandidaturasPorVagaDTO dto = new CandidaturasPorVagaDTO(totalVagas, totalCandidaturas);
        return ResponseEntity.ok(dto);
    }
}

package com.worklink.todosimple.cadastro.controllers;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.worklink.todosimple.avaliacao.model.Avaliacao;
import com.worklink.todosimple.avaliacao.repository.AvaliacaoRepository;
import com.worklink.todosimple.cadastro.models.Empresa;
import com.worklink.todosimple.cadastro.repositories.EmpresaRepository;
import com.worklink.todosimple.cadastro.services.UsuarioService;

@RestController
@RequestMapping("/empresas")
@CrossOrigin(origins = "*")
public class EmpresaController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody Empresa empresa) {
        if (empresaJaExiste(empresa)) {
            return ResponseEntity.status(409).body(
                Map.of("mensagem", "Empresa já cadastrada")
            );
        }
        empresa = usuarioService.createEmpresa(empresa);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}").buildAndExpand(empresa.getId()).toUri();
        return ResponseEntity.created(uri).body(empresa);
    }

    @GetMapping
    public ResponseEntity<List<Empresa>> findAll() {
        List<Empresa> empresas = usuarioService.findAllEmpresas();
        return ResponseEntity.ok().body(empresas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empresa> findById(@PathVariable Integer id) {
        Empresa empresa = usuarioService.findEmpresaById(id);
        if (empresa == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(empresa);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Empresa> findEmpresaByEmail(@PathVariable String email) {
        Empresa empresa = usuarioService.findEmpresaByEmail(email);
        if (empresa == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(empresa);
    }

    @GetMapping("/cnpj/{cnpj}")
    public ResponseEntity<Empresa> findEmpresaByCnpj(@PathVariable String cnpj) {
        Empresa empresa = usuarioService.findEmpresaByCnpj(cnpj);
        if (empresa == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(empresa);
    }

    @GetMapping("/telefone/{telefone}")
    public ResponseEntity<Empresa> findEmpresaByTelefone(@PathVariable String telefone) {
        Empresa empresa = usuarioService.findEmpresaByTelefone(telefone);
        if (empresa == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(empresa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(
            @PathVariable Integer id,
            @RequestParam("nome") String nome,
            @RequestParam("descricao") String descricao,
            @RequestParam("endereco") String endereco,
            @RequestParam("email") String email,
            @RequestParam("telefone") String telefone,
            @RequestParam("cep") String cep,
            @RequestParam("cidade") String cidade,
            @RequestParam("numero") String numero,
            @RequestParam("nomeResponsavel") String nomeResponsavel,
            @RequestParam("cargo") String cargo,
            @RequestParam("setor") String setor, // Novo campo
            @RequestParam(value = "fotoPerfil", required = false) MultipartFile fotoPerfil) {

        try {
            Empresa empresa = usuarioService.findEmpresaById(id);
            if (empresa == null) {
                return ResponseEntity.notFound().build();
            }

            empresa.setNome(nome);
            empresa.setDescricao(descricao);
            empresa.setEndereco(endereco);
            empresa.setEmail(email);
            empresa.setTelefone(telefone);
            empresa.setCep(cep);
            empresa.setCidade(cidade);
            empresa.setNumero(numero);
            empresa.setNomeResponsavel(nomeResponsavel);
            empresa.setCargo(cargo);
            empresa.setSetor(setor); // Atualiza o setor

            if (fotoPerfil != null && !fotoPerfil.isEmpty()) {
                String caminhoImagem = salvarImagem(fotoPerfil);
                empresa.setFotoPerfil(caminhoImagem);
            }

            usuarioService.updateEmpresa(empresa);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PutMapping("/cnpj/{cnpj}")
    public ResponseEntity<Void> updateByCnpj(
            @PathVariable String cnpj,
            @RequestParam("nome") String nome,
            @RequestParam("descricao") String descricao,
            @RequestParam("endereco") String endereco,
            @RequestParam("email") String email,
            @RequestParam("telefone") String telefone,
            @RequestParam("cep") String cep,
            @RequestParam("cidade") String cidade,
            @RequestParam("numero") String numero,
            @RequestParam("nomeResponsavel") String nomeResponsavel,
            @RequestParam("cargo") String cargo,
            @RequestParam("setor") String setor, // <-- ADICIONE ESTA LINHA
            @RequestParam(value = "fotoPerfil", required = false) MultipartFile fotoPerfil) {

        try {
            Empresa empresa = usuarioService.findEmpresaByCnpj(cnpj);
            if (empresa == null) {
                System.out.println("Empresa não encontrada com CNPJ: " + cnpj);
                return ResponseEntity.notFound().build();
            }

            // Atualiza os campos da empresa
            empresa.setNome(nome);
            empresa.setDescricao(descricao);
            empresa.setEndereco(endereco);
            empresa.setEmail(email);
            empresa.setTelefone(telefone);
            empresa.setCep(cep);
            empresa.setCidade(cidade);
            empresa.setNumero(numero);
            empresa.setNomeResponsavel(nomeResponsavel);
            empresa.setCargo(cargo);
            empresa.setSetor(setor); // <-- SALVA O SETOR

            // Salva a imagem no servidor e atualiza o caminho no banco de dados
            if (fotoPerfil != null && !fotoPerfil.isEmpty()) {
                String caminhoImagem = salvarImagem(fotoPerfil);
                empresa.setFotoPerfil(caminhoImagem);
            }

            usuarioService.updateEmpresa(empresa);
            System.out.println("Empresa atualizada com sucesso!");
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            System.err.println("Erro ao atualizar empresa: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        usuarioService.deleteEmpresa(id);
        return ResponseEntity.noContent().build();
    }

    public boolean empresaJaExiste(Empresa empresa) {
        if (empresaRepository.findByEmail(empresa.getEmail()) != null) return true;
        if (empresaRepository.findByCnpj(empresa.getCnpj()) != null) return true;
        if (empresaRepository.findByNome(empresa.getNome()) != null) return true;
        if (empresaRepository.findByTelefone(empresa.getTelefone()) != null) return true;
        return false;
    }

    private String salvarImagem(MultipartFile fotoPerfil) throws IOException {
        // Diretório onde as imagens serão salvas
        String diretorio = "src/back/todosimple/src/main/resources/static/uploads/";
        
        // Gera um nome único para o arquivo
        String nomeArquivo = UUID.randomUUID() + "_" + fotoPerfil.getOriginalFilename();
        
        // Caminho completo do arquivo
        Path caminho = Paths.get(diretorio + nomeArquivo);
        
        // Log para verificar o caminho gerado
        System.out.println("Salvando imagem em: " + caminho.toString());
        
        // Cria o diretório, se não existir
        Files.createDirectories(caminho.getParent());
        
        // Salva o arquivo no sistema de arquivos
        Files.write(caminho, fotoPerfil.getBytes());
        
        // Log para confirmar que a imagem foi salva
        System.out.println("Imagem salva com sucesso!");
        
        // Retorna o caminho relativo para o frontend
        return "/uploads/" + nomeArquivo;
    }

    // Endpoint para listar empresas com média e quantidade de avaliações
    @GetMapping("/com-avaliacoes")
    public ResponseEntity<List<Map<String, Object>>> findAllWithAvaliacao() {
        List<Empresa> empresas = usuarioService.findAllEmpresas();
        List<Map<String, Object>> resultado = empresas.stream().map(empresa -> {
            List<Avaliacao> avals = avaliacaoRepository.findAll().stream()
                .filter(a -> a.getEmpresa().getId().equals(empresa.getId()))
                .toList();
            double media = avals.stream().mapToDouble(Avaliacao::getNota).average().orElse(0.0);
            int qtd = avals.size();
            Map<String, Object> map = new java.util.HashMap<>();
            map.put("id", empresa.getId());
            map.put("nome", empresa.getNome());
            map.put("setor", empresa.getSetor());
            map.put("cidade", empresa.getCidade());
            map.put("mediaAvaliacao", media);
            map.put("qtdAvaliacoes", qtd);
            // Adicione outros campos que quiser exibir no card
            return map;
        }).toList();
        return ResponseEntity.ok(resultado);
    }
}
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

import com.worklink.todosimple.cadastro.models.Empresa;
import com.worklink.todosimple.cadastro.services.UsuarioService;
import com.worklink.todosimple.cadastro.repositories.EmpresaRepository;

@RestController
@RequestMapping("/empresas")
@CrossOrigin(origins = "*")
public class EmpresaController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private EmpresaRepository empresaRepository;

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
            @RequestParam(value = "fotoPerfil", required = false) MultipartFile fotoPerfil) {

        try {
            System.out.println("Iniciando atualização da empresa com ID: " + id);

            Empresa empresa = usuarioService.findEmpresaById(id);
            if (empresa == null) {
                System.out.println("Empresa não encontrada com ID: " + id);
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

            // Salva a imagem no servidor e atualiza o caminho no banco de dados
            if (fotoPerfil != null && !fotoPerfil.isEmpty()) {
                try {
                    System.out.println("Salvando imagem...");
                    String caminhoImagem = salvarImagem(fotoPerfil);
                    empresa.setFotoPerfil(caminhoImagem);
                    System.out.println("Imagem salva em: " + caminhoImagem);
                } catch (IOException e) {
                    System.err.println("Erro ao salvar a imagem: " + e.getMessage());
                    return ResponseEntity.status(500).body(null);
                }
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
}
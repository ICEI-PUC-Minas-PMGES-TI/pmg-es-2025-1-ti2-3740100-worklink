package com.worklink.todosimple.cadastro.controllers;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.worklink.todosimple.cadastro.models.Candidato;
import com.worklink.todosimple.cadastro.services.UsuarioService;

@RestController
@RequestMapping("/candidatos")
@CrossOrigin(origins = "*")
public class CandidatoController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Candidato> create(@RequestBody Candidato candidato) {
        System.out.println("Candidato recebido: " + candidato); // Log para verificar o objeto recebido
        System.out.println("CEP recebido: " + candidato.getCep()); // Log específico para o CEP

        if (usuarioService.emailJaExiste(candidato.getEmail())) {
            return ResponseEntity.status(409).build(); // 409 Conflict
        }
        candidato = usuarioService.createCandidato(candidato);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}").buildAndExpand(candidato.getId()).toUri();
        return ResponseEntity.created(uri).body(candidato);
    }

    @GetMapping
    public ResponseEntity<List<Candidato>> findAll() {
        List<Candidato> candidatos = usuarioService.findAllCandidatos();
        return ResponseEntity.ok().body(candidatos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Candidato> findById(@PathVariable Integer id) {
        Candidato candidato = usuarioService.findCandidatoById(id);
        if (candidato == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(candidato);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(
            @PathVariable Integer id,
            @RequestParam("nome") String nome,
            @RequestParam("formacao") String formacao,
            @RequestParam("areaAtuacao") String areaAtuacao,
            @RequestParam("experiencia") Integer experiencia,
            @RequestParam("habilidades") String habilidades,
            @RequestParam("email") String email,
            @RequestParam("telefone") String telefone,
            @RequestParam(value = "sobre", required = false) String sobre,
            @RequestParam(value = "links", required = false) String links,
            @RequestParam(value = "idiomas", required = false) String idiomas,
            @RequestParam(value = "disponibilidade", required = false) String disponibilidade,
            @RequestParam(value = "experiencias", required = false) String experiencias, // <-- NOVO CAMPO
            @RequestParam(value = "fotoPerfil", required = false) MultipartFile fotoPerfil) {

        try {
            Candidato candidato = usuarioService.findCandidatoById(id);
            if (candidato == null) {
                return ResponseEntity.notFound().build();
            }

            candidato.setNome(nome);
            candidato.setFormacao(formacao);
            candidato.setAreaAtuacao(areaAtuacao);
            candidato.setExperiencia(experiencia);
            candidato.setHabilidades(habilidades);
            candidato.setEmail(email);
            candidato.setTelefone(telefone);

            // Novos campos
            if (sobre != null) candidato.setSobre(sobre);
            if (links != null) candidato.setLinks(links);
            if (idiomas != null) candidato.setIdiomas(idiomas);
            if (disponibilidade != null) candidato.setDisponibilidade(disponibilidade);
            if (experiencias != null) candidato.setExperiencias(experiencias); // <-- NOVO CAMPO

            if (fotoPerfil != null && !fotoPerfil.isEmpty()) {
                String caminhoImagem = salvarImagem(fotoPerfil);
                candidato.setFotoPerfil(caminhoImagem);
            }

            usuarioService.updateCandidato(candidato);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        usuarioService.deleteCandidato(id);
        return ResponseEntity.noContent().build();
    }

    // Método auxiliar para salvar a imagem de perfil
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
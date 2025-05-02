package com.worklink.todosimple.cadastro.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
        return ResponseEntity.ok().body(candidato);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody Candidato candidato, @PathVariable Integer id) {
        candidato.setId(id);
        usuarioService.updateCandidato(candidato);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        usuarioService.deleteCandidato(id);
        return ResponseEntity.noContent().build();
    }
}
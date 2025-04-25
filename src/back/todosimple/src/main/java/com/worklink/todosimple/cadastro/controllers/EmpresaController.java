package com.worklink.todosimple.cadastro.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.worklink.todosimple.cadastro.models.Empresa;
import com.worklink.todosimple.cadastro.services.UsuarioService;

@RestController
@RequestMapping("/empresas")
@CrossOrigin(origins = "*")
public class EmpresaController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Empresa> create(@RequestBody Empresa empresa) {
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
        return ResponseEntity.ok().body(empresa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody Empresa empresa, @PathVariable Integer id) {
        empresa.setId(id);
        usuarioService.updateEmpresa(empresa);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        usuarioService.deleteEmpresa(id);
        return ResponseEntity.noContent().build();
    }
}
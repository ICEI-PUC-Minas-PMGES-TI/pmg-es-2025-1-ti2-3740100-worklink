package com.worklink.todosimple.cadastro.controllers;

import java.net.URI;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.worklink.todosimple.cadastro.models.Empresa;
import com.worklink.todosimple.cadastro.services.UsuarioService;
import com.worklink.todosimple.cadastro.repositories.EmpresaRepository; // ajuste o caminho se necessário

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
        if (empresaRepository.findByEmail(empresa.getEmail()) != null) {
            return ResponseEntity.status(409).body(
                Map.of("campo", "email", "mensagem", "E-mail já cadastrado")
            );
        }
        if (empresaRepository.findByCnpj(empresa.getCnpj()) != null) {
            return ResponseEntity.status(409).body(
                Map.of("campo", "cnpj", "mensagem", "CNPJ já cadastrado")
            );
        }
        if (empresaRepository.findByNome(empresa.getNome()) != null) {
            return ResponseEntity.status(409).body(
                Map.of("campo", "nome", "mensagem", "Nome já cadastrado")
            );
        }
        if (empresaRepository.findByTelefone(empresa.getTelefone()) != null) {
            return ResponseEntity.status(409).body(
                Map.of("campo", "telefone", "mensagem", "Telefone já cadastrado")
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

    // Buscar empresa por CNPJ
    @GetMapping("/cnpj/{cnpj}")
    public ResponseEntity<Empresa> findEmpresaByCnpj(@PathVariable String cnpj) {
        Empresa empresa = usuarioService.findEmpresaByCnpj(cnpj);
        if (empresa == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(empresa);
    }

    // Buscar empresa por telefone
    @GetMapping("/telefone/{telefone}")
    public ResponseEntity<Empresa> findEmpresaByTelefone(@PathVariable String telefone) {
        Empresa empresa = usuarioService.findEmpresaByTelefone(telefone);
        if (empresa == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(empresa);
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

    public boolean empresaJaExiste(Empresa empresa) {
        if (empresaRepository.findByEmail(empresa.getEmail()) != null) return true;
        if (empresaRepository.findByCnpj(empresa.getCnpj()) != null) return true;
        if (empresaRepository.findByNome(empresa.getNome()) != null) return true;
        if (empresaRepository.findByTelefone(empresa.getTelefone()) != null) return true;
        return false;
    }
}
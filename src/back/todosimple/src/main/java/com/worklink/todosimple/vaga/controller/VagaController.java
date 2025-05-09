package com.worklink.todosimple.vaga.controller;

import com.worklink.todosimple.vaga.model.Vaga;
import com.worklink.todosimple.vaga.repository.VagaRepository;
import com.worklink.todosimple.cadastro.models.Empresa;
import com.worklink.todosimple.cadastro.repositories.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/vagas")
public class VagaController {

    @Autowired
    private VagaRepository vagaRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    // GET todas as vagas
    @GetMapping
    public List<Vaga> getAllVagas() {
        return vagaRepository.findAll();
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
    public ResponseEntity<Vaga> createVaga(@RequestBody Vaga vaga) {
        Vaga savedVaga = vagaRepository.save(vaga);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedVaga);
    }

    // PUT atualizar vaga
    @PutMapping("/{id}")
    public ResponseEntity<Vaga> updateVaga(@PathVariable Long id, @RequestBody Vaga vagaAtualizada) {
        return vagaRepository.findById(id)
            .map(vaga -> {
                vaga.setTitulo(vagaAtualizada.getTitulo());
                vaga.setDescricao(vagaAtualizada.getDescricao());
                vaga.setBeneficios(vagaAtualizada.getBeneficios());
                vaga.setDataFinal(vagaAtualizada.getDataFinal());
                vaga.setTipoContrato(vagaAtualizada.getTipoContrato());
                vaga.setModalidade(vagaAtualizada.getModalidade());
                vaga.setSalario(vagaAtualizada.getSalario());
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
}

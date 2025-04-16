package com.worklink.todosimple.vaga.controller;

import com.worklink.todosimple.vaga.model.Vaga;
import com.worklink.todosimple.vaga.repository.VagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vagas")
public class VagaController {

    @Autowired
    private VagaRepository vagaRepository;

    // GET todas as vagas
    @GetMapping
    public List<Vaga> getAllVagas() {
        return vagaRepository.findAll();
    }

    // GET vaga por ID
    @GetMapping("/{id}")
    public Optional<Vaga> getVagaById(@PathVariable Long id) {
        return vagaRepository.findById(id);
    }

    // POST criar vaga
    @PostMapping
    public Vaga createVaga(@RequestBody Vaga vaga) {
        return vagaRepository.save(vaga);
    }

    // PUT atualizar vaga
    @PutMapping("/{id}")
    public Vaga updateVaga(@PathVariable Long id, @RequestBody Vaga vagaAtualizada) {
        return vagaRepository.findById(id)
            .map(vaga -> {
                vaga.setTitulo(vagaAtualizada.getTitulo());
                vaga.setDescricao(vagaAtualizada.getDescricao());
                vaga.setRequisitos(vagaAtualizada.getRequisitos());
                vaga.setSalario(vagaAtualizada.getSalario());
                return vagaRepository.save(vaga);
            }).orElseGet(() -> {
                vagaAtualizada.setId(id);
                return vagaRepository.save(vagaAtualizada);
            });
    }

    // DELETE vaga
    @DeleteMapping("/{id}")
    public void deleteVaga(@PathVariable Long id) {
        vagaRepository.deleteById(id);
    }
}


package com.worklink.todosimple.cadastro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.worklink.todosimple.cadastro.models.Candidato;

@Repository
public interface CandidatoRepository extends JpaRepository<Candidato, Integer> {
    Candidato findByEmail(String email);
}
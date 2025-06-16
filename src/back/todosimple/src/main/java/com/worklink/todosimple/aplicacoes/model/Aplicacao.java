package com.worklink.todosimple.aplicacoes.model;

import jakarta.persistence.*;
import java.io.Serializable;

import com.worklink.todosimple.cadastro.models.Candidato;
import com.worklink.todosimple.vaga.model.Vaga;

@Entity
@Table(name = "aplicacao")
public class Aplicacao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "status", length = 20, nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "id_vaga", nullable = false)
    private Vaga vaga;

    @ManyToOne
    @JoinColumn(name = "cpf_usuario", referencedColumnName = "cpf", nullable = false)
    private Candidato candidato;

    @Column(name = "teste_resposta")
    private String testeResposta; // Caminho ou URL do arquivo enviado

    @Column(name = "feedback", columnDefinition = "TEXT")
    private String feedback;

    // Getters e Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Vaga getVaga() {
        return vaga;
    }

    public void setVaga(Vaga vaga) {
        this.vaga = vaga;
    }

    public Candidato getCandidato() {
        return candidato;
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }

    public String getTesteResposta() {
        return testeResposta;
    }

    public void setTesteResposta(String testeResposta) {
        this.testeResposta = testeResposta;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
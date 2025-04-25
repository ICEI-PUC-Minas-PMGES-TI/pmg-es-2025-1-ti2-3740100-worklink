package com.worklink.todosimple.cadastro.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "candidato")
public class Candidato extends Usuario {

    @NotNull
    @Column(name = "cpf", length = 11, nullable = false, unique = true)
    private String cpf;

    @NotNull
    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNasc;

    @NotNull
    @Column(name = "area_atuacao", length = 100, nullable = false)
    private String areaAtuacao;

    @NotNull
    @Column(name = "sexo", length = 10, nullable = false)
    private String sexo;

    @NotNull
    @Column(name = "cep", length = 8, nullable = false)
    private String cep;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(LocalDate dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getAreaAtuacao() {
        return areaAtuacao;
    }

    public void setAreaAtuacao(String areaAtuacao) {
        this.areaAtuacao = areaAtuacao;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}
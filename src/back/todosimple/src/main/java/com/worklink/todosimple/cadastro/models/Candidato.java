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
    @Column(name = "formacao", length = 100, nullable = false)
    private String formacao;

    @NotNull
    @Column(name = "experiencia", nullable = false)
    private Integer experiencia;

    @NotNull
    @Column(name = "habilidades", length = 500, nullable = false)
    private String habilidades;

    @Column(name = "sobre", length = 1000)
    private String sobre;

    @Column(name = "links", length = 1000)
    private String links; // Pode ser uma string separada por vírgula ou JSON

    @Column(name = "idiomas", length = 1000)
    private String idiomas; // Armazene como JSON string

    @Column(name = "disponibilidade", length = 100)
    private String disponibilidade;

    @Column(name = "experiencias", columnDefinition = "TEXT")
    private String experiencias; // Armazena um array JSON

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

    public String getFormacao() {
        return formacao;
    }

    public void setFormacao(String formacao) {
        this.formacao = formacao;
    }

    public Integer getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(Integer experiencia) {
        this.experiencia = experiencia;
    }

    public String getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(String habilidades) {
        this.habilidades = habilidades;
    }

    public String getSobre() {
        return sobre;
    }

    public void setSobre(String sobre) {
        this.sobre = sobre;
    }

    public String getLinks() {
        return links;
    }

    public void setLinks(String links) {
        this.links = links;
    }

    public String getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

    public String getDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(String disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    public String getExperiencias() {
        return experiencias;
    }

    public void setExperiencias(String experiencias) {
        this.experiencias = experiencias;
    }
}
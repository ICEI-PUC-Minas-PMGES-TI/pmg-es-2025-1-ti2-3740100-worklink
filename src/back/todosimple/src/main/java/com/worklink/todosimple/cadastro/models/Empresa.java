package com.worklink.todosimple.cadastro.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "empresa")
public class Empresa extends Usuario {

    @NotNull
    @Column(name = "cnpj", length = 14, nullable = false, unique = true)
    private String cnpj;

    @Column(name = "descricao", length = 500)
    private String descricao;

    @Column(name = "nome_responsavel", length = 100)
    private String nomeResponsavel;

    @Column(name = "cargo_responsavel", length = 50)
    private String cargo;

    @Min(0)
    @Max(5)
    @Column(name = "avaliacao", nullable = true)
    private Double avaliacao;

    @Column(name = "setor", length = 100)
    private String setor; // Novo campo para o setor da empresa

    // Getters e Setters
    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public String getNomeResponsavel() {
        return nomeResponsavel;
    }

    public void setNomeResponsavel(String nomeResponsavel) {
        this.nomeResponsavel = nomeResponsavel;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj.replaceAll("\\D", "");
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Double avaliacao) {
        if (avaliacao != null) {
            this.avaliacao = Math.max(0, Math.min(avaliacao, 5));
        } else {
            this.avaliacao = null;
        }
    }
}
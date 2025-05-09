package com.worklink.todosimple.vaga.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import java.util.*;
import com.worklink.todosimple.cadastro.models.Empresa;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Vaga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private long id;
    private String titulo;
    private String descricao;
    private String beneficios;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataFinal;
    private String tipoContrato; //CLT|ESTAGIO|APRENDIZ|PJ|OUTROS|
    private String modalidade; //PRESENCIAL|REMOTO|HIBRIDO|
    private String teste; //Arquivo em pdf

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    private double salario;

    // Construtor padrão
    public Vaga() {}

    // Construtor completo
    public Vaga(Long id, String titulo, String descricao, String beneficios, double salario, Date dataFinal, String tipoContrato, String modalidade, String teste ) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.beneficios = beneficios;
        this.salario = salario;
        this.dataFinal = dataFinal;
        this.tipoContrato = tipoContrato;
        this.modalidade = modalidade;
        this.teste = teste;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getBeneficios() {
        return beneficios;
    }

    public void setBeneficios(String beneficios) {
        this.beneficios = beneficios;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public Date getDataFinal() {
        return dataFinal;
    }
    
    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }
    
    public String getTipoContrato() {
        return tipoContrato;
    }
    
    public void setTipoContrato(String tipoContrato) {
        this.tipoContrato = tipoContrato;
    }
    
    public String getModalidade() {
        return modalidade;
    }
    
    public void setModalidade(String modalidade) {
        this.modalidade = modalidade;
    }
    
    public String getTeste() {
        return teste;
    }
    
    public void setTeste(String teste) {
        this.teste = teste;
    }
    
    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
}

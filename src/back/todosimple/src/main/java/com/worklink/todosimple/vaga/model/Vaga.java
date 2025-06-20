package com.worklink.todosimple.vaga.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
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

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dataFinal;

    private String tipoContrato;
    private String modalidade;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    private double salario;

    @Column(name = "teste", length = 255)
    private String teste;

    @Column(name = "data_criacao", nullable = false, updatable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dataCriacao;

    @Column(name = "status", length = 20, nullable = false)
    private String status = "Aberta"; // Novo atributo, padrão "Aberta"

    // Construtor padrão
    public Vaga() {
        // Ao criar uma nova vaga, define a data de criação como hoje (sem hora)
        this.dataCriacao = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    // Construtor completo
    public Vaga(Long id, String titulo, String descricao, String beneficios, double salario, Date dataFinal, String tipoContrato, String modalidade, String teste, Date dataCriacao) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.beneficios = beneficios;
        this.salario = salario;
        this.dataFinal = dataFinal;
        this.tipoContrato = tipoContrato;
        this.modalidade = modalidade;
        this.teste = teste;
        this.dataCriacao = dataCriacao != null ? dataCriacao : Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
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
    
    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public String getTeste() {
        return teste;
    }

    public void setTeste(String teste) {
        this.teste = teste;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}

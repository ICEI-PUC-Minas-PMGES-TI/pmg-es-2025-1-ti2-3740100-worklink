package com.worklink.todosimple.vaga.model;

import jakarta.persistence.Entity;
import jakarta.persistence.id;

@Entity
public class Vaga {
    
    @Id
    private String id;
    
    private String titulo;
    private String descricao;
    private String requisitos;
    private double salario;

    public Vaga(String id,String titulo, String descricao, String requisitos, double salario){
            this.id = id;
            this.titulo = titulo;
            this.descricao = descricao;
            this.requisitos = requisitos;
            this.salario = salario;
    }

    //Getter e Setter

    public String getId() {
        return id;
    }
    public void setId(String id) {
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

    public String getRequisitos() {
        return requisitos;
    }
    public void setRequisitos(String requisitos) {
        this.requisitos = requisitos;
    }
    
    public double getSalario() {
        return salario;
    }
    public void setSalario(double salario) {
        this.salario = salario;
    }

}

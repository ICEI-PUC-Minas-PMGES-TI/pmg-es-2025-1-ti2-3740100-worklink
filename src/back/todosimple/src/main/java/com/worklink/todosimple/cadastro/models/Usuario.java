package com.worklink.todosimple.cadastro.models;

import jakarta.validation.constraints.NotNull;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Table;


@Entity
@Table(name = Usuario.TABLE_NAME)
public class Usuario {
    public static final String TABLE_NAME = "usuario";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "idusuario")
    private Integer id;

    @NotNull
    @Column(name = "emailusuario", length = 50, nullable = false)
    private String email;

    @NotNull
    @Column(name = "senhausuario", length = 20, nullable = false)
    private String senha;

    @Column(name = "tipousuario")
    private Integer tipo;


    public Usuario(){}

    public Usuario(Integer id, String email, String senha, Integer tipo){
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.tipo = tipo;
    }

    public Integer getId(){
        return this.id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getEmail(){
        return this.email;
    }

    public void setSenha(String senha){
        this.senha = senha;
    }

    public String getSenha(){
        return this.senha;
    }

    public void setTipo(Integer tipo){
        this.tipo = tipo;
    }

    public Integer getTipo(){
        return this.tipo;
    }
} 
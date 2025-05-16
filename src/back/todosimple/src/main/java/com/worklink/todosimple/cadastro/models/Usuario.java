package com.worklink.todosimple.cadastro.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@MappedSuperclass
public abstract class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "idusuario")
    private Integer id;

    @NotNull
    @Column(name = "nomeusuario", length = 100, nullable = false)
    private String nome;

    @NotNull
    @Column(name = "emailusuario", length = 50, nullable = false)
    private String email;

    @NotNull
    @Column(name = "senhausuario", length = 20, nullable = false)
    private String senha;

    @NotNull
    @Column(name = "telefoneusuario", length = 15, nullable = false)
    private String telefone;

    @NotNull
    @Column(name = "enderecousuario", length = 255, nullable = false)
    private String endereco;

    @NotNull
    @Column(name = "cepusuario", length = 8, nullable = true)
    private String cep;

    @NotNull
    @Column(name = "cidadeusuario", length = 100, nullable = false)
    private String cidade;

    @NotNull
    @Column(name = "numerousuario", length = 10, nullable = false)
    private String numero;

    @Column(name = "foto_perfil", length = 255)
    private String fotoPerfil; // Caminho ou URL da imagem de perfil

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }
}
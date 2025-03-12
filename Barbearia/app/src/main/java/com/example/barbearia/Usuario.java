package com.example.barbearia;

public class Usuario {
    private String nome;
    private int idade;
    private String email;

    public Usuario(String nome, int idade, String email) {
        setNome(nome);
        setIdade(idade);
        setEmail(email);
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return this.idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }




}
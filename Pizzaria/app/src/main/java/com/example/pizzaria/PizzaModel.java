package com.example.pizzaria;

public class PizzaModel {
    String nome;
    String ingredientes;
    double valor;
    int imagem;
    int quantidade;

    public PizzaModel(String nome, String ingredientes, double valor, int imagem) {
        setNome(nome);
        setIngredientes(ingredientes);
        setValor(valor);
        setImagem(imagem);
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIngredientes() {
        return this.ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public double getValor() {
        return this.valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getImagem() {
        return this.imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

}

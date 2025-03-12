package com.example.barbearia;

public class Opcao {
    String titulo;
    double preco;
    String descricao;
    int imagem;

    public Opcao(String titulo, double preco, String descricao, int imagem) {
        setTitulo(titulo);
        setPreco(preco);
        setDescricao(descricao);
        setImagem(imagem);
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }

    public int getImagem() {
        return this.imagem;
    }



}

package com.example.todolist;

public class Tarefa {
    private String nome;
    private boolean completa;

    public Tarefa(String nome, boolean completa) {
        this.nome = nome;
        this.completa = completa;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isCompleta() {
        return this.completa;
    }

    public void setCompleta(boolean completa) {
        this.completa = completa;
    }


}

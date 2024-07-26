package br.com.dio.desafio.dominio;

import java.util.ArrayList;
import java.util.List;

public abstract class Conteudo {

    public List<Conteudo> conteudos = new ArrayList<>();

    protected static final double XP_PADRAO = 10d;

    private String titulo;
    private String descricao;

    public abstract double calcularXp();

    public List<Conteudo> getAllConteudos() {
        return conteudos;
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
}

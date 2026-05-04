package com.weg.biblioteca.model;

import java.time.LocalDate;

public class Livro {
    private long id;
    private String titulo;
    private String autor;
    private LocalDate anoPublicacao;
    public Livro( String titulo, String autor, LocalDate anoPublicacao) {
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
    }
    public Livro(long id, String titulo, String autor, LocalDate anoPublicacao) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }
    public LocalDate getAnoPublicacao() {
        return anoPublicacao;
    }
    public void setAnoPublicacao(LocalDate anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    
}

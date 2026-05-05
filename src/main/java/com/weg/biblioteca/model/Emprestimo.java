package com.weg.biblioteca.model;

import java.time.LocalDate;


public class Emprestimo {

    private long id;
    private long livroId;
    private long usuarioId;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;
    public Emprestimo(long id, long livroId, long usuarioId, LocalDate dataEmprestimo, LocalDate dataDevolucao) {
        this.id = id;
        this.livroId = livroId;
        this.usuarioId = usuarioId;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
    }
    public Emprestimo(long livroId, long usuarioId, LocalDate dataEmprestimo, LocalDate dataDevolucao) {
        this.livroId = livroId;
        this.usuarioId = usuarioId;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
    }

    

    
    public Emprestimo(long id, long livroId, long usuarioId, LocalDate dataEmprestimo) {
        this.id = id;
        this.livroId = livroId;
        this.usuarioId = usuarioId;
        this.dataEmprestimo = dataEmprestimo;
    }

    
    public Emprestimo(long livroId, long usuarioId, LocalDate dataEmprestimo) {
        this.livroId = livroId;
        this.usuarioId = usuarioId;
        this.dataEmprestimo = dataEmprestimo;
    }
    public Emprestimo() {
    }

    

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public long getLivroId() {
        return livroId;
    }
    public void setLivroId(long livroId) {
        this.livroId = livroId;
    }
    public long getUsuarioId() {
        return usuarioId;
    }
    public void setUsuarioId(long usuarioId) {
        this.usuarioId = usuarioId;
    }
    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }
    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }
    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }
    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    
}

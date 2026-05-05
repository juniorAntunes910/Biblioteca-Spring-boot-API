package com.weg.biblioteca.service;

import java.sql.SQLException;
import java.util.List;

import com.weg.biblioteca.model.Livro;

public interface LivroService {

    public Livro salvar(Livro livro) throws SQLException;

    public List<Livro> buscarTodos() throws SQLException;

    public Livro buscarPorID(long id) throws SQLException;

    public void atualizar(Livro livro, long id) throws SQLException;

    public void deletar(long id) throws SQLException;

}

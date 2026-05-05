package com.weg.biblioteca.service;

import java.sql.SQLException;
import java.util.List;

import com.weg.biblioteca.model.Emprestimo;

public interface EmprestimoService {

    public Emprestimo salvar(Emprestimo emprestimo) throws SQLException;

    public List<Emprestimo> buscarTodos() throws SQLException;

    public Emprestimo buscarPorID(long id) throws SQLException;

    public void atualizar(Emprestimo emprestimo, long id) throws SQLException;

    public void deletar(long id) throws SQLException;

}

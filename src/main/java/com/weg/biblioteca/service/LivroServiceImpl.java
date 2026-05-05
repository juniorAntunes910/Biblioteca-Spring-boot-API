package com.weg.biblioteca.service;

import java.sql.SQLException;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.stereotype.Service;

import com.weg.biblioteca.dao.LivroDao;
import com.weg.biblioteca.model.Livro;


@Service
public class LivroServiceImpl implements LivroService {
    private LivroDao livroDao;

    public LivroServiceImpl(LivroDao livroDao) {
        this.livroDao = livroDao;
    }

    @Override
    public Livro salvar(Livro livro) throws SQLException {
        if (livro.getTitulo() == null) {
            throw new RuntimeErrorException(null, "O título não pode ser nulo");
        }
        if (livro.getAutor() == null) {
            throw new RuntimeErrorException(null, "O Autor não pode ser nulo");
        }

        livro = livroDao.salvar(livro);
        return livro;
    }

    @Override
    public List<Livro> buscarTodos() throws SQLException {
        List<Livro> listLivros = livroDao.buscarTodos();
        if (listLivros == null) {
            throw new RuntimeErrorException(null, "A lista esta vazia");
        }
        return listLivros;
    }

    @Override
    public Livro buscarPorID(long id) throws SQLException {
        Livro livro = livroDao.buscarPorID(id);
        if (livro == null) {
            throw new RuntimeErrorException(null, "O livro não existe!");
        }
        return livro;
    }

    @Override
    public void atualizar(Livro livro, long id) throws SQLException {
        if (livroDao.buscarPorID(id) == null) {
            throw new RuntimeErrorException(null, "O livro não existe!");
        }
        livroDao.atualizar(livro, id);
    }

    @Override
    public void deletar(long id) throws SQLException {
        if (livroDao.buscarPorID(id) == null) {
            throw new RuntimeErrorException(null, "O livro não existe!");
        }
        livroDao.deletar(id);
    }

}

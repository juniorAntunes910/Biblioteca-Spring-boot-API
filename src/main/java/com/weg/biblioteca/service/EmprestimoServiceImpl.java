package com.weg.biblioteca.service;

import java.sql.SQLException;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.stereotype.Service;

import com.weg.biblioteca.dao.EmprestimoDao;
import com.weg.biblioteca.dao.LivroDao;
import com.weg.biblioteca.dao.UsuarioDao;
import com.weg.biblioteca.model.Emprestimo;

@Service
public class EmprestimoServiceImpl implements EmprestimoService {
    private LivroDao livroDao;
    private EmprestimoDao emprestimoDao;
    private UsuarioDao usuarioDao;



    public EmprestimoServiceImpl(LivroDao livroDao, EmprestimoDao emprestimoDao, UsuarioDao usuarioDao) {
        this.livroDao = livroDao;
        this.emprestimoDao = emprestimoDao;
        this.usuarioDao = usuarioDao;
    }

    @Override
    public Emprestimo salvar(Emprestimo emprestimo) throws SQLException {
        if(usuarioDao.buscarPorID(emprestimo.getUsuarioId()) == null){
            throw new RuntimeErrorException(null, "O usuário não existe");
        }
        if(livroDao.buscarPorID(emprestimo.getLivroId()) == null){
            throw new RuntimeErrorException(null, "O livro não existe");
        }
   
        if(emprestimo.getDataEmprestimo() == null){
            throw new RuntimeErrorException(null, "A data de Emprestimo esta vazia");
        }
        emprestimo = emprestimoDao.salvar(emprestimo);
        return emprestimo;
    }

    @Override
    public List<Emprestimo> buscarTodos() throws SQLException {
        List<Emprestimo> lEmprestimos = emprestimoDao.buscarTodos();
        if(lEmprestimos == null ){
            throw new RuntimeErrorException(null, "A lista de emprestimos esta vazia");
        }
        return lEmprestimos;
    }

    @Override
    public Emprestimo buscarPorID(long id) throws SQLException {
        Emprestimo emprestimo = emprestimoDao.buscarPorID(id);
        if(emprestimo == null){
            throw new RuntimeErrorException(null, "O emprestimo não existe");
        }
        return emprestimo;
    }

    @Override
    public void atualizar(Emprestimo emprestimo, long id) throws SQLException {
        if(emprestimoDao.buscarPorID(id) == null){
            throw new RuntimeErrorException(null, "O emprestimo não existe");
        }
        emprestimoDao.atualizar(emprestimo, id);
    }

    @Override
    public void deletar(long id) throws SQLException {
        if(emprestimoDao.buscarPorID(id) == null){
            throw new RuntimeErrorException(null, "O emprestimo não existe");
        }
        emprestimoDao.deletar(id);
    }

    @Override
    public void devolver(long id) throws SQLException {
        if(emprestimoDao.buscarPorID(id) == null){
            throw new RuntimeErrorException(null, "O emprestimo não existe");
        }
        emprestimoDao.deletar(id);
    }
}

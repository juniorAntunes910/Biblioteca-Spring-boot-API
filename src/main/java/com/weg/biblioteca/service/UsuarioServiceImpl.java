package com.weg.biblioteca.service;

import java.sql.SQLException;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.stereotype.Service;

import com.weg.biblioteca.dao.UsuarioDao;
import com.weg.biblioteca.model.Usuario;


@Service
public class UsuarioServiceImpl implements UsuarioService{
    private UsuarioDao usuarioDao;

    public UsuarioServiceImpl(UsuarioDao usuarioDao){
        this.usuarioDao = usuarioDao;
    }

    @Override
    public Usuario salvar(Usuario usuario) throws SQLException {
        if(usuario.getNome() == null){
            throw new RuntimeErrorException(null, "O nome não pode ser nulo");
        }
        if(usuario.getEmail() == null){
            throw new RuntimeErrorException(null, "O email não pode ser nulo");
        }
        usuario = usuarioDao.salvar(usuario);
        return usuario;
    }

    @Override
    public List<Usuario> buscarTodos() throws SQLException {
        List<Usuario> lUsuarios = usuarioDao.buscarTodos();
        if(lUsuarios == null){
            throw new RuntimeErrorException(null, "A lista esta vazia");
        }
        return lUsuarios;
    }

    @Override
    public Usuario buscarPorID(long id) throws SQLException {
        if(usuarioDao.buscarPorID(id) == null){
            throw new RuntimeErrorException(null, "O usuário não existe");
        }
        Usuario usuario = usuarioDao.buscarPorID(id);
        return usuario;
    }

    @Override
    public void atualizar(Usuario usuario, long id) throws SQLException {
        if(usuarioDao.buscarPorID(id) == null){
            throw new RuntimeErrorException(null, "O usuário não existe");
        }
        usuarioDao.atualizar(usuario, id);
    }

    @Override
    public void deletar(long id) throws SQLException {
        if(usuarioDao.buscarPorID(id) == null){
            throw new RuntimeErrorException(null, "O usuário não existe");
        }
        usuarioDao.deletar(id);
    }

}

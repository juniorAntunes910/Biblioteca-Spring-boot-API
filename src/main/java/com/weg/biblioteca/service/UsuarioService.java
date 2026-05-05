package com.weg.biblioteca.service;

import java.sql.SQLException;
import java.util.List;

import com.weg.biblioteca.model.Usuario;

public interface UsuarioService {

    
    public Usuario salvar(Usuario usuario) throws SQLException;

    public List<Usuario> buscarTodos() throws SQLException;

    public Usuario buscarPorID(long id) throws SQLException;

    public void atualizar(Usuario usuario, long id) throws SQLException;

    public void deletar(long id) throws SQLException;


}

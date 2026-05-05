package com.weg.biblioteca.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.weg.biblioteca.infra.ConnectionFactory;
import com.weg.biblioteca.model.Usuario;

@Repository
public class UsuarioDaoImpl implements UsuarioDao {
    private ConnectionFactory connectionFactory;

    public UsuarioDaoImpl(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public Usuario salvar(Usuario usuario) throws SQLException {
        String command = """
                INSERT INTO usuario
                (nome,
                email)
                VALUES
                (?,?)
                """;
        try (Connection conn = connectionFactory.conexao();
                PreparedStatement stmt = conn.prepareStatement(command, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                usuario.setId(rs.getLong(1));
            }
            return usuario;
        }
    }

    @Override
    public List<Usuario> buscarTodos() throws SQLException {
        String command = """
                SELECT id,
                nome,
                email
                FROM usuario
                """;
        try (Connection conn = connectionFactory.conexao();
                PreparedStatement stmt = conn.prepareStatement(command)) {
            ResultSet rs = stmt.executeQuery();
            List<Usuario> listUsuarios = new ArrayList<>();
            while (rs.next()) {
                listUsuarios.add(
                        new Usuario(
                                rs.getLong("id"),
                                rs.getString("nome"),
                                rs.getString("email")));
            }
            return listUsuarios;
        }
    }

    @Override
    public Usuario buscarPorID(long id) throws SQLException {
        String command = """
                SELECT 
                nome,
                email
                FROM usuario
                WHERE id = ?
                """;
        try (Connection conn = connectionFactory.conexao();
                PreparedStatement stmt = conn.prepareStatement(command)) {
                    stmt.setLong(1, id);
                    ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Usuario(
                        id,
                        rs.getString("nome"),
                        rs.getString("email"));
            }
        }
        return null;
    }

    @Override
    public void atualizar(Usuario usuario, long id) throws SQLException {
        String command = """
                UPDATE usuario 
                SET nome = ?,
                email = ?
                WHERE id = ?
                """;
                try(Connection conn = connectionFactory.conexao();
            PreparedStatement stmt = conn.prepareStatement(command)){
                stmt.setString(1, usuario.getNome());
                stmt.setString(2, usuario.getEmail());
                stmt.setLong(3, id);
                stmt.executeUpdate();
            }
    }

    @Override
    public void deletar(long id) throws SQLException {
        String command = """
                DELETE FROM usuario
                WHERE id = ?
                """;
                try(Connection conn = connectionFactory.conexao();
            PreparedStatement stmt = conn.prepareStatement(command)){
                stmt.setLong(1, id);
                stmt.executeUpdate();
            }
    }

}

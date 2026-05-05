package com.weg.biblioteca.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.weg.biblioteca.infra.ConnectionFactory;
import com.weg.biblioteca.model.Emprestimo;

@Repository
public class EmprestimoDaoImpl implements EmprestimoDao {
    private ConnectionFactory connectionFactory;

    public EmprestimoDaoImpl(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public Emprestimo salvar(Emprestimo emprestimo) throws SQLException {
        String command = """
                            INSERT INTO emprestimo
                            (
                            livro_id,
                            usuario_id,
                            data_emprestimo
                            )
                            VALUES
                            (?,?,?)
                            """;
        try (Connection conn = connectionFactory.conexao();
                PreparedStatement stmt = conn.prepareStatement(command, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, emprestimo.getLivroId());
            stmt.setLong(2, emprestimo.getUsuarioId());
            stmt.setObject(3, LocalDate.now());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                emprestimo.setId(rs.getLong(1));
            }
            return emprestimo;
        }
    }

    @Override
    public List<Emprestimo> buscarTodos() throws SQLException {
        String command = """
                SELECT
                id,
                livro_id,
                usuario_id,
                data_emprestimo
                FROM emprestimo
                """;
        try (Connection conn = connectionFactory.conexao();
                PreparedStatement stmt = conn.prepareStatement(command)) {
            ResultSet rs = stmt.executeQuery();
            List<Emprestimo> lEmprestimos = new ArrayList<>();
            while (rs.next()) {
                lEmprestimos.add(
                        new Emprestimo(rs.getLong("id"),
                                rs.getLong("livro_id"),
                                rs.getLong("usuario_id"),
                                rs.getObject("data_emprestimo", LocalDate.class)));
            }
            return lEmprestimos;
        }
    }

    @Override
    public Emprestimo buscarPorID(long id) throws SQLException {
        String command = """
                SELECT
                livro_id,
                usuario_id,
                data_emprestimo,
                data_devolucao
                FROM emprestimo
                WHERE id = ?
                """;
        try (Connection conn = connectionFactory.conexao();
                PreparedStatement stmt = conn.prepareStatement(command)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Emprestimo(
                        id,
                        rs.getLong("livro_id"),
                        rs.getLong("usuario_id"),
                        rs.getObject("data_emprestimo", LocalDate.class),
                        rs.getObject("data_devolucao", LocalDate.class));
            }
        }
        return null;
    }

    @Override
    public void atualizar(Emprestimo emprestimo, long id) throws SQLException {
        String command = """
                UPDATE emprestimo
                SET livro_id = ?,
                usuario_id = ?,
                data_emprestimo = ?,
                data_devolucao = ?
                WHERE id = ?
                """;
        try (Connection conn = connectionFactory.conexao();
                PreparedStatement stmt = conn.prepareStatement(command)) {
            stmt.setLong(1, emprestimo.getLivroId());
            stmt.setLong(2, emprestimo.getUsuarioId());
            stmt.setObject(3, emprestimo.getDataEmprestimo());
            stmt.setObject(4, emprestimo.getDataDevolucao());
            stmt.setLong(5, id);
            stmt.executeUpdate();
        }

    }

    @Override
    public void deletar(long id) throws SQLException {
        String command = """
                DELETE FROM emprestimo
                WHERE id = ?
                """;
        try (Connection conn = connectionFactory.conexao();
                PreparedStatement stmt = conn.prepareStatement(command)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public Emprestimo buscarPorLivro(long livroID) throws SQLException {
        String command = """
                SELECT
                id
                livro_id,
                usuario_id,
                data_emprestimo,
                data_devolucao
                FROM emprestimo
                WHERE livro_id = ?
                """;
        try (Connection conn = connectionFactory.conexao();
                PreparedStatement stmt = conn.prepareStatement(command)) {
            stmt.setLong(1, livroID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Emprestimo(
                        rs.getLong("id"),
                        rs.getLong("livro_id"),
                        rs.getLong("usuario_id"),
                        rs.getObject("data_emprestimo", LocalDate.class),
                        rs.getObject("data_devolucao", LocalDate.class));
            }
        }
        return null;
    }

}

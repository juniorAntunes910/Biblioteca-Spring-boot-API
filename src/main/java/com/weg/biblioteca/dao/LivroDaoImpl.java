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
import com.weg.biblioteca.model.Livro;

@Repository
public class LivroDaoImpl {
    private ConnectionFactory connectionFactory;

    public LivroDaoImpl(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public Livro salvar(Livro livro) {
        String command = """
                INSERT INTO livros
                (titulo, autor, ano_publicacao)
                VALUES
                (?,?,?)
                """;
        try (Connection conn = connectionFactory.conexao();
                PreparedStatement stmt = conn.prepareStatement(command, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setObject(3, livro.getAnoPublicacao());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                livro.setId(rs.getLong(1));
            }
            return livro;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Livro> buscarTodos() {
        String command = """
                SELECT id,
                    titulo,
                    autor,
                    ano_publicacao
                FROM livro
                """;
        try (Connection conn = connectionFactory.conexao();
                PreparedStatement stmt = conn.prepareStatement(command)) {
            List<Livro> listLivros = new ArrayList<>();
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                listLivros.add(
                        new Livro(
                                rs.getLong("id"),
                                rs.getString("titulo"),
                                rs.getString("autor"),
                                rs.getObject("ano_publicacao", LocalDate.class)));
                return listLivros;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public Livro buscarPorID(long id) {
        String command = """
                SELECT id,
                    titulo,
                    autor,
                    ano_publicacao
                FROM livro
                WHERE id = ?
                """;
        try (Connection conn = connectionFactory.conexao();
                PreparedStatement stmt = conn.prepareStatement(command)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                return new Livro(
                        rs.getLong("id"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getObject("ano_publicacao", LocalDate.class));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public void atualizar(Livro livro, long id) {
        String command = """
                UPDATE livro
                SET titulo = ?,
                autor = ?,
                ano_publicacao = ?
                WHERE id = ?
                """;
        try (Connection conn = connectionFactory.conexao();
                PreparedStatement stmt = conn.prepareStatement(command)) {
            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setObject(3, livro.getAnoPublicacao());
            stmt.setLong(4, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void deletar(long id){
        String command = """
                DELETE FROM livro
                WHERE id = ?
                """;
                           try(Connection conn = connectionFactory.conexao();
            PreparedStatement stmt = conn.prepareStatement(command)){
                stmt.setLong(1, id);
                stmt.executeUpdate();
                      } catch(SQLException e){
                System.out.println(e);
            }
    }
    
}

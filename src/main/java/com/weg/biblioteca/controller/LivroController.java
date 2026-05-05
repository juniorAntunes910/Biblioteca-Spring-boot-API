package com.weg.biblioteca.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weg.biblioteca.model.Livro;
import com.weg.biblioteca.service.LivroService;

@RestController
@RequestMapping("/livros")
public class LivroController {

    private LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @PostMapping()
    public String salvar(@RequestBody Livro livro) {
        try {
            livroService.salvar(livro);
            return "Livro salvo com sucesso";
        } catch (SQLException e) {
            System.out.println("Erro ao salvar ao banco de dados " + e);
        }
        return "Internal Server Error";
    }

    @GetMapping()
    public List<Livro> buscarTodos() {
        try {
            return livroService.buscarTodos();
        } catch (SQLException e) {
            System.out.println("Erro ao buscar ao banco de dados " + e);
        }
        return null;
    }

    @GetMapping("/{id}")
    public Livro buscarPorId(@PathVariable long id) {
        try {
            return livroService.buscarPorID(id);
        } catch (SQLException e) {
            System.out.println("Erro ao buscar ao banco de dados " + e);
        }
        return null;
    }

    @PutMapping("/{id}")
    public String atualizar(@PathVariable long id, @RequestBody Livro livro) {
        try {
            livroService.atualizar(livro, id);
            return "Atualizado com sucesso!";
        } catch (SQLException e) {
            return "Erro ao atualizar no banco de dados " + e;
        }
    }

    @DeleteMapping("/{id}")
    public String deletar(@PathVariable long id) {
        try {
            livroService.deletar(id);
            return "Deletado com sucesso!";
        } catch (SQLException e) {
            return "Erro ao Deletar no banco de dados " + e;
        }
    }

}

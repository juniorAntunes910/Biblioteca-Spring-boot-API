package com.weg.biblioteca.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weg.biblioteca.model.Emprestimo;
import com.weg.biblioteca.service.EmprestimoService;

import java.sql.SQLException;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {
    private EmprestimoService emprestimoService;

    public EmprestimoController(EmprestimoService emprestimoService) {
        this.emprestimoService = emprestimoService;
    }

    @PostMapping()
    public String salvar(@RequestBody Emprestimo emprestimo) {
        try{
            emprestimoService.salvar(emprestimo);
            return "Emprestimo salvo com sucesso";
        }catch(SQLException e){
            System.out.println("Erro ao salvar ao banco de dados " + e);
        }
        return "Internal Server Error";
    }

    @GetMapping()
    public List<Emprestimo> buscarTodos() {
        try{
            return emprestimoService.buscarTodos();
        }catch(SQLException e){
            System.out.println("Erro ao buscar ao banco de dados " + e);
        }
        return null;
    }

    @GetMapping("/{id}")
    public Emprestimo buscarPorId(@PathVariable long id) {
        try {
            return emprestimoService.buscarPorID(id);
        } catch (SQLException e) {
            System.out.println("Erro ao buscar ao banco de dados " + e);
        }
        return null;
    }

    @PutMapping("/{id}")
    public String atualizar(@PathVariable long id, @RequestBody Emprestimo emprestimo) {
        try{
            emprestimoService.atualizar(emprestimo, id);
            return "Atualizado com sucesso!";
        }catch (SQLException e){
            return "Erro ao atualizar no banco de dados";
        }
    }

    @DeleteMapping("/{id}")
    public String deletar(@PathVariable long id){
        try {
            emprestimoService.deletar(id);
            return "Deletado com sucesso!";
        } catch (SQLException e) {
            return "Erro ao Deletar no banco de dados";
        }
    }
    
    
    
}

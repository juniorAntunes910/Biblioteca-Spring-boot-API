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

import com.weg.biblioteca.model.Usuario;
import com.weg.biblioteca.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping()
    public String salvar(@RequestBody Usuario usuario) {
        try{
            usuarioService.salvar(usuario);
            return "Usuario salvo com sucesso";
        }catch(SQLException e){
            System.out.println("Erro ao salvar ao banco de dados " + e);
        }
        return "Internal Server Error";
    }

    @GetMapping()
    public List<Usuario> buscarTodos() {
        try{
            return usuarioService.buscarTodos();
        }catch(SQLException e){
            System.out.println("Erro ao buscar ao banco de dados " + e);
        }
        return null;
    }

    @GetMapping("/{id}")
    public Usuario buscarPorId(@PathVariable long id) {
        try {
            System.out.println("deu bom ");
            return usuarioService.buscarPorID(id);
        } catch (SQLException e) {
            System.out.println("Erro ao buscar ao banco de dados " + e);
        }
        System.out.println("Cheguei no null");
        return null;
    }

    @PutMapping("/{id}")
    public String atualizar(@PathVariable long id, @RequestBody Usuario usuario) {
        try{
            System.out.println("entrei");
            usuarioService.atualizar(usuario, id);
            return "Atualizado com sucesso!";
        }catch (SQLException e){
            return "Erro ao atualizar no banco de dados " + e;
        }
    }

    @DeleteMapping("/{id}")
    public String deletar(@PathVariable long id){
        try {
            usuarioService.deletar(id);
            return "Deletado com sucesso!";
        } catch (SQLException e) {
            return "Erro ao Deletar no banco de dados";
        }
    }
    

}

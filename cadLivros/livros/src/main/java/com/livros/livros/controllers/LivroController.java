package com.livros.livros.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.livros.livros.entities.Livro;
import com.livros.livros.services.LivroServices;


@RestController
@RequestMapping("/livro")

public class LivroController {
	private final LivroServices livroServices;

    public LivroController(LivroServices livroServices) {
        this.livroServices = livroServices;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> getLivro(@PathVariable Long id) {
        Livro livro = ((LivroServices) livroServices).getLivroById(id);
        if (livro != null) {
            return ResponseEntity.ok(livro);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/home")
    public String paginaInicial() {
        return "index"; // Nome do seu arquivo HTML (sem a extensão)
    }

    @PostMapping
    public Livro createLivro(@RequestBody Livro livro) {
        return livroServices.saveLivro(livro);
    }
    
  //Utilizando o ResponseEntity e RequestEntity
  	@GetMapping
  	public ResponseEntity<List<Livro>> getAllLivros(RequestEntity<Void> requestEntity) {
  		String method = requestEntity.getMethod().name();
  		String contentType = requestEntity.getHeaders().getContentType().toString();
  		List<Livro> livros = livroServices.getAllLivros();
  		return ResponseEntity.status(HttpStatus.OK).header("Method", method).header("Content-Type", contentType)
  				.body(livros);
  	}
  	
  	@PutMapping("/{id}")
  	public Livro updateLivro(@PathVariable Long id, @RequestBody Livro livro) {
  	    return livroServices.updateLivro(id, livro);
  	}
  	@DeleteMapping("/{id}")
    public void deleteLivro(@PathVariable Long id) {
        livroServices.deleteLivro(id);
    }
}

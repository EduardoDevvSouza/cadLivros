package com.livros.livros.services;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import com.livros.livros.entities.Livro;
import com.livros.livros.repositories.LivroRepository;

@Service
public class LivroServices {
 
 private final LivroRepository livroRepository;
 
 public LivroServices(LivroRepository livroRepository) {
    this.livroRepository = livroRepository;
 }
 
 public Livro getLivroById(Long id) {
        return livroRepository.findById(id).orElse(null);
    }
 
 public Livro saveLivro(Livro livro){
    return livroRepository.save(livro);
 } 
 
 public List<Livro> getAllLivros(){
    return livroRepository.findAll();
 }

    public void deleteLivro(Long id) {
        livroRepository.deleteById(id);
        
    }
 // fazendo o update do jogo com o optional
 	public Livro updateLivro(Long id, Livro novoLivro) {
         Optional<Livro> livroOptional = livroRepository.findById(id);
         if (livroOptional.isPresent()) {
         	Livro livroExistente = livroOptional.get();
            	livroExistente.setDescricao(novoLivro.getDescricao());
         	livroExistente.setIsbn(novoLivro.getIsbn());          
             return livroRepository.save(livroExistente); 
         } else {
             return null; 
         }
     }
    
 
 
 
 


}
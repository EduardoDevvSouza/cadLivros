package com.livros.livros.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.livros.livros.entities.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> {

}

package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
    Livro findByNomeLivroStartsWithIgnoreCase(String nomeLivro);
}

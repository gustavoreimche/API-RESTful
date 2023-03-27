package com.example.demo.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Estudante;
import com.example.demo.entity.Livro;
import com.example.demo.repository.EstudanteRepository;
import com.example.demo.repository.LivroRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EstudanteService {

    private EstudanteRepository estudanteRepository;
    private LivroRepository livroRepository;

    public ResponseEntity<Estudante> buscarEstudantePorId(Long id) {
        if (estudanteRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.OK).body(estudanteRepository.findById(id).get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    public List<Estudante> buscarEstudantePorNome(String nome) {
        return estudanteRepository.findByNomeStartsWithIgnoreCase(nome);
    }

    public ResponseEntity<List<Estudante>> buscarEstudantes() {
        
        return ResponseEntity.status(HttpStatus.OK).body(estudanteRepository.findAll());
    }

    public Page<Estudante> buscarEstudantesPage(PageRequest page) {
        return estudanteRepository.findAll(page);
    }

    public ResponseEntity<Estudante> cadastrarEstudante(Estudante estudante) {
        Set<Livro> livros = estudante.getLivros();
        estudante.setLivros(new HashSet<>());
        Estudante estudanteSalvo = estudanteRepository.save(estudante);
        
        for(Livro livro : livros){
            
            if((livroRepository.findByNomeLivroStartsWithIgnoreCase(livro.getNomeLivro())) == null){
                livro.setEstudante(Estudante.builder().id(estudanteSalvo.getId()).build());
                estudante.getLivros().add(livroRepository.save(livro));
            } else {
                Livro livroJaExistente = livroRepository.findByNomeLivroStartsWithIgnoreCase(livro.getNomeLivro());
                livroJaExistente.setEstudante(Estudante.builder().id(estudanteSalvo.getId()).build());
                estudante.getLivros().add(livroRepository.save(livroJaExistente));
            }     
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(estudanteRepository.save(estudanteSalvo));
    }

    public ResponseEntity<Estudante> atualizarEstudante(Long id, Estudante estudante) {
        if (estudanteRepository.existsById(id)) {
            estudante.setId(id);
            return ResponseEntity.status(HttpStatus.OK).body(estudanteRepository.save(estudante));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    public ResponseEntity<String> removerEstudante(Long id) {
        if (estudanteRepository.existsById(id)) {
            estudanteRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Estudante deletado com sucesso!");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estudante não encontrado!");
    }

}

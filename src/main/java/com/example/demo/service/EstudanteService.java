package com.example.demo.service;

import java.util.List;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Estudante;
import com.example.demo.repository.EstudanteRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EstudanteService {

    private EstudanteRepository estudanteRepository;

    public ResponseEntity<Estudante> buscarEstudantePorId (Long id) {
        if(estudanteRepository.existsById(id)){
            return ResponseEntity.status(HttpStatus.OK).body(estudanteRepository.findById(id).get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    public List<Estudante> buscarEstudantePorNome (String nome) {
        // return estudanteRepository.findByNome(nome);
        return estudanteRepository.findByNomeStartsWith(nome);
        
    }

    public List<Estudante> buscarEstudantes () {
        return estudanteRepository.findAll();
    }

    public ResponseEntity<Estudante> cadastrarEstudante (Estudante estudante) {
        return ResponseEntity.status(HttpStatus.CREATED).body(estudanteRepository.save(estudante));
    }

    public ResponseEntity<Estudante> atualizarEstudante (Long id, Estudante estudante) {
        if(estudanteRepository.existsById(id)){
            return ResponseEntity.status(HttpStatus.OK).body(estudanteRepository.save(estudante));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    public ResponseEntity<String> removerEstudante (Long id) {
        if(estudanteRepository.existsById(id)){
            estudanteRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Estudante deletado com sucesso!");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estudante não encontrado!");
    }

}

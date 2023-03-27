package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.AvaliacaoCursoService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/avaliacoes")
public class AvaliacaoCursoController {

    private final AvaliacaoCursoService avaliacaoCursoService;

    @PostMapping
    public ResponseEntity<String> avaliar(
            @RequestParam Long idEstudante,
            @RequestParam String nomeCurso,
            @RequestParam Integer notaAvaliacao) {
                return avaliacaoCursoService.avaliar(idEstudante, nomeCurso, notaAvaliacao);
    }

}

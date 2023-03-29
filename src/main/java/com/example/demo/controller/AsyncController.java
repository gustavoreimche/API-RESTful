package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.AsyncService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/asyncs")
@AllArgsConstructor
public class AsyncController {

    private AsyncService asyncService;

    @GetMapping
    public ResponseEntity<String> executarTarefaAsync() throws InterruptedException {
        asyncService.metodoAsync();
        return ResponseEntity.ok("Atividade iniciada com sucesso!");
    }
}

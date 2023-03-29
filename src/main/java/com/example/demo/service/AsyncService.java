package com.example.demo.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class AsyncService {

    NotificacaoService notificacaoService;

    @Async
    public void metodoAsync() throws InterruptedException {

        log.info("tarefa iniciada");
        Thread.sleep(5000);
        notificacaoService.publicar("tarefa finalizada");
    }
}

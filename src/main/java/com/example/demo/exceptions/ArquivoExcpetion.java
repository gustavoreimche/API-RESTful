package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Arquivo não enconrado")
public class ArquivoExcpetion extends RuntimeException {

    public ArquivoExcpetion(String message) {
        super(message);
    }
    
    public ArquivoExcpetion(String message, Throwable cause) {
        super(message, cause);
    }
}

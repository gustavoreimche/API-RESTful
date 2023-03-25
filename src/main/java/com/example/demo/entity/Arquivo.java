package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Arquivo {
    
    private String nome;
    private String urlDownload;
    private String tipoArquivo;
    private long size;

}

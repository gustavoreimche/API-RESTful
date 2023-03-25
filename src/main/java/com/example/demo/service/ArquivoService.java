package com.example.demo.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.config.ArquivoStorageProperties;
import com.example.demo.exceptions.ArquivoExcpetion;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ArquivoService {

    private final Path fileStorageLocation;

    public ArquivoService(ArquivoStorageProperties arquivoStorageProperties) {
        this.fileStorageLocation = Paths.get(arquivoStorageProperties.getUploadDir()).toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (IOException e) {
            throw new ArquivoExcpetion("Algo deu errado ao criar a pasta", e);
        }
    }

    public String salvarArquivo(MultipartFile file) {

        String nomeArquivo = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            if(nomeArquivo.contains("..")){
                throw new ArquivoExcpetion("Arquivo invalido");
            }
            Path targetLocation = this.fileStorageLocation.resolve(nomeArquivo);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return nomeArquivo;
        } catch (IOException e) {
            throw new ArquivoExcpetion("Erro ao salvar o arquivo");
        }

    }

    public Resource carregarArquivo(String nomeArquivo) {
        try {
            Path filePath = this.fileStorageLocation.resolve(nomeArquivo).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new ArquivoExcpetion("Arquivo não encontrado1");
            }
        } catch (Exception e) {
            throw new ArquivoExcpetion("Arquivo não encontrado2");
        }
    }

    public String getContentType(HttpServletRequest request, Resource resource) {
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (Exception e) {
            log.error("Não foi possivel determinar o tipo do arquivo");
        }

        if (contentType == null) {
            contentType = "application/octet-stream";
        }
        return contentType;
    }
}

package com.portfolio.my_portfolio_backend.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageService {
    
    @Value("${file.upload.dir}")
    private String uploadDir;

    public String storeFile(MultipartFile file) throws IOException {
        // Lógica para almacenar el archivo en el sistema de archivos
        // y devolver la URL o ruta del archivo almacenado
        if (file.isEmpty()) {
            throw new IOException("El archivo está vacío.");
        }

        String originalFilename = file.getOriginalFilename();
        String extension = "";
        if (originalFilename != null) {
            int dotIndex = originalFilename.lastIndexOf('.');
            if (dotIndex > 0) {
                extension = originalFilename.substring(dotIndex);
            }
        }

        String fileName = UUID.randomUUID().toString() + extension;
        
        // crear la ruta completa del archivo
        Path filePath = Paths.get(uploadDir, fileName).normalize();

        // copia del archivo al destino
        Files.copy(file.getInputStream(), filePath);

        // retorno de la URL relativa
        return "/img/projects/" + fileName;
    }
}

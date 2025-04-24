package com.example.demoapi.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;
import java.nio.charset.StandardCharsets;

@Service
public class S3Service {

    @Value("${aws.s3.bucket-name}")
    private String bucketName;

    private final S3Client s3Client;

    public S3Service(@Value("${aws.s3.bucket-name}") String bucketName, 
    S3Client s3Client) {
        this.bucketName = bucketName;
        this.s3Client = s3Client;
    }

    public String uploadTestFile() {
        String key = "test-file.txt";
        String content = "¡Hola S3! Este es un archivo de prueba.";

        try {
            // Subir archivo
            PutObjectRequest putRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();

            s3Client.putObject(putRequest, RequestBody.fromString(content));
            return "Archivo subido exitosamente a S3: " + key;

        } catch (S3Exception e) {
            return "Error al subir el archivo: " + e.getMessage();
        }
    }

    public String downloadTestFile() {
        String key = "test-file.txt";

        try {
            // Descargar archivo
            GetObjectRequest getRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();

            String content = s3Client.getObjectAsBytes(getRequest).asString(StandardCharsets.UTF_8);
            return "Contenido del archivo desde S3: " + content;

        } catch (S3Exception e) {
            return "Error al descargar el archivo: " + e.getMessage();
        }
    }
}
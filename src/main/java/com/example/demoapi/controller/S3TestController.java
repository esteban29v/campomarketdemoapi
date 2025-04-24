package com.example.demoapi.controller;

import com.example.demoapi.service.S3Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/s3")
public class S3TestController {

    private final S3Service s3Service;

    public S3TestController(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    @PostMapping("/upload-test")
    public String uploadTest() {
        return s3Service.uploadTestFile();
    }

    @GetMapping("/download-test")
    public String downloadTest() {
        return s3Service.downloadTestFile();
    }
}
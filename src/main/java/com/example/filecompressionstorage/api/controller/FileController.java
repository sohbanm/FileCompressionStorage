package com.example.filecompressionstorage.api.controller;

import com.amazonaws.HttpMethod;
import com.example.filecompressionstorage.api.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class FileController {

    @Autowired
    private FileService service;

    @GetMapping("/")
    public String homePage(){
        return "<h1> Welcome to my AWS S3 Compressed File Storage API. </h1> <h4> This Application's purpose is to store File's/Folders in S3 whihc are zipped in the Bucket and can also be retreived.</h4>";
    }

    @PostMapping("/uploadurl")
    public ResponseEntity<String> fileUploadUrl(@RequestParam String extension) {
        return ResponseEntity.ok(service.generateUrl(UUID.randomUUID()+"."+extension, HttpMethod.PUT));
    }

    @GetMapping("/downloadurl")
    public ResponseEntity<String> fileDownloadUrl(@RequestParam String filename) {
        return ResponseEntity.ok(service.generateUrl(filename, HttpMethod.GET));
    }

}

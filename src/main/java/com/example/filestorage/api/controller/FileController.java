package com.example.filestorage.api.controller;

import com.amazonaws.HttpMethod;
import com.example.filestorage.api.service.FileService;
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
        return "<h1>File Storage on AWS S3 API Documentation</h1> <h2>1. Generate Upload URL</h2> <p>Make a <strong>POST</strong> request to the following endpoint to get the upload URL: <code>/uploadurl?extension={extension}</code></p><p>Replace <code>{extension}</code> with the file extension (e.g., pdf, jpg, txt).</p><h2>2. Upload File</h2><p>Once you obtain the upload URL, make a <strong>PUT</strong> request to that URL and attach the file. If you are using Postman, go to <strong>Body</strong> &rarr; <strong>Binary</strong>, then attach the file.</p><h2>3. Check S3 Bucket</h2><p>After uploading, you can check your S3 bucket to confirm the file has been stored.</p><h2>4. Download File</h2><p>Use a <strong>GET</strong> request on the following endpoint to get the download URL: <code>/downloadurl?filename={filename}</code></p><p>Replace <code>{filename}</code> with the actual filename.</p> <h2>5. View File</h2><p>Enter the resulting download URL in your browser to view the file stored in the AWS S3 bucket.</p>";
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

package com.hrushikesh.SpringBootLearning.Service;

import java.io.InputStream;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.hrushikesh.SpringBootLearning.Entity.DatabaseFileUploadResponse;

public interface FileUploadService {

	ResponseEntity<?> localFileUpload(MultipartFile file);
	
	InputStream serveImage(String fileName);
	
	ResponseEntity<?> serveImage1(String fileName);
	
	ResponseEntity<DatabaseFileUploadResponse> databaseFileUpload(MultipartFile file);
	
}

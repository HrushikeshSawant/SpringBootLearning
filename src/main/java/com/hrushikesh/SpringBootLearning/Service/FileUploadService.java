package com.hrushikesh.SpringBootLearning.Service;

import java.io.InputStream;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {

	ResponseEntity<?> localFileUpload(MultipartFile file);
	
	InputStream serveImage(String fileName);
	
	ResponseEntity<?> serveImageUrl(String fileName);
	
	ResponseEntity<?> databaseFileUpload(MultipartFile file);
	
	ResponseEntity<?> databaseFileServe(String fileName);
	
}

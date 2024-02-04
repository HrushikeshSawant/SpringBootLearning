package com.hrushikesh.SpringBootLearning.Service;

import org.springframework.web.multipart.MultipartFile;

import com.hrushikesh.SpringBootLearning.Entity.LocalFileUploadResponse;

public interface FileUploadService {

	LocalFileUploadResponse localFileUpload(MultipartFile file);
	
}

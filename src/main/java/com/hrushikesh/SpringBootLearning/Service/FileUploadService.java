package com.hrushikesh.SpringBootLearning.Service;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {

	String localFileUpload(String folderLocation, MultipartFile file);
	
}

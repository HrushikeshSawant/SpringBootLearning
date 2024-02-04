package com.hrushikesh.SpringBootLearning.ServiceImpl;

import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hrushikesh.SpringBootLearning.Service.FileUploadService;

@Service
public class FileUploadServiceImpl implements FileUploadService{

	@Override
	public String localFileUpload(String folderLocation, MultipartFile file) {

//		if(Files.exists(Path.of(folderLocation), null))
		
		
		return null;
	}

}

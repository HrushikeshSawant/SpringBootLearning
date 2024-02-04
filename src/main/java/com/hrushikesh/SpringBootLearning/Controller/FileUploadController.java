package com.hrushikesh.SpringBootLearning.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hrushikesh.SpringBootLearning.ServiceImpl.FileUploadServiceImpl;

@RestController
@RequestMapping("/api/file-upload")
public class FileUploadController {
	
	private Logger log = LoggerFactory.getLogger(FileUploadController.class); 
	
	@Autowired
	FileUploadServiceImpl fileUploadServiceImpl;
	
	@PostMapping("/local-upload")
	public ResponseEntity<?> localFileUploadPng(@RequestParam("file") MultipartFile file)
	{
		log.info("Calling fileUploadServiceImpl to save PNG or JPG file");
		return new ResponseEntity<>(fileUploadServiceImpl.localFileUpload(file), HttpStatus.OK);
	}

}

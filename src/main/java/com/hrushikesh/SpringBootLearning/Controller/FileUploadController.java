package com.hrushikesh.SpringBootLearning.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/file-upload")
public class FileUploadController {
	
	@PostMapping("/local-upload")
	public ResponseEntity<String> localFileUpload(@RequestParam("file") MultipartFile file)
	{
		System.out.println(file.getContentType());
		System.out.println(file.getName());
		System.out.println(file.getOriginalFilename());
		System.out.println(file.getSize());
		System.out.println(file.getResource());
		return new ResponseEntity<String>("Working", HttpStatus.OK);
	}

}

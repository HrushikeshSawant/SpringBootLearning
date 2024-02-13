package com.hrushikesh.SpringBootLearning.Controller;

import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hrushikesh.SpringBootLearning.ServiceImpl.FileUploadServiceImpl;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/file-upload")
public class FileUploadController {
	
	private Logger log = LoggerFactory.getLogger(FileUploadController.class); 
	
	@Autowired
	FileUploadServiceImpl fileUploadServiceImpl;
	
	@PostMapping("/local-upload")
	public ResponseEntity<?> localFileUpload(@RequestParam("file") MultipartFile file)
	{
		log.info("Calling fileUploadServiceImpl to save PNG or JPG file");
		return fileUploadServiceImpl.localFileUpload(file);
	}
	
	@GetMapping("/image/{fileName}")
	public void serveImage(@PathVariable String fileName, HttpServletResponse response)
	{
		try
		{
			InputStream serveImage = fileUploadServiceImpl.serveImage(fileName);
			if(fileName.substring(fileName.lastIndexOf(".")).contains("png"))
				response.setContentType(MediaType.IMAGE_PNG_VALUE);
//			else if(fileName.substring(fileName.lastIndexOf(".")).contains("jpg"))
//				response.setContentType(MediaType.IMAGE_JPEG_VALUE);
			
			StreamUtils.copy(serveImage, response.getOutputStream());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@GetMapping("/image-serve-url/{fileName}")
	public ResponseEntity<?> serveImage1(@PathVariable String fileName, HttpServletResponse response)
	{
		return fileUploadServiceImpl.serveImage1(fileName);
	}
	
	@PostMapping("/database-upload")
	public ResponseEntity<?> databaseFileUpload( @RequestParam("file") MultipartFile file)
	{
		return fileUploadServiceImpl.databaseFileUpload(file);
	}

}

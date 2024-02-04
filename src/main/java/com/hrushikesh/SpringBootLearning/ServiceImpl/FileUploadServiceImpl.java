package com.hrushikesh
.SpringBootLearning.ServiceImpl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hrushikesh.SpringBootLearning.Entity.LocalFileUploadResponse;
import com.hrushikesh.SpringBootLearning.Service.FileUploadService;

@Service
public class FileUploadServiceImpl implements FileUploadService{
	
	private Logger log = LoggerFactory.getLogger(FileUploadService.class);

	@Value("${image.folder.location}")
	String folderLocation;
	
	@Override
	public LocalFileUploadResponse localFileUpload(MultipartFile multipartFile) {
		
		try 
		{
			if(multipartFile.isEmpty())
			{
				log.info("Uploaded file is empty/null.");
				return new LocalFileUploadResponse("", "File not uploaded!! Empty file.");
			}
			
			String fileContentType = multipartFile.getContentType();
			
			if(!(fileContentType.equalsIgnoreCase("image/png") || fileContentType.equalsIgnoreCase("image/jpeg")))
			{
				log.info("Uploaded file is " + multipartFile.getContentType() + " type");
				return new LocalFileUploadResponse(multipartFile.getOriginalFilename(), "Only PNG and JPG files are allowed!!");
			}
			
			File uploadDir = new File(folderLocation);
			if(!uploadDir.exists())
			{
				log.info("Directory not present to upload file created new one.");
				uploadDir.mkdir();
			}
			
//			UNIQUE FILE NAME ADDED TO AVOID FileAlreadyExistsException exception.
			String uniqueName = UUID.randomUUID().toString();
			String origialFileName = multipartFile.getOriginalFilename();
			String newFileName = uniqueName + origialFileName.substring(origialFileName.lastIndexOf("."));
			
//			METHODS FOR UPLOADING FILE INTO DIRECTORY
//			METHOD 1:
//			InputStream is = multipartFile.getInputStream();
//			byte[] data = new byte[is.available()];
//			is.read(data);
//			
//			FileOutputStream fos = new FileOutputStream(folderLocation + File.separator + newFileName);
//			fos.write(data);
//			
//			fos.flush();
//			fos.close();
//			is.close();

			
//			METHOD 2:
			Files.copy(multipartFile.getInputStream(), Path.of(folderLocation + File.separator + newFileName));
			
			log.info("File uploaded successfully to directory.");
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		return new LocalFileUploadResponse(multipartFile.getOriginalFilename(), "File uploaded successfully");
	}

}

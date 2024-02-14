package com.hrushikesh.SpringBootLearning.Helper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class UploadFileHelper {

	public static boolean uploadFile(MultipartFile file, String uploadLocation)
	{
		try
		{
			Files.copy(file.getInputStream(), Path.of(uploadLocation), StandardCopyOption.REPLACE_EXISTING);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;	
	}
	
}

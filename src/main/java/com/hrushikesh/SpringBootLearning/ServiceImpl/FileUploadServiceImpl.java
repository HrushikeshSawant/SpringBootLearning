package com.hrushikesh.SpringBootLearning.ServiceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.hrushikesh.SpringBootLearning.Entity.DatabaseFileUploadResponse;
import com.hrushikesh.SpringBootLearning.Entity.DatabaseFiles;
import com.hrushikesh.SpringBootLearning.Entity.LocalFileUploadResponse;
import com.hrushikesh.SpringBootLearning.Exception.ResourseNotFoundException;
import com.hrushikesh.SpringBootLearning.Helper.UploadFileHelper;
import com.hrushikesh.SpringBootLearning.Repository.DatabaseFilesRepo;
import com.hrushikesh.SpringBootLearning.Service.FileUploadService;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class FileUploadServiceImpl implements FileUploadService{
	
	private Logger log = LoggerFactory.getLogger(FileUploadService.class);

	@Value("${image.folder.location}")
	String folderLocation;
	
	@Autowired
	DatabaseFilesRepo databaseFilesRepo;
	
	@Override
	public ResponseEntity<?> localFileUpload(MultipartFile multipartFile) {
		
		String uriString = "";
		String uniqueName = "";
		String origialFileName = "";
		String newFileName = "";
		
		try 
		{
			if(multipartFile.isEmpty())
			{
				log.info("Uploaded file is empty/null.");
				return new ResponseEntity<>(new LocalFileUploadResponse("", "File not uploaded!! Empty file.", ""), HttpStatus.BAD_REQUEST);
			}			
			
			String fileContentType = multipartFile.getContentType();
			
			if(!(fileContentType.equalsIgnoreCase("image/png") || fileContentType.equalsIgnoreCase("image/jpeg")))
			{
				log.info("Uploaded file is " + multipartFile.getContentType() + " type");
				return new ResponseEntity<>(new LocalFileUploadResponse(multipartFile.getOriginalFilename(), "Only PNG and JPG files are allowed!!", ""), HttpStatus.BAD_REQUEST);
			}
			
//			Check your absolute path used by ClassPathResource simply by executing
//			System.out.println(new ClassPathResource("").getFile().getAbsolutePath());
//			Output: ..\target\classes			
//			The output of this snippet will let you know what is the absolute path that ClassPathResource is referring to.
//			Now create your folders which will store the uploaded data, for-example: static/image/ to store images etc. under the output path of the above snippet.
			
			String upload_location = new ClassPathResource("static/image/").getFile().getAbsolutePath();
			
			uniqueName = UUID.randomUUID().toString();
			origialFileName = multipartFile.getOriginalFilename();
			newFileName = uniqueName + origialFileName.substring(origialFileName.lastIndexOf("."));
			
/*			
			log.info(new ClassPathResource("static").getFile().getAbsolutePath());
			
			String uploadDir = new ClassPathResource("static/image/Claim Process.png").getFile().getAbsolutePath();
//			if(!uploadDir.exists())
//			{
//				log.info("Directory not present to upload file created new one.");
//				uploadDir.mkdir();
//			}
			
//			UNIQUE FILE NAME ADDED TO AVOID FileAlreadyExistsException exception.
			String uniqueName = UUID.randomUUID().toString();
			String origialFileName = multipartFile.getOriginalFilename();
			String newFileName = uniqueName + origialFileName.substring(origialFileName.lastIndexOf("."));
			log.info(uploadDir);
			log.info(newFileName);
			
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
			Files.copy(multipartFile.getInputStream(), Path.of(uploadDir + File.separator + newFileName));
*/			
			
//			METHOD 3: + (Serving of Image)
			
//			WITHOUT USING HELPER CLASS
//			Files.copy(multipartFile.getInputStream(), Path.of(upload_location + File.separator + origialFileName), StandardCopyOption.REPLACE_EXISTING);
//			uriString = ServletUriComponentsBuilder.fromCurrentContextPath().path("/image/").path(origialFileName).toUriString();
//
//			log.info("File uploaded successfully to directory.");
//			return new ResponseEntity<>(new LocalFileUploadResponse(multipartFile.getOriginalFilename(), "File uploaded successfully", uriString), HttpStatus.CREATED);
			
//			WITH USING HELPER CLASS
			boolean checker = UploadFileHelper.uploadFile(multipartFile, upload_location + File.separator + origialFileName);
			
			if(checker)
			{
				uriString = ServletUriComponentsBuilder.fromCurrentContextPath().path("/image/").path(origialFileName).toUriString();
				log.info("File uploaded successfully to directory.");
				return new ResponseEntity<>(new LocalFileUploadResponse(multipartFile.getOriginalFilename(), "File uploaded successfully", uriString), HttpStatus.CREATED);
			}
			else
			{
				log.info("File not uploaded.");
				return new ResponseEntity<>(new LocalFileUploadResponse(multipartFile.getOriginalFilename(), "File not uploaded", ""), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
	}

	@Override
	public InputStream serveImage(String fileName) {
		
		String filePath = "";
			
		try 
		{
			filePath = folderLocation + File.separator + fileName;
			if(!new File(filePath).exists())
				throw new ResourseNotFoundException("File", "name", fileName);		
			
			return new FileInputStream(filePath);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return null;		
	}
	
	@Override
	public ResponseEntity<?> serveImage1(String fileName) {

		String uriString = "";
		
		try 
		{
			File file = new File(new ClassPathResource("static/image/").getFile().getAbsolutePath()  + File.separator + fileName);
			if(file.exists())
			{
				log.info("Exists");
				uriString = ServletUriComponentsBuilder.fromCurrentContextPath().path("/image/").path(fileName).toUriString();
				return new ResponseEntity<>(new LocalFileUploadResponse(fileName, "File found on server.", uriString), HttpStatus.OK);
			}
			else
			{
				log.info("Not exists");
				return new ResponseEntity<>(new LocalFileUploadResponse(fileName, "File not found on server.", ""), HttpStatus.BAD_REQUEST);
			}			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
	}

	@Override
	public ResponseEntity<?> databaseFileUpload(MultipartFile file) {

		String originalFileName = "";
		String newName = "";
		String filePath = "";
		String fileType = "";
		
		try
		{
			originalFileName = file.getOriginalFilename();
			fileType = file.getContentType();
			newName = UUID.randomUUID() + originalFileName.substring(originalFileName.lastIndexOf("."));
			filePath = new ClassPathResource("static/image").getFile().getAbsolutePath() + File.separator + newName;
			
			boolean checker = UploadFileHelper.uploadFile(file, filePath);
			
			if(checker)
			{
				databaseFilesRepo.save(new DatabaseFiles(0, originalFileName, newName, filePath, fileType));
				log.info("File uploaded successsfully in database");
				String serveUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("/image/").path(newName).toUriString();
				return new ResponseEntity<>(new DatabaseFileUploadResponse(originalFileName, "File uploaded successsfully in database", serveUrl), HttpStatus.CREATED);
			}
			else
			{
				log.info("File not uploaded in database.");
				return new ResponseEntity<>(new DatabaseFileUploadResponse(originalFileName, "File not uploaded in database.", ""), HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			
//			String originalFileName = file.getOriginalFilename();
//			String filePath = folderLocation + File.separator + UUID.randomUUID() + originalFileName.substring(originalFileName.lastIndexOf("."));
//			
//			File uploadDir = new File(folderLocation);
//			if(!uploadDir.exists())
//			{
//				log.info("Directory not present to upload file created new one.");
//				uploadDir.mkdir();
//			}
//						
//			Files.copy(file.getInputStream(), Path.of(filePath), StandardCopyOption.REPLACE_EXISTING);
//			
//			databaseFilesRepo.save(new DatabaseFiles(0, originalFileName, filePath));
//			
//			String uriString = ServletUriComponentsBuilder.fromCurrentContextPath().path("/uploads/").path("007b7215-2781-4154-a1ca-74711833ad02.png").build().toUriString();
//			
//			return new ResponseEntity<>(new DatabaseFileUploadResponse(originalFileName, "File uploaded successfully", uriString), HttpStatus.CONFLICT);
//			
////			log.info(filePath);
////			List<DatabaseFiles> databaseFiles = databaseFilesRepo.findByoriginalFileName(originalFileName);
//			
////			if(databaseFiles.isEmpty())
////			{
////				Files.copy(file.getInputStream(), Path.of(filePath), StandardCopyOption.REPLACE_EXISTING);
////				return new ResponseEntity<>(new DatabaseFileUploadResponse(originalFileName, "File not found on server!!", ""), HttpStatus.NOT_FOUND);
////			}
////			else
////			{
////				
////			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<?> databaseFileServe(String fileName) {

		
		
		return null;
	}


}

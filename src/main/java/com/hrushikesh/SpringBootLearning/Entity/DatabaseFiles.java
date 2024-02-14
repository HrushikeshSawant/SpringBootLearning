package com.hrushikesh.SpringBootLearning.Entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class DatabaseFiles {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String originalFileName;
	String newName;
	String filePath;
	String fileType;
	Date date;

	public DatabaseFiles() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DatabaseFiles(int id, String originalFileName, String newName, String filePath, String fileType) {
		super();
		this.id = id;
		this.originalFileName = originalFileName;
		this.newName = newName;
		this.filePath = filePath;
		this.fileType = fileType;
		this.date = new Date();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOriginalFileName() {
		return originalFileName;
	}

	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}

	public String getNewName() {
		return newName;
	}

	public void setNewName(String newName) {
		this.newName = newName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}

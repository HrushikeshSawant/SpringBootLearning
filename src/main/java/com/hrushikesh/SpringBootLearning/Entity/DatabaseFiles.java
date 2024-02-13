package com.hrushikesh.SpringBootLearning.Entity;

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
	String filePath;

	public DatabaseFiles() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DatabaseFiles(int id, String originalFileName, String filePath) {
		super();
		this.id = id;
		this.originalFileName = originalFileName;
		this.filePath = filePath;
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

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

}

package com.hrushikesh.SpringBootLearning.Entity;

public class LocalFileUploadResponse {

	String fileName;
	String response;
	
	public LocalFileUploadResponse() {
		super();
	}

	public LocalFileUploadResponse(String fileName, String response) {
		super();
		this.fileName = fileName;
		this.response = response;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}
}

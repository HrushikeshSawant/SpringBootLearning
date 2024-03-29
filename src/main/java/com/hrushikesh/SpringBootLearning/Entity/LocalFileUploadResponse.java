package com.hrushikesh.SpringBootLearning.Entity;

public class LocalFileUploadResponse {

	String fileName;
	String response;
	String url;

	public LocalFileUploadResponse() {
		super();
	}

	public LocalFileUploadResponse(String fileName, String response, String url) {
		super();
		this.fileName = fileName;
		this.response = response;
		this.url = url;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}

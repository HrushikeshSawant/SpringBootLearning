package com.hrushikesh.SpringBootLearning.Exception;

import java.util.Date;

public class ErrorMsgDesc {
	
	private int httpStatus;
	private Date dateTime;
	private String message;
	
	public ErrorMsgDesc() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ErrorMsgDesc(int httpStatus, Date dateTime, String message) {
		super();
		this.httpStatus = httpStatus;
		this.dateTime = dateTime;
		this.message = message;
	}
	
	public int getHttpStatusCode() {
		return httpStatus;
	}

	public void setHttpStatus(int httpStatus) {
		this.httpStatus = httpStatus;
	}
	
	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}

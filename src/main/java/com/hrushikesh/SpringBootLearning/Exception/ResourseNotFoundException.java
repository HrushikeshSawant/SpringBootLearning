package com.hrushikesh.SpringBootLearning.Exception;


public class ResourseNotFoundException extends RuntimeException{

	private static final long serialVersionUID = -5041986972490164205L;
	private String resourceName;
	private String fieldName;
	private int fieldValue;
	private String strFieldValue;
	
	public ResourseNotFoundException(String resourceName, String fieldName, int fieldValue) {
		super(String.format("%s not found with %s: %d", resourceName, fieldName, fieldValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
	
	public ResourseNotFoundException(String resourceName, String fieldName, String strFieldValue) {
		super(String.format("%s not found with %s: %s", resourceName, fieldName, strFieldValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.strFieldValue = strFieldValue;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public int getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(int fieldValue) {
		this.fieldValue = fieldValue;
	}

	public String getStrFieldValue() {
		return strFieldValue;
	}

	public void setStrFieldValue(String strFieldValue) {
		this.strFieldValue = strFieldValue;
	}
}

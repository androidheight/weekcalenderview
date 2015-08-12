package com.example.calenderdemo;

public class Schedulemodel {
	String title;
	String subTitle;
	String subjectName;
	
	
	public Schedulemodel(String title, String subTitle, String subjectName) {
		super();
		this.title = title;
		this.subTitle = subTitle;
		this.subjectName = subjectName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSubTitle() {
		return subTitle;
	}
	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

}

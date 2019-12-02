package com.jci.india.web.app.dto;


import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

public class ContactDTO {
	
	private Long contact_id;
	
	private String id;
	
	private String bloodgroup;
	
	private String cgb;
	
	@Size(min = 3, max = 50)
	private String name;
	
	private String mob;
	
	private String occupation;
	
	private String dob;
	
	@Size(max = 500)
	private String address;

	private String chapter;
	
	private String designation;
	
	private String watsapp;
	
	private MultipartFile files;
	
	private String images;

	public Long getContact_id() {
		return contact_id;
	}

	public void setContact_id(Long contact_id) {
		this.contact_id = contact_id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBloodgroup() {
		return bloodgroup;
	}

	public void setBloodgroup(String bloodgroup) {
		this.bloodgroup = bloodgroup;
	}

	public String getCgb() {
		return cgb;
	}

	public void setCgb(String cgb) {
		this.cgb = cgb;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMob() {
		return mob;
	}

	public void setMob(String mob) {
		this.mob = mob;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getChapter() {
		return chapter;
	}

	public void setChapter(String chapter) {
		this.chapter = chapter;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getWatsapp() {
		return watsapp;
	}

	public void setWatsapp(String watsapp) {
		this.watsapp = watsapp;
	}

	public MultipartFile getFiles() {
		return files;
	}

	public void setFiles(MultipartFile files) {
		this.files = files;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

}

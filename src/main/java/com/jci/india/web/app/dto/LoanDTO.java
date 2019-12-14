package com.jci.india.web.app.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class LoanDTO {

	private String region;

	private String month;

	private String date;

	private Integer noSheets;

	private String project;

	private String program;

	private String jc;

	private String jct;

	private String jj;

	private String others;

	private Float hours;

	private String description;

	private Double rate;

	private List<MultipartFile> images;

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Integer getNoSheets() {
		return noSheets;
	}

	public void setNoSheets(Integer noSheets) {
		this.noSheets = noSheets;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getProgram() {
		return program;
	}

	public void setProgram(String program) {
		this.program = program;
	}

	public String getJc() {
		return jc;
	}

	public void setJc(String jc) {
		this.jc = jc;
	}

	public String getJct() {
		return jct;
	}

	public void setJct(String jct) {
		this.jct = jct;
	}

	public String getJj() {
		return jj;
	}

	public void setJj(String jj) {
		this.jj = jj;
	}

	public String getOthers() {
		return others;
	}

	public void setOthers(String others) {
		this.others = others;
	}

	public Float getHours() {
		return hours;
	}

	public void setHours(Float hours) {
		this.hours = hours;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public List<MultipartFile> getImages() {
		return images;
	}

	public void setImages(List<MultipartFile> images) {
		this.images = images;
	}

}

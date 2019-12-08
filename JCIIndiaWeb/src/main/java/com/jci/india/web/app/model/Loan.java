package com.jci.india.web.app.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonAlias;

@Entity
@Table(name = "loans")
@EntityListeners(AuditingEntityListener.class)
public class Loan {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="loan_id")
	private Long loanId;

	private String region;

	private String month;

	private Date date;

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

	@OneToMany(fetch = FetchType.LAZY,
			 cascade = {
		                CascadeType.PERSIST,
		                CascadeType.MERGE
		            }, orphanRemoval=true)
	@JoinColumn(name="loan_id", referencedColumnName="loan_id")
	private List<Images> images;

	public Loan() {}


	public Long getLoanId() {
		return loanId;
	}

	public void setLoanId(Long loanId) {
		this.loanId = loanId;
	}

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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
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

	public List<Images> getImages() {
		return images;
	}

	public void setImages(List<Images> images) {
		  if (this.images == null) {
	            this.images = images;
	        } else if(this.images != images) { 
	        	this.images.clear();
	            if(images != null){
	                this.images.addAll(images);
	            }
	        }	
		  }

}

package com.jci.india.web.app.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonAlias;

@Entity
@Table(name = "images")
@EntityListeners(AuditingEntityListener.class)
public class Images {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long imageId;

	private String name;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Loan.class,  cascade = CascadeType.ALL)
	@JoinColumn(name="loanId", referencedColumnName = "loanId", nullable=false)
	private Loan loan;

	public Images() {}

	public Long getImageId() {
		return imageId;
	}

	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Loan getLoan() {
		return loan;
	}

	public void setLoan(Loan loan) {
		this.loan = loan;
	}


}

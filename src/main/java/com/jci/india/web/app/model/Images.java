package com.jci.india.web.app.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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

@Entity
@Table(name = "images")
@EntityListeners(AuditingEntityListener.class)
public class Images {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="image_id")
	private Long imageId;

	private String name;
	
	@Column(name="loan_id")
	private Long loanId;

	/*@ManyToOne(fetch = FetchType.LAZY,  cascade = CascadeType.ALL)
	@JoinColumn(name="loanId", referencedColumnName = "loan_id", insertable = false, updatable = false)
	private Loan loan;*/

	public Images() {}
	public Images(String name) {
		this.name = name;
	}

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

	public Long getLoanId() {
		return loanId;
	}

	public void setLoanId(Long loanId) {
		this.loanId = loanId;
	}
	
	

	/*public Loan getLoan() {
		return loan;
	}

	public void setLoan(Loan loan) {
		this.loan = loan;
	}*/


}

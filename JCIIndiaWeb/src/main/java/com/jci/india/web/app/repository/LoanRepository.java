package com.jci.india.web.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.jci.india.web.app.model.Loan;


public interface LoanRepository extends JpaRepository<Loan, Long> ,PagingAndSortingRepository<Loan, Long> {

}

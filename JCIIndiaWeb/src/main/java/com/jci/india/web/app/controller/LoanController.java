package com.jci.india.web.app.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jci.india.web.app.dto.LoanDTO;
import com.jci.india.web.app.model.Loan;
import com.jci.india.web.app.services.LoanServiceImpl;

@RestController
@RequestMapping("/api")
public class LoanController {
	
	@Autowired
	private LoanServiceImpl loanServiceImpl;
	private static final Logger LOGGER = LogManager.getLogger(LoanController.class);
	
	@PostMapping(value="/loan/create",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createLoan(@ModelAttribute @Valid LoanDTO loanDTO) {
		try {
			if(loanServiceImpl.save(loanDTO)) {		
				return ResponseEntity.ok("Loan Insertion successfully.");	
			}
              else {
            	  return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Loan Registration Failed.");
			}
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
	
	
	@GetMapping(value="/loan/all", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Loan> getAllCandidates() {
		return loanServiceImpl.findAll();
	}

	@GetMapping(value="/loan/{loan_id}",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getLoanById(@PathVariable(value = "loan_id") Long loan_id) {
		try {
		Optional<Loan> loanOpt = loanServiceImpl.findOne(loan_id);
		if (!loanOpt.isPresent()) {
			return ((BodyBuilder) ResponseEntity.notFound()).body("Loan Details Not Found");
		}
		return ResponseEntity.ok().body(loanOpt);
	}
	catch(Exception e) {

		LOGGER.error(e.getMessage(), e);
		System.out.println(e.getMessage());
		return ResponseEntity.status(HttpStatus.CONFLICT).build();

	}
}
	@PostMapping(value="/loan/{loan_id}",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateLoan(@ModelAttribute @Valid LoanDTO loanDTO, @PathVariable(value = "loan_id") Long loan_id) {
		try {
		if(loanServiceImpl.update(loanDTO, loan_id))
		{
			return ResponseEntity.ok("Loan Details Updation successfully.");	
		}
		else
		{
			 return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Loan Deatails Updation Failed.");
		}
		
		}
		catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
	
	@DeleteMapping(value="/loan/{loan_id}",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteLoan(@PathVariable(value = "loan_id") Long loan_id) {
		try {
		Optional<Loan> loanOpt = loanServiceImpl.findOne(loan_id);
		if (!loanOpt.isPresent()) {
			return ((BodyBuilder) ResponseEntity.notFound()).body("Loan Details  Not Found");
		}

		loanServiceImpl.delete(loanOpt.get());
		return ResponseEntity.ok().body(loanOpt.get().getLoanId() + "  Successfully Deleted");
		}
		catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
	
	@GetMapping(value="/loan/list",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<Loan>> listLoanByPages(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		try {

			Page<Loan> laonPages = loanServiceImpl.findLoanByPages(page, size);
			return ResponseEntity.ok(laonPages);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
	
}

package com.jci.india.web.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

}

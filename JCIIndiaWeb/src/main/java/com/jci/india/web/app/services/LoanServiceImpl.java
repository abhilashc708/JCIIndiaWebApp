package com.jci.india.web.app.services;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jci.india.web.app.dto.LoanDTO;
import com.jci.india.web.app.model.Images;
import com.jci.india.web.app.model.Loan;
import com.jci.india.web.app.repository.LoanRepository;

import javassist.NotFoundException;

@Service
public class LoanServiceImpl {
	
	private static final Logger LOGGER = LogManager.getLogger(LoanServiceImpl.class);
	
	@Autowired
	private FileStorageService fileStorageService;

	@Autowired
	private LoanRepository loanRepository;
	
	@Transactional
	public boolean save(LoanDTO loanDTO) throws ParseException {
		boolean isSave = false;
		Loan loan= new Loan();
		Date dateDOB=new SimpleDateFormat("dd-MM-yyyy").parse(loanDTO.getDate()); 
		BeanUtils.copyProperties(loanDTO, loan);
		loan.setDate(dateDOB);
		List<MultipartFile> file = loanDTO.getImages();
		List<String> fileNames = new ArrayList<String>();
		if (file != null && file.size() > 0) {
			for (MultipartFile multipartFile : file) 
			{
				 //String fileName = multipartFile.getOriginalFilename();
				 String filePath = fileStorageService.storeFileInAPath(multipartFile);
				 fileNames.add(filePath);
			}
			 List<Images> objectList = new ArrayList<Images>();
			for(String singleFile: fileNames) {
				Images addImag  = new Images(singleFile);
				objectList.add(addImag);
			}			
			loan.setImages(objectList);
			loanDTO.setImages(null);
		}
		loanRepository.save(loan);
		isSave = true;
		 LOGGER.info("Loan  Creation Successfully");
		return isSave;
	}
	
	public List<Loan> findAll() {
		return loanRepository.findAll();
	}
	
	public Optional<Loan> findOne(Long loan_id) {
		return loanRepository.findById(loan_id);
	}
	
	public boolean update(LoanDTO loanDTO, Long loan_id) throws NotFoundException, ParseException {
		boolean isUpdated=false;
		Date dateDOB=new SimpleDateFormat("dd-MM-yyyy").parse(loanDTO.getDate()); 
		Optional<Loan> loanOpt= findOne(loan_id);
		if(!loanOpt.isPresent()){
			throw new NotFoundException("Loan Details not found");
		}
		Loan loan= loanOpt.get();
		BeanUtils.copyProperties(loanDTO, loan);
		loan.setDate(dateDOB);
		loan.setLoanId(loan_id);;
		List<MultipartFile> file = loanDTO.getImages();
		List<String> fileNames = new ArrayList<String>();
		if (file != null && file.size() > 0) {
			for (MultipartFile multipartFile : file) 
			{
				 //String fileName = multipartFile.getOriginalFilename();
				 String filePath = fileStorageService.storeFileInAPath(multipartFile);
				 fileNames.add(filePath);
			}
			 List<Images> objectList = new ArrayList<Images>();
			for(String singleFile: fileNames) {
				Images addImag  = new Images(singleFile);
				objectList.add(addImag);
			}	
			loan.setImages(objectList);
			loanDTO.setImages(null);
		}
			loanRepository.save(loan);
		isUpdated=true;
		return isUpdated;
	}
	
	public void delete(Loan loan) {
		loanRepository.delete(loan);
	}
	
	public Page<Loan> findLoanByPages(int pageNumber, int size) {
		Pageable pageable = new PageRequest(pageNumber, size);
		return loanRepository.findAll(pageable);
	}

	
}


package com.jci.india.web.app.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import com.jci.india.web.app.dto.ContactDTO;
import com.jci.india.web.app.model.Contact;
import com.jci.india.web.app.repository.ContactRepository;

import javassist.NotFoundException;


@Service
public class ContactServiceImpl {
	
	private static final Logger LOGGER = LogManager.getLogger(ContactServiceImpl.class);
	
	@Autowired
	private FileStorageService fileStorageService;
	
	@Autowired
	private ContactRepository contactRepository;
	
	@Transactional
	public boolean save(ContactDTO contactDTO) throws ParseException {
		boolean isSave = false;
		Contact contact= new Contact();
		Date dateDOB=new SimpleDateFormat("dd-MM-yyyy").parse(contactDTO.getDob()); 
		BeanUtils.copyProperties(contactDTO, contact);
		contact.setDob(dateDOB);
		MultipartFile file = contactDTO.getFiles();
		if (file != null) {
			String filePath = fileStorageService.storeFileInAPath(file);
			contact.setImages(filePath);
			contactDTO.setFiles(null);
			contactRepository.save(contact);
			isSave = true;
			 LOGGER.info("Contact  Creation Successfully");
		}
		else
		{
			 LOGGER.error("Contact creation is failed");
			return isSave;
		}
		return isSave;
	}
	
	public List<Contact> findAll() {
		return contactRepository.findAll();
	}
	
	public Optional<Contact> findOne(Long contact_id) {
		return contactRepository.findById(contact_id);
	}
	
	public boolean update(ContactDTO contactDTO, Long contact_id) throws NotFoundException, ParseException {
		boolean isUpdated=false;
		Date dateDOB=new SimpleDateFormat("dd-MM-yyyy").parse(contactDTO.getDob()); 
		Optional<Contact> contactOpt= findOne(contact_id);
		if(!contactOpt.isPresent()){
			throw new NotFoundException("Contact not found");
		}
		Contact contact= contactOpt.get();
		BeanUtils.copyProperties(contactDTO, contact);
		contact.setDob(dateDOB);
		contact.setContact_id(contact_id);
		MultipartFile file = contactDTO.getFiles();
		if (file != null) {
			String filePath = fileStorageService.storeFileInAPath(file);
			contact.setImages(filePath);
			contactDTO.setFiles(null);
		}
		contactRepository.save(contact);
		isUpdated=true;
		return isUpdated;
	}
	
	public void delete(Contact contact) {
		contactRepository.delete(contact);
	}

	public Page<Contact> findContactByPages(int pageNumber, int size) {
		Pageable pageable = new PageRequest(pageNumber, size);
		return contactRepository.findAll(pageable);
	}

}

package com.jci.india.web.app.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
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

import com.jci.india.web.app.dto.ContactDTO;
import com.jci.india.web.app.model.Contact;
import com.jci.india.web.app.services.ContactServiceImpl;
import com.jci.india.web.app.services.FileStorageService;

@RestController
@RequestMapping("/api")
public class ContactController {
	
	@Autowired
	private FileStorageService fileStorageService;
	
	@Autowired
	ContactServiceImpl contactServiceImpl;
	private static final Logger LOGGER = LogManager.getLogger(ContactController.class);
	
	@PostMapping(value="/contact/create",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createContact(@ModelAttribute @Valid ContactDTO contactDTO) {
		try {
			if(contactServiceImpl.save(contactDTO)) {		
				return ResponseEntity.ok("Contact Insertion successfully.");	
			}
              else {
            	  return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Contact Registration Failed.");
			}
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
	
	@GetMapping(value="/contact/all", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Contact> getAllCandidates() {
		return contactServiceImpl.findAll();
	}
	
	
	@GetMapping(value="/contact/{contact_id}",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getContactById(@PathVariable(value = "contact_id") Long contact_id) {
		try {
		Optional<Contact> contactOpt = contactServiceImpl.findOne(contact_id);
		if (!contactOpt.isPresent()) {
			return ((BodyBuilder) ResponseEntity.notFound()).body("Contact Not Found");
		}
		return ResponseEntity.ok().body(contactOpt);
	}
	catch(Exception e) {

		LOGGER.error(e.getMessage(), e);
		System.out.println(e.getMessage());
		return ResponseEntity.status(HttpStatus.CONFLICT).build();

	}
}
	
	@PostMapping(value="/contact/{contact_id}",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateContact(@ModelAttribute @Valid ContactDTO contactDTO, @PathVariable(value = "contact_id") Long contact_id) {
		try {
		if(contactServiceImpl.update(contactDTO, contact_id))
		{
			return ResponseEntity.ok("Contact Updation successfully.");	
		}
		else
		{
			 return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Contact Updation Failed.");
		}
		
		}
		catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
	
	@DeleteMapping(value="/contact/{contact_id}",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteContact(@PathVariable(value = "contact_id") Long contact_id) {
		try {
		Optional<Contact> contactOpt = contactServiceImpl.findOne(contact_id);
		if (!contactOpt.isPresent()) {
			return ((BodyBuilder) ResponseEntity.notFound()).body("Candidate  Not Found");
		}

		contactServiceImpl.delete(contactOpt.get());
		return ResponseEntity.ok().body(contactOpt.get().getName() + "  Successfully Deleted");
		}
		catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
	
	@GetMapping(value="/contact/list",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<Contact>> listContactByPages(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		try {

			Page<Contact> contactPages = contactServiceImpl.findContactByPages(page, size);
			return ResponseEntity.ok(contactPages);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
	
	 @GetMapping("/downloadFile/{fileName:.+}")
	    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
	        // Load file as Resource
	        Resource resource = fileStorageService.loadFileAsResource(fileName);

	        // Try to determine file's content type
	        String contentType = null;
	        try {
	            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
	        } catch (IOException ex) {
	        	LOGGER.info("Could not determine file type.");
	        }

	        // Fallback to the default content type if type could not be determined
	        if(contentType == null) {
	            contentType = "application/octet-stream";
	        }

	        return ResponseEntity.ok()
	                .contentType(MediaType.parseMediaType(contentType))
	                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
	                .body(resource);
	    }

}

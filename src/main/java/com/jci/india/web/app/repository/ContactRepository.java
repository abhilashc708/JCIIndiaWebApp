package com.jci.india.web.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.jci.india.web.app.model.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> ,PagingAndSortingRepository<Contact, Long> {

}

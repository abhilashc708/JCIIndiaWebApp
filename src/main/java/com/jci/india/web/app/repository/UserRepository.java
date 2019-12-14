package com.jci.india.web.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.jci.india.web.app.model.User;


public interface UserRepository extends JpaRepository<User, Long> ,PagingAndSortingRepository<User, Long> {
	Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    User findUserByEmail(String email);

}

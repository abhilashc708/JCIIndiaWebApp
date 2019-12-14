package com.jci.india.web.app.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jci.india.web.app.dto.UserDTO;
import com.jci.india.web.app.model.Role;
import com.jci.india.web.app.model.User;
import com.jci.india.web.app.repository.RoleRepository;
import com.jci.india.web.app.repository.UserRepository;
import com.jci.india.web.app.model.RoleName;

import javassist.NotFoundException;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
    UserRepository userRepository;
    
	@Autowired
	PasswordEncoder encoder;
	
	private static final Logger LOGGER = LogManager.getLogger(UserDetailsServiceImpl.class);
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepository.findByUsername(username).orElseThrow(
				() -> new UsernameNotFoundException("User Not Found with -> username or email : " + username));

		return UserPrinciple.build(user);
	}
	
	@Autowired
	RoleRepository roleRepository;
	
	@Transactional
	public boolean save(UserDTO userDTO) throws NotFoundException {
		
		boolean isSave = false;
		userDTO.setPassword(encoder.encode(userDTO.getPassword()));
        User usr =new User();
        BeanUtils.copyProperties(userDTO, usr);
        if(userDTO.getRoles()!=null){
        	try{
        		Set<Role> roles= userDTO.getRoles().stream().map(x->roleRepository.findByName(RoleName.ROLE_USER).get()).collect(Collectors.toSet());
        		usr.setRoles(roles);
        	}catch (NoSuchElementException e) {
				throw new NotFoundException("Role not found");
			}
			
		} 
        Optional<User> userOpt =userRepository.findByUsername(userDTO.getUsername());
        if(userOpt.isPresent()){
        	throw new NotFoundException("username already in use");
        }
        
		 if( userRepository.save(usr) != null) {
			 isSave = true;
			 return isSave;
		 }
		 else {
			 return isSave;
		 }
			 
		 }
	
	
	public User update(UserDTO userDTO, Long id) throws NotFoundException {
		Optional<User> usrOpt =findOne(id);
		if(usrOpt.isPresent()){
			User usr = usrOpt.get();
			if((userDTO.getPassword() == "") || (userDTO.getPassword() == null )) {
				usr.setPassword(usr.getPassword());
			}
			else
			{
				usr.setPassword(encoder.encode(userDTO.getPassword()));
			}
			usr.setName(userDTO.getName());
			if(userDTO.getRoles()!=null){
				try{
					Set<Role> roles= userDTO.getRoles().stream().map(x->roleRepository.findByName(x.getName()).get()).collect(Collectors.toSet());
					usr.setRoles(roles);
	        	}catch (NoSuchElementException e) {
					throw new NotFoundException("Role not found");
				}	
			}
			if((userDTO.getEmail()).equals(usr.getEmail()) )
			{
				usr.setEmail(userDTO.getEmail());	
			}
			else
			{
				throw new NotFoundException("Email Cannot modify....");
			}
			if((userDTO.getUsername()).equals(usr.getUsername()) )
			{
				usr.setUsername(userDTO.getUsername());	
			}
			else
			{
				throw new NotFoundException("UserName Cannot modify....");
			}
			
			return userRepository.saveAndFlush(usr);
		}else{
			throw new NotFoundException("User not found exception");
		}
		
	}
	
	public List<User> findAll() {
		return userRepository.findAll();
	}

	public Optional<User> findOne(Long id) {
		return userRepository.findById(id);
	}

	public void delete(User user) {
		userRepository.delete(user);
	}
	

}

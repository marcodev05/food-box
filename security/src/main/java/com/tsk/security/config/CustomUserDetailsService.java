package com.tsk.security.config;

import com.tsk.dao.UserRepository;
import com.tsk.domain.entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;



@Component
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
 	UserRepository userRepository;
	
	@Override
	public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Users user = userRepository.findByEmail(username);
		return CustomUserDetails.fromUsersToUserDetails(user);
	}

}

package com.tsk.security.config;

import com.tsk.model.Users;
import com.tsk.service.auth.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;



@Component
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	IAuthService authService;
	
	@Override
	public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Users user = authService.getByEmail(username);
		
		return CustomUserDetails.fromUsersToUserDetails(user);
	}

}

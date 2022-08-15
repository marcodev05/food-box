package com.tsk.security.config;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.tsk.domain.entities.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class CustomUserDetails implements UserDetails {
	
	private String email;
	private String password;
	Collection<? extends GrantedAuthority> granteAuthority;

	public static CustomUserDetails fromUsersToUserDetails(Users user) {
		CustomUserDetails c = new CustomUserDetails();
		c.email = user.getEmail();
		c.password = user.getPassword();
		List<SimpleGrantedAuthority> simpleGrantedAuthorityCollections = user.getRoles().stream()
				.map((roleEntity) -> {
					return new SimpleGrantedAuthority(roleEntity.getName());
				})
				.collect(Collectors.toList());
		c.granteAuthority = simpleGrantedAuthorityCollections;
		return c;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return granteAuthority;
	}

	
	@Override
	public String getPassword() {
		return password;
	}

	
	@Override
	public String getUsername() {
		return email;
	}

	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	
	@Override
	public boolean isEnabled() {
		return true;
	}

}

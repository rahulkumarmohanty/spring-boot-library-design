package com.greatlearning.library.Security;

import org.springframework.security.core.GrantedAuthority;

public final class SimpleGrantedAuthority implements GrantedAuthority{
	
	private String role;

	public SimpleGrantedAuthority(String authorities) {
		// TODO Auto-generated constructor stub
		this.role = authorities;
	}

	@Override
	public String getAuthority() {
		return role;
	}

}

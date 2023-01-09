package com.greatlearning.library.Security;

import org.springframework.security.core.GrantedAuthority;

public final class SimpleGrantedAuthority implements GrantedAuthority{
	
	private String role;

	@Override
	public String getAuthority() {
		return role;
	}

}

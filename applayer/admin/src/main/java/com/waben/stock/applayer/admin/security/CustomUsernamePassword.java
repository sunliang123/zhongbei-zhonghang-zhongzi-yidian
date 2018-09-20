package com.waben.stock.applayer.admin.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class CustomUsernamePassword extends UsernamePasswordAuthenticationToken {

	private static final long serialVersionUID = -3969104903599527222L;

	public CustomUsernamePassword(Object principal, Object credentials) {
		super(principal, credentials);
	}

}

package com.waben.stock.applayer.tactics.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class CustomUsernamePasswordAuthenticationToken extends UsernamePasswordAuthenticationToken {

	private static final long serialVersionUID = -3969104903599527222L;

	public CustomUsernamePasswordAuthenticationToken(Object principal, Object credentials) {
		super(principal, credentials);
	}

}

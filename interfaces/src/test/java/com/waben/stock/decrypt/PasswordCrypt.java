package com.waben.stock.decrypt;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class PasswordCrypt {
	public static String crypt(String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(password);
	}

	public static boolean match(String password, String encodedPassword) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.matches(password, encodedPassword);
	}
	
	public static void main(String[] args) {
		System.out.println(PasswordCrypt.crypt("123456"));
	}
}

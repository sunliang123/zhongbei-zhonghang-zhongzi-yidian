package com.waben.stock.interfaces.util;

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
	
	public static void testMain(String[] args) {
		System.out.println(PasswordCrypt.crypt("wangbei"));
	}

}

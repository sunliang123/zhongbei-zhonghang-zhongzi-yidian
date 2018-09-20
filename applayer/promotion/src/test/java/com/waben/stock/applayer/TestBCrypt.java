package com.waben.stock.applayer;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestBCrypt {
	
	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println(encoder.encode("wangbei"));
	}
	
	@Test
	public void test() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println(encoder.encode("493671072"));
		System.out.println(encoder.matches("aaa123", "$2a$10$CCBeqGvfjmlOgX5UFVzrw.qhn6QwPqv7sT.ZT.DghrwFxMMtocD1a"));
	}
	
}

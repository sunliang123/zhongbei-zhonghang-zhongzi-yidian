package com.waben.stock;

import com.waben.stock.interfaces.util.IdGenerator;

import junit.framework.TestCase;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
	
	public static void main(String[] args) {
		System.out.println(IdGenerator.INSTANCE.nextId());
	}
	
}

package com.waben.stock.datalayer.publisher;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.waben.stock.interfaces.util.IdGenerator;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PublisherApplicationTests {

	@Test
	public void contextLoads() {
		System.out.println(IdGenerator.INSTANCE.nextId());
	}

}

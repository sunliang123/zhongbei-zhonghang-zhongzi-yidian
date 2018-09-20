package com.waben.stock.datalayer.buyrecord;

import com.waben.stock.datalayer.buyrecord.service.BuyRecordService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BuyrecordApplicationTests {

	@Autowired
	private BuyRecordService buyRecordService;


	@Test
	public void testBuyRecordSend() throws InterruptedException {
//		buyRecordService.queueDirect("hello");
		Thread.sleep(30*1000);
	}

}

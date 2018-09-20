package com.waben.stock.applayer.tactics;

import com.waben.stock.interfaces.dto.stockoption.StockOptionTradeDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.*;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class TacticsApplicationTests {

	@Test
	public void contextLoads() {
		List<Student> list = new ArrayList<Student>();
		Student s1 = new Student();
		s1.setAge(20);
		s1.setUsable(true);
		Student s2 = new Student();
		s2.setAge(19);
		s2.setUsable(true);
		Student s3 = new Student();
		s3.setAge(21);
		s3.setUsable(false);
		list.add(s1);
		list.add(s2);
		list.add(s3);
		for (Student student : list){
			System.out.println(student.toString());
		}
		descByNominalAmount(list);
		for (Student student : list){
			System.out.println(student.toString());
		}
	}

	@Test
	public void isTradeTime() {
		String amStartTime = "09:35:00";
		String amEndTime =  "11:25:00";
		String pmStartTime = "13:05:00";
		String pmEndTime =  "14:55:00";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss");
		String currentTime = simpleDateFormat.format(new Date());
		System.out.println(currentTime);
		if(currentTime.compareTo(amStartTime)>0&&currentTime.compareTo(amEndTime)<0||currentTime.compareTo(pmStartTime)>0&&currentTime.compareTo(pmEndTime)<0) {
			System.out.println("在交易时间");
		}
	}
	private void descByNominalAmount(List<Student> list) {
		Collections.sort(list, new Comparator<Student>() {

			@Override
			public int compare(Student o1, Student o2) {
				// 按照学生的年龄进行升序排列
				if (o1.getAge() > o2.getAge()) {
					return 1;
				}
				if (o1.getAge() == o2.getAge()) {
					return 0;
				}
				return -1;
			}
		});
	}

}

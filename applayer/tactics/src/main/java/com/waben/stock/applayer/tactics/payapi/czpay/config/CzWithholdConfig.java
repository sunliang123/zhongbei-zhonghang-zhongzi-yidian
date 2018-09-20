package com.waben.stock.applayer.tactics.payapi.czpay.config;

public class CzWithholdConfig {
	
	public static String host = "111.161.76.55";
	
	public static Integer port = 8916;
	
	public static String sercode = "1001";
	
	// 测试
	// public static String enterpriseNo = "9275001";
	// 测试
	// public static String merId = "901031702540001";
	
	// 生产
	public static String enterpriseNo = "8UA57PW";
	// 生产
	public static String merId = "I1Q6VM2PMV6LDU7";
	
	public static String ppType = "0";
	
	public static String accountType = "0";
	
	public static String sep = "|";
	
	// 测试
	// public static String backUrl = "http://1108ab82.nat123.cc:53623/tactics/payment/czwithholdcallback";
	// 测试
	// public static String salt_key = "C2I5C130DMDPC7L6W59X8683W08E8D39";
	
	// 生产
	public static String backUrl = "https://m.youguwang.com.cn/tactics/payment/czwithholdcallback";
	// 生产
	public static String salt_key = "NWUCH4OL1102FMW4KN48LUL08ES8M2UL";
	
}

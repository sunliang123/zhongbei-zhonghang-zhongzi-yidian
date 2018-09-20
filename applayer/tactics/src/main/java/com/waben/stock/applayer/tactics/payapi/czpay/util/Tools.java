package com.waben.stock.applayer.tactics.payapi.czpay.util;

public  class Tools {
	public static String leftPad(String value,int len){
		String temp="";
		for(int i=value.length();i<len;i++){
			temp+="0";
		}
		return temp+value;
	}
	public static String rightPad(String value,int len){
		String temp="";
		for(int i=value.length();i<len;i++){
			temp+="0";
		}
		return value+temp;
	}
	public static String rightPad(String value,int len,String padStr){
		String temp="";
		for(int i=value.length();i<len;i++){
			temp+=padStr;
		}
		return value+temp;
	}
	public static String leftPad(String value,int len,String padStr){
		String temp="";
		for(int i=value.length();i<len;i++){
			temp+=padStr;
		}
		return temp+value;
	}
	
}

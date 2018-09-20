package com.waben.stock.applayer.strategist.payapi.czpay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.waben.stock.applayer.strategist.payapi.czpay.bean.CzWithholdResponse;
import com.waben.stock.applayer.strategist.payapi.czpay.config.CzWithholdConfig;
import com.waben.stock.applayer.strategist.payapi.czpay.util.ByteUtil;
import com.waben.stock.applayer.strategist.payapi.czpay.util.RealPayUtils;

public class CzWithholdOverSocket {

	private static Logger logger = LoggerFactory.getLogger(CzWithholdOverSocket.class);

	private static SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");

	public static CzWithholdResponse withhold(String withdrawalsNo, String name, String bankCard, String userPhone,
			String bankCode, BigDecimal amount) {
		StringBuilder strBuilder = new StringBuilder();
		String sercode = CzWithholdConfig.sercode;
		String enterpriseNo = CzWithholdConfig.enterpriseNo;
		String merId = CzWithholdConfig.merId;
		String ppType = CzWithholdConfig.ppType;
		strBuilder.append(sercode).append(enterpriseNo).append(merId).append(ppType);

		String sendTime = sf.format(new Date());
		String tranDate = sendTime.substring(0, 8);
		String txnTime = sendTime.substring(8, 14);
		String orderId = withdrawalsNo;
		String txnAmt = String.valueOf(amount.multiply(new BigDecimal(100)).intValue());
		String posCati = null;
		String accountType = CzWithholdConfig.accountType;
		String accNo = bankCard;
		String payName = name;
		String bankNo = null;
		String bankName = bankCode;
		String phone = userPhone;
		String backUrl = CzWithholdConfig.backUrl;
		String sep = CzWithholdConfig.sep;

		StringBuilder temp = new StringBuilder();
		temp.append(tranDate).append(sep).append(txnTime).append(sep).append(orderId).append(sep).append(txnAmt)
				.append(sep).append(posCati).append(sep).append(accountType).append(sep).append(accNo).append(sep)
				.append(payName).append(sep).append(bankNo).append(sep).append(bankName).append(sep).append(phone)
				.append(sep).append(backUrl);
		String stk = temp.toString();
		temp = temp.append(CzWithholdConfig.salt_key);

		strBuilder.append(stk);
		// 签名
		String sign = RealPayUtils.MD5(temp.toString());
		strBuilder.append(sign);
		// 计算请求数据的长度
		int countChinese = RealPayUtils.count(strBuilder.toString());
		// 建立socket连接，发送数据请求
		BufferedReader br = null;
		OutputStream os = null;
		Socket socket = null;
		try {
			socket = new Socket(CzWithholdConfig.host, CzWithholdConfig.port);
			byte[] bytes = ByteUtil.int2byte(strBuilder.length() + countChinese * 2);
			os = socket.getOutputStream();
			os.write(bytes);
			InputStream is = socket.getInputStream();
			br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			os.write(strBuilder.toString().getBytes("UTF-8"));
			os.flush();
			socket.shutdownOutput();
			// 接收服务器的响应
			String reply = null;
			String respXml = "";
			while (!((reply = br.readLine()) == null)) {
				respXml += reply;
			}
			respXml = respXml.substring(2);
			logger.error("橙子代扣响应：{}", respXml);
			String[] respArr = respXml.split("\\|");
			return new CzWithholdResponse(respArr[1], respArr[2]);
		} catch (Exception ex) {
			String message = "代扣建立连接发生异常!";
			logger.error(message + ex.getMessage());
			return new CzWithholdResponse("-1", message);
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
				}
			}
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
				}
			}
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
				}
			}
		}
	}

	public static void test(String[] args) {
		withhold("1232154848135858", "张三", "6217000010080916292", "15001120301", "PFYH", new BigDecimal(0.01));
	}

}

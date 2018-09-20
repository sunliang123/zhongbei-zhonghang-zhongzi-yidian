package com.waben.stock.datalayer.manage.service.collector;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JavaType;
import com.waben.stock.datalayer.manage.repository.DynamicQuerySqlDao;
import com.waben.stock.datalayer.manage.service.collector.bean.DataResponse;
import com.waben.stock.datalayer.manage.service.collector.bean.DomainTable;
import com.waben.stock.interfaces.util.JacksonUtil;

@Service
public class Collector {

	@Value("${execute.url:http://localhost:9090/collector/}")
	private String collectorUrl;
	@Value("${execute.domain:youguwang.com.cn}")
	private String domain;
	@Value("${execute.time:01:00:00}")
	private String executeTime;
	@Value("${execute.isgrace:false}")
	private boolean isGrace;
	@Value("${needtask:false}")
	private boolean needTask;

	private RestTemplate restTemplate = new RestTemplate();

	private SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
	private SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
	private SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Autowired
	private DynamicQuerySqlDao sqlDao;

	@PostConstruct
	public void initTask() {
		if (needTask && isGrace) {
			try {
				Timer timer = new Timer();
				Date date = new Date();
				String time = sdf2.format(date);
				// 计算下次执行时间
				Date executeDate = sdf3.parse(sdf1.format(date) + " " + executeTime);
				if (time.compareTo(executeTime) >= 0) {
					executeDate.setTime(executeDate.getTime() + 24 * 60 * 60 * 1000);
				}
				timer.schedule(new CollectorTask(), executeDate);
			} catch (Exception ex) {
			}
		}
	}

	private class CollectorTask extends TimerTask {
		@Override
		public void run() {
			try {
				List<DomainTable> tables = getDomainTable();
				for (DomainTable table : tables) {
					try {
						String sql = table.getExecuteSql();
						String countSql = "select count(*) " + sql.substring(sql.indexOf("from"));
						BigInteger totalElements = sqlDao.executeComputeSql(countSql);
						int total = totalElements != null ? totalElements.intValue() : 0;
						// 每次发送1000条
						int size = 1000;
						int page = ((total % size) > 0) ? ((total / size) + 1) : (total / size);
						for (int i = 0; i < page; i++) {
							sql += " limit " + i * size + "," + size;
							List<Object[]> sqlResult = sqlDao.execute(sql);
							sendData(table.getId(), JacksonUtil.encode(sqlResult));
						}
					} catch (Exception ex) {
						sendError(table.getId(), ex.getMessage());
					}
				}
			} catch (Exception ex) {
			} finally {
				initTask();
			}
		}
	}

	private List<DomainTable> getDomainTable() {
		String requestUrl = collectorUrl + "/domainTable/listByDomain?domain=" + domain;
		String response = restTemplate.getForObject(requestUrl, String.class);
		JavaType javaType = JacksonUtil.getGenericType(ArrayList.class, DomainTable.class);
		List<DomainTable> responseObj = JacksonUtil.decode(response, javaType);
		return responseObj;
	}
	
	private boolean sendError(Long domainTableId, String error) {
		Map<String, String> paramsMap = new HashMap<>();
		paramsMap.put("domainTableId", domainTableId.toString());
		paramsMap.put("error", error);
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.set("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		HttpEntity<String> requestEntity = new HttpEntity<String>(getParamSrc(paramsMap), requestHeaders);
		String requestUrl = collectorUrl + "/domainTable/receiveError";
		String response = restTemplate.postForObject(requestUrl, requestEntity, String.class);
		DataResponse<String> responseObj = JacksonUtil.decode(response,
				JacksonUtil.getGenericType(DataResponse.class, String.class));
		if ("200".equals(responseObj.getCode())) {
			return true;
		} else {
			return false;
		}
	}

	private boolean sendData(Long domainTableId, String data) {
		Map<String, String> paramsMap = new HashMap<>();
		paramsMap.put("domainTableId", domainTableId.toString());
		paramsMap.put("data", data);
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.set("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		HttpEntity<String> requestEntity = new HttpEntity<String>(getParamSrc(paramsMap), requestHeaders);
		String requestUrl = collectorUrl + "/domainTable/receiveData";
		String response = restTemplate.postForObject(requestUrl, requestEntity, String.class);
		DataResponse<String> responseObj = JacksonUtil.decode(response,
				JacksonUtil.getGenericType(DataResponse.class, String.class));
		if ("200".equals(responseObj.getCode())) {
			return true;
		} else {
			return false;
		}
	}

	public static String getParamSrc(Map<String, String> paramsMap) {
		StringBuffer paramstr = new StringBuffer();
		for (String pkey : paramsMap.keySet()) {
			String pvalue = paramsMap.get(pkey);
			paramstr.append(pkey + "=" + pvalue + "&");
		}
		// 去掉最后一个&
		String result = paramstr.substring(0, paramstr.length() - 1);
		return result;
	}

}

package com.waben.stock.interfaces.web;

import com.waben.stock.interfaces.util.JacksonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @author Created by yuyidi on 2017/9/26.
 * @desc
 */
public class HttpRest {
    static Logger logger = LoggerFactory.getLogger(HttpRest.class);

    private HttpRest() {
    }

    private static RestTemplate restInstance() {
        RestTemplate restTemplate = new RestTemplate();
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory
                (HttpClientInstance.getInstance());
        clientHttpRequestFactory.setConnectTimeout(6000);
        clientHttpRequestFactory.setReadTimeout(10000);
        restTemplate.setRequestFactory(clientHttpRequestFactory);
        return restTemplate;
    }

    public static RestTemplate getInstance() {
        return HttpRestHandler.nestedInstance;
    }

    public static <T> T post(String url, Object requst, Class<T> responseType) {
        logger.info("请求地址为:{}", url);
        RestTemplate restTemplate = getInstance();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        String json = JacksonUtil.encode(requst);
        Map<String, String> param = JacksonUtil.decode(json, Map.class);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        for (Map.Entry<String, String> entry : param.entrySet()) {
            map.add(entry.getKey(), entry.getValue());
        }
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        ResponseEntity<T> response = restTemplate.postForEntity(url,
                request, responseType);
        T body = null;
        if (response.getStatusCode().is3xxRedirection()) {
            body = (T) response.getHeaders().getLocation();
            logger.info("发生重定向,响应结果为重定向后的URL:{}", body);
        }
        if (response.getStatusCode().is2xxSuccessful()) {
            body = response.getBody();
            logger.info("响应成功,响应内容:{}", body);
        }
        return body;
    }

    public static <T> T get(String url, Class<T> entityClass, Map<String, String> params) {
        RestTemplate restTemplate = getInstance();
        ResponseEntity<T> response = restTemplate.getForEntity(url, entityClass, params);
        return response.getBody();
    }

    public static <T> T get(String url, Class<T> entityClass, Map<String, String> params, HttpHeaders headers) {
        RestTemplate restTemplate = getInstance();
//        ResponseEntity<T> response = restTemplate.getForEntity(url, entityClass,params);
        ResponseEntity<T> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity(headers),
                entityClass, params);
        return response.getBody();
    }

    public static <T> T  get(String url, Class<T> entityClass) {
        RestTemplate restTemplate = getInstance();
        ResponseEntity<T> response = restTemplate.getForEntity(url, entityClass);
        return response.getBody();
    }

    private static class HttpRestHandler {
        private static final RestTemplate nestedInstance = restInstance();
    }
}

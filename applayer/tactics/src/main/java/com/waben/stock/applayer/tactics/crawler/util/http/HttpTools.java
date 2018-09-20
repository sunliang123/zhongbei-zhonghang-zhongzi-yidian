package com.waben.stock.applayer.tactics.crawler.util.http;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.virjar.dungproxy.client.ippool.IpPool;
import com.virjar.dungproxy.client.model.AvProxy;

/**
 * @author Boyce
 */
public class HttpTools {
    private static final int TIMEOUT = 60000;
    private static Logger logger = LoggerFactory.getLogger(HttpTools.class);


    public static String doGet(String url, Map<String, String> params) {
        return doGet(url, params, null);
    }


    /**
     * 使用代理Get请求
     *
     * @param url
     * @param params
     * @return
     * @throws URISyntaxException
     * @throws Exception
     */
    public static String doGetProxy(String url, Map<String, String> params, Map<String, String> header) {
        CloseableHttpClient httpClient = HttpClients.custom().build();
        String result = null;
        try {
            AvProxy iPModel = IpPool.getInstance().bind("", url);
            RequestConfig requestConfig;
            if (iPModel == null) {
                requestConfig = RequestConfig.custom().setSocketTimeout(TIMEOUT).setConnectTimeout(TIMEOUT)
                        .setConnectionRequestTimeout(TIMEOUT).build();
            } else {
                requestConfig = RequestConfig.custom().setProxy(new HttpHost(iPModel.getIp(), iPModel.getPort())).setSocketTimeout(TIMEOUT).setConnectTimeout(TIMEOUT).setConnectionRequestTimeout(TIMEOUT).build();
            }

            URI uri = null;
            URIBuilder uriBuilder = new URIBuilder();
            if (params != null && params.size() > 0) {
                Set<String> keys = params.keySet();
                Iterator<String> it = keys.iterator();
                for (; it.hasNext(); ) {
                    String key = it.next();
                    String val = params.get(key);
                    uriBuilder.setParameter(key, val);
                }
            }

            uri = uriBuilder.build();

            HttpGet httpGet = new HttpGet(url + uri);
            // System.out.println(url + uri);
            httpGet.setConfig(requestConfig);
            if (header != null && header.size() > 0) {
                Set<String> keySet = header.keySet();
                for (String key : keySet) {
                    httpGet.addHeader(key, header.get(key));
                }
            }
            result = httpClient.execute(httpGet, createResponseHandler());
        } catch (Exception e1) {
            e1.printStackTrace();
            return result;
        }
        return result;
    }

    /**
     * Get请求
     *
     * @param url
     * @param params
     * @return
     * @throws URISyntaxException
     * @throws Exception
     */
    public static String doGet(String url, Map<String, String> params, Map<String, String> header) {
        CloseableHttpClient httpClient = HttpClients.custom().build();
        String result = null;
        try {
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(TIMEOUT).setConnectTimeout(TIMEOUT)
                    .setConnectionRequestTimeout(TIMEOUT).build();
            URI uri = null;
            URIBuilder uriBuilder = new URIBuilder();
            if (params != null && params.size() > 0) {
                Set<String> keys = params.keySet();
                Iterator<String> it = keys.iterator();
                for (; it.hasNext(); ) {
                    String key = it.next();
                    String val = params.get(key);
                    uriBuilder.setParameter(key, val);
                }
            }

            uri = uriBuilder.build();
            logger.info("url:{}",url+uri);
            HttpGet httpGet = new HttpGet(url + uri);
            // System.out.println(url + uri);
            httpGet.setConfig(requestConfig);
            if (header != null && header.size() > 0) {
                Set<String> keySet = header.keySet();
                for (String key : keySet) {
                    httpGet.addHeader(key, header.get(key));
                }
            }
            result = httpClient.execute(httpGet, createResponseHandler());
        } catch (Exception e1) {
            e1.printStackTrace();
            return result;
        }
        return result;
    }

    /**
     * POST请求
     *
     * @param url
     * @param params
     * @return
     * @throws IOException
     * @throws ClientProtocolException
     */
    public static String doPost(String url, Map<String, String> params) {
        return doPost(url, params, null);
    }


    public static String doPost(String url, Map<String, String> params, Map<String, String> header) {
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(TIMEOUT).setConnectTimeout(TIMEOUT)
                .setAuthenticationEnabled(true).setConnectionRequestTimeout(TIMEOUT).build();

        List<NameValuePair> paramList = new ArrayList<NameValuePair>();
        if (params != null && params.size() > 0) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                paramList.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }

        HttpPost httpPost = new HttpPost(url);
        if (header != null && header.size() > 0) {
            Set<String> keySet = header.keySet();
            for (String key : keySet) {
                httpPost.addHeader(key, header.get(key));
            }
        }
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(paramList, "UTF-8"));
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        httpPost.setConfig(requestConfig);

        String result = null;
        try {
            CloseableHttpClient httpClient = HttpClients.custom().build();
            result = httpClient.execute(httpPost, createResponseHandler());
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        }
        return result;
    }

    /**
     * GET 请求 HTTPURLConnection实现
     *
     * @param url
     * @param params
     * @param charset
     * @return
     * @throws IOException
     */
    public static String urlGet(String url, Map<String, String> params, String charset, boolean paramNeedEncode) {
        try {
            String get_url = url;
            String paramStr = "";
            if (params != null && params.size() > 0) {
                for (String key : params.keySet()) {
                    paramStr += key + "=";
                    if (paramNeedEncode) {
                        paramStr += URLEncoder.encode(params.get(key), charset) + "&";
                    } else {
                        paramStr += params.get(key) + "&";
                    }
                }
            }
            if (params != null && params.size() > 0) {
                paramStr = paramStr.substring(0, paramStr.lastIndexOf('&'));
                if (url != null && url.lastIndexOf("?") == -1) {
                    get_url += "?" + paramStr;
                }
            }
            URL getUrl = new URL(get_url);
            HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
            connection.connect();
            InputStream httpIns = connection.getInputStream();

            String responseContent = IOUtils.toString(httpIns, Charset.forName(charset));
            connection.disconnect();
            return responseContent;
        } catch (Exception e) {
            return "";
        }

    }

    /**
     * POST 请求 HTTPURLConnection实现
     *
     * @param url
     * @param params
     * @return
     * @throws IOException
     */
    public static String urlPost(String url, Map<String, String> params, String charset, boolean lineFlag) {
        try {
            logger.info("invoke other service ,url-->{}", url);
            URL postUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            /*
             * 配置本次连接的Content-type，配置为application/x-www-form-urlencoded的
			 * 意思是正文是urlencoded编码过的form参数，下面我们可以看到我们对正文内容使用URLEncoder.encode
			 * 进行编码
			 */
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.connect();
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            String content = "";
            if (params != null && params.size() > 0)
                for (String key : params.keySet()) {
                    content += key + "=" + URLEncoder.encode(params.get(key), "UTF-8") + "&";
                }
            if (params != null && params.size() > 0)
                content = content.substring(0, content.lastIndexOf('&'));
			/*
			 * DataOutputStream.writeBytes将字符串中的16位的unicode字符以8位的字符形式写道流里面
			 */
            out.writeBytes(content);
            out.flush();
            out.close();
			/*
			 * 设置编码,否则中文乱码
			 */
            InputStream httpIns = connection.getInputStream();
            String responseContent = IOUtils.toString(httpIns, Charset.forName(charset));
            connection.disconnect();
            return responseContent;
        } catch (Exception e) {
            return "";
        }
    }

    private static ResponseHandler<String> createResponseHandler() {
        return new ResponseHandler<String>() {

            public String handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
                HttpEntity httpEntity = response.getEntity();
                if (httpEntity == null) {
                    throw new ClientProtocolException("Response contains no content");
                }
                ContentType contentType = ContentType.getOrDefault(httpEntity);
                Charset charset = contentType.getCharset();
                if (charset == null) {
                    charset = Consts.UTF_8;
                }

                String result = EntityUtils.toString(response.getEntity(), charset);
                return result;
            }
        };
    }

}

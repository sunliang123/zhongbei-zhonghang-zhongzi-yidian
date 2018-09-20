package com.waben.stock.applayer.strategist.crawler.util;

import com.virjar.dungproxy.client.ippool.IpPool;
import com.virjar.dungproxy.client.model.AvProxy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * jsoup 爬虫 工具类
 *
 * @author yangdehong
 * @version 2017年4月20日
 */
public class JsoupUtil {

    private static final Logger logger = LoggerFactory.getLogger(JsoupUtil.class);

    public static final int TIMEOUT=10000;

    public static Document getJsoupUrlLink(String url, int i) {

        if (i > StockCrawlerConstants.CONN_MAX_TIMES) {
            return getDocument(url);
        }
        // 获取文档
        Document doc = getDocumentByProxy(url);
        if (doc == null) {
            i++;  // 失败次数
            doc = getJsoupUrlLink(url, i);
        }
        return doc;
    }

    public static Document getDocumentByProxy(String url) {

        AvProxy iPModel = IpPool.getInstance().bind("", url);


        Document doc = getDocumentByProxy(url, iPModel);

        if (iPModel != null) {
            if (doc == null) iPModel.recordFailed();
            else iPModel.recordUsage();
        }
        return doc;
    }

    /**
     * 通过地址得到document对象 IP 代理
     *
     * @param url
     */
    public static Document getDocumentByProxy(String url, AvProxy avProxy) {

        try {
            Document document;
            if (avProxy != null) {
                document = Jsoup.connect(url).proxy(avProxy.getIp(), avProxy.getPort()).timeout(TIMEOUT).get();
                logger.info("使用代理请求成功 {}", url);
            } else {
                document = getDocument(url);
            }
            return document;
        } catch (IOException e) {

            logger.warn("request error [{}] from proxy[{}] ,error= {}", url, avProxy != null ? avProxy.getIp() : null, e.getMessage());
        }
        return null;
    }


    public static Document getDocument(String url) {
        logger.info("不使用代理直接请求 {}", url);
        Document doc = null;
        try {
            doc = Jsoup.connect(url).timeout(TIMEOUT).get();
        } catch (IOException e) {
            try {
                doc = Jsoup.connect(url).timeout(TIMEOUT).get();
            } catch (IOException e2) {
                logger.info("========Jsoup url=" + url + " 啊啊啊啊， 连接失败");
            }
        }
        return doc;
    }


    public static boolean isError(Document doc, String select) {
        if (doc == null) return true;

        return doc.select(select).isEmpty();
    }
   

    /**
     * 判断字符是否是常规编码
     *
     * @param content 内容
     * @return 是否是常规编码
     */
    public static boolean isCheckUnicode(String content) {
    	boolean istrue=false;
    	for (int i = 0; i < content.length(); i++) {
			char c = content.charAt(i);
		    Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
	        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
	                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
	                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
	                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
	                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
	                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS||ub==Character.UnicodeBlock.BASIC_LATIN) {
	        	istrue =true;
	        }else{
	        	logger.info(content+"========字符code==" + ub );
	        	istrue=false;
	        	break;
	        }
		}
    	return istrue;
    }

}
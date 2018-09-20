package com.waben.stock.applayer.strategist.crawler.task;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.waben.stock.applayer.strategist.crawler.model.BulletinModel;
import com.waben.stock.applayer.strategist.crawler.model.ReportModel;
import com.waben.stock.applayer.strategist.crawler.model.news.ArticleEnum;
import com.waben.stock.applayer.strategist.crawler.model.news.DailyReportModel;
import com.waben.stock.applayer.strategist.crawler.mongo.DailyReportDao;
import com.waben.stock.applayer.strategist.crawler.util.MD5;
import com.waben.stock.applayer.strategist.crawler.util.base.exception.BusinessException;
import com.waben.stock.applayer.strategist.crawler.util.commons.StringUtil;
import com.waben.stock.applayer.strategist.crawler.util.date.SimpleDateFormatUtil;
import com.waben.stock.applayer.strategist.crawler.util.http.HttpTools;
import com.waben.stock.applayer.strategist.crawler.util.mvc.ResponseCode;
import com.waben.stock.applayer.strategist.crawler.util.prop.CustomProperties;

/**
 * Created by qmw on 2017/10/31.
 */
@Component
public class CrawlerData {

    @Autowired
    CustomProperties springProperties;
    public static final String CRAWLER_BULLETIN = "crawler:bulletin"; // 资讯
    public static final String CRAWLER_NEWS = "crawler:news"; // 深度要闻
    public static final String PUSH_KEY = "bulletin:push"; // 深度要闻
    static Logger logger = LoggerFactory.getLogger(CrawlerData.class);

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    DailyReportDao dailyReportDao;



    // @Scheduled(cron = "0 0 0/2 * * ? ")
    // @Async
    public void news() {
        logger.info("从 <财联社> 获取深度新闻数据,当前线程为 {}", Thread.currentThread().getName());
        long ctime = System.currentTimeMillis() / 1000 / 100;
        String code = springProperties.getProperty("crawler.cai_lian_she.data.code", "20057");
        String secret = springProperties.getProperty("crawler.cai_lian_she.data.secret", "dk5aG3sjGfDlaHhi7g54ohG7FDaeo");
        String signString = code + secret + ctime;
        String sign = MD5.toMD5(signString);  // java MD5

        Long cacheTime = (Long) redisTemplate.opsForValue().get(CRAWLER_NEWS);

        HashMap<String, String> params = new HashMap<>();
        params.put("code", code);
        params.put("sign", sign);
        params.put("rn", "20");

        if (cacheTime == null) {
            initNewsData(params);
            return;
        }
        String result = HttpTools.doGet("https://openapi.cailianpress.com/lm/depth/list", params);
        JSONObject jsonObject = JSONObject.parseObject(result);

        List<ReportModel> list = JSONObject.parseArray(jsonObject.getString("data"), ReportModel.class);
        if (list.isEmpty()) return;
        Long dataTime = list.get(0).getCtime();
        if (cacheTime >= dataTime) return;
        redisTemplate.opsForValue().set(CRAWLER_NEWS, dataTime);

        for (int i = 0; i < list.size(); i++) {
            ReportModel reportModel = list.get(i);
            if (cacheTime >= reportModel.getCtime()) {
                logger.info("从 <财联社> 获取深度新闻数据,获取数据 {} 条", i);
                break;
            }
            DailyReportModel model = new DailyReportModel();
            model.setStatus(1);
            model.setType(2);
            model.setFormat(ArticleEnum.FORMAT_HTML.getValue());
            model.setCreateTime(SimpleDateFormatUtil.getLongToDate(reportModel.getCtime()));
            model.setClicks(0);
            model.setTitle(reportModel.getTitle());
            model.setSource("财联社");
            model.setCoverUrl(reportModel.getImg());
            model.setContent(reportModel.getContent());
            dailyReportDao.saveReport(model);
        }
    }

    public void jinshiBulletin() {

        logger.info("从 <金十数据> 获取7*24小时数据");
        List<String> jinshiData = Lists.newArrayList();
        String record = getCrawlerData("https://www.jin10.com/newest_1.js?", 1);

        if (StringUtil.isEmpty(record))
            throw new BusinessException(ResponseCode.INNER_RESULT_DATA_ERROR, "返回data为空");

        Pattern pattern = Pattern.compile("[0-1]{1}#[0-1]{1}#.*?##");
        Matcher matcher = pattern.matcher(record);
        while (matcher.find()) {
            String replace = matcher.group().replace("0#1#", "");
            replace = replace.replace("0#0#", "");
            replace = replace.replace("#", "");
            replace = replace.replace("</b>", "");
            replace = replace.replace("<b>", "");
            replace = replace.replace("<br />", "");
            jinshiData.add(replace);
        }

        jinshiData.removeIf(new Predicate<String>() {
			@Override
			public boolean test(String t) {
				if(t.contains("http") || t.contains("https")) {
					return true;
				}
				return false;
			}
		});
		// next -> next.contains("http") || next.contains("https"));

        Long cacheTime = (Long) redisTemplate.opsForValue().get(CRAWLER_BULLETIN);

        String t = jinshiData.get(0).substring(0, 19);
        Long time1 = SimpleDateFormatUtil.stringToDate(t).getTime() / 1000;

        if (cacheTime != null && cacheTime >= time1) return;
        redisTemplate.opsForValue().set(CRAWLER_BULLETIN, time1);


        for (int i = 0; i < jinshiData.size(); i++) {
            String s = jinshiData.get(i);
            String time = s.substring(0, 19);
            Date date = SimpleDateFormatUtil.stringToDate(time);

            if (cacheTime != null && cacheTime >= date.getTime() / 1000) {
                logger.info("从 <金十数据> 获取7*24小时数据,更新数据为 {} 条", i + 1);
                break;
            }

            String text = s.substring(19);
            Pattern cPattern = Pattern.compile("^【.*?】");
            Matcher cMatcher = cPattern.matcher(text);

            DailyReportModel model = new DailyReportModel();
            while (cMatcher.find()) {
                model.setTitle(cMatcher.group());
            }
            model.setStatus(1);
            model.setType(1);
            if (model.getTitle() != null) {
                model.setContent(text.replace(model.getTitle(), ""));
            } else {
                model.setTitle("【快讯】");
                model.setContent(text);
            }
            model.setFormat(ArticleEnum.FORMAT_HTML.getValue());
            model.setCreateTime(date);
            model.setClicks(0);
            model.setSource("金十数据");
            dailyReportDao.saveReport(model);
        }
    }


    // @Scheduled(cron = "0 0/1 * * * ? ")
    // @Async
    public void bulletin() {
        logger.info("从 <财联社> 获取7*24小时数据,当前线程为 {}", Thread.currentThread().getName());
        long ctime = System.currentTimeMillis() / 1000 / 100;
        String code = springProperties.getProperty("crawler.cai_lian_she.data.code", "20057");
        String secret = springProperties.getProperty("crawler.cai_lian_she.data.secret", "dk5aG3sjGfDlaHhi7g54ohG7FDaeo");

        String signString = code + secret + ctime;
        String sign = MD5.toMD5(signString);  // java MD5

        Long cacheTime = (Long) redisTemplate.opsForValue().get(CRAWLER_BULLETIN);

        HashMap<String, String> params = new HashMap<>();
        params.put("code", code);
        params.put("sign", sign);

        String result = HttpTools.doGet("https://openapi.cailianpress.com/lm/roll/get_roll_list", params);
        JSONObject jsonObject = JSONObject.parseObject(result);

        List<BulletinModel> list = JSONObject.parseArray(jsonObject.getString("data"), BulletinModel.class);

        if (list != null && list.isEmpty()) return;

        Long dataTime = list.get(0).getCtime();
        if (cacheTime != null && cacheTime >= dataTime) return;
        redisTemplate.opsForValue().set(CRAWLER_BULLETIN, dataTime);

        Pattern pattern = Pattern.compile("^【.*?】");
        /*
        list = list.stream().map(bulletinModel -> {
            Matcher matcher = pattern.matcher(bulletinModel.getContent());
            while (matcher.find()) {
                bulletinModel.setTitle(matcher.group());
                bulletinModel.setContent(bulletinModel.getContent().replace(matcher.group(), ""));
            }
            return bulletinModel;
        }).collect(Collectors.toList());
        */
        
        for(BulletinModel model : list) {
        	Matcher matcher = pattern.matcher(model.getContent());
            while (matcher.find()) {
            	model.setTitle(matcher.group());
            	model.setContent(model.getContent().replace(matcher.group(), ""));
            }
        }

        for (int i = 0; i < list.size(); i++) {
            BulletinModel bulletinModel = list.get(i);
            if (cacheTime != null && cacheTime >= bulletinModel.getCtime()) {
                logger.info("从 <财联社> 获取7*24小时数据,获取数据 {} 条", i);
                break;
            }
            DailyReportModel model = new DailyReportModel();
            model.setStatus(1);
            model.setBrief(bulletinModel.getBrief());
            model.setType(1);
            model.setFormat(ArticleEnum.FORMAT_HTML.getValue());
            model.setCreateTime(SimpleDateFormatUtil.getLongToDate(bulletinModel.getCtime()));
            model.setClicks(0);
            model.setContent(bulletinModel.getContent());
            if (StringUtil.isNotEmpty(bulletinModel.getTitle())) {
                model.setTitle(bulletinModel.getTitle());
            } else {
                model.setTitle("【快讯】");
            }
            model.setSource("财联社电报");
            dailyReportDao.saveReport(model);
        }
    }

    public boolean pushBulletin() {

        LocalTime now = LocalTime.now();

        Integer count = (Integer) redisTemplate.opsForHash().get(PUSH_KEY + now.getHour(), now.getHour() + ":00");

        if (now.isAfter(LocalTime.parse("07:00")) && now.isBefore(LocalTime.parse("09:00"))) {

            if (count != null) return false;

            redisTemplate.opsForHash().increment(PUSH_KEY + now.getHour(), now.getHour() + ":00", 1);
            redisTemplate.expire(PUSH_KEY + now.getHour(), 2, TimeUnit.HOURS);

        } else if (now.isAfter(LocalTime.parse("09:00")) && now.isBefore(LocalTime.parse("15:00"))) {

            if (count != null && count >= 2) return false;

            redisTemplate.opsForHash().increment(PUSH_KEY + now.getHour(), now.getHour() + ":00", 1);
            redisTemplate.expire(PUSH_KEY + now.getHour(), 5, TimeUnit.HOURS);


        } else if (now.isAfter(LocalTime.parse("15:00")) && now.isBefore(LocalTime.parse("23:00"))) {
            String time = (String) redisTemplate.opsForValue().get(PUSH_KEY + "other");
            if (StringUtil.isNotEmpty(time)) {
                LocalTime cacheTIme = LocalTime.parse(time);
                LocalTime plus = cacheTIme.plus(3, ChronoUnit.HOURS);
                if (!now.isAfter(plus)) return false;
            }
            redisTemplate.opsForValue().set(PUSH_KEY + "other", now.toString().substring(0, 5), 8, TimeUnit.HOURS);
        } else return false;

        return true;
    }


    //重试次数
    public String getCrawlerData(String url, int i) {

        if (i > 3) {
            return HttpTools.doGetProxy(url, null, null);
        }
        String data = HttpTools.doGetProxy(url, null, null);
        if (data == null || data.contains("<head><title>400")) {
            ++i;  // 失败次数
            getCrawlerData(url, i);
        }
        return data;
    }

    public void initNewsData(HashMap<String, String> params) {
        Long time = null;
        int count = 0;
        for (int i = 0; i < 10; i++) {
            logger.info("初始化 <财联社> 获取深度新闻数据,第{}次,每次20条", i + 1);
            if (time != null) {
                params.put("last_time", String.valueOf(time));// 最后一条数据的 sort_score，用于分页。
            }
            String result = HttpTools.doGet("https://openapi.cailianpress.com/lm/depth/list", params);
            JSONObject jsonObject = JSONObject.parseObject(result);
            List<ReportModel> list = JSONObject.parseArray(jsonObject.getString("data"), ReportModel.class);

            if (list == null) continue;
            time = list.get(list.size() - 1).getCtime();

            for (int j = 0; j < list.size(); j++) {
                ReportModel reportModel = list.get(j);
                DailyReportModel model = new DailyReportModel();
                model.setStatus(1);
                model.setType(2);
                model.setFormat(ArticleEnum.FORMAT_HTML.getValue());
                model.setCreateTime(SimpleDateFormatUtil.getLongToDate(reportModel.getCtime()));
                model.setClicks(0);
                model.setTitle(reportModel.getTitle());
                model.setSource("财联社");
                model.setCoverUrl(reportModel.getImg());
                model.setContent(reportModel.getContent());
                dailyReportDao.saveReport(model);
            }
            if (count == 0) {
                Long dataTime = list.get(0).getCtime();
                redisTemplate.opsForValue().set(CRAWLER_NEWS, dataTime);
                count++;
            }
        }
    }
}

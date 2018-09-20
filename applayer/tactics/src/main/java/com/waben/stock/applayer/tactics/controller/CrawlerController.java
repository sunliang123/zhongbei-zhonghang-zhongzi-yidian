package com.waben.stock.applayer.tactics.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.waben.stock.applayer.tactics.crawler.model.AnnualReportModel;
import com.waben.stock.applayer.tactics.crawler.model.CompanyAnnouncementModel;
import com.waben.stock.applayer.tactics.crawler.model.CompanyProfileModel;
import com.waben.stock.applayer.tactics.crawler.model.StockNewsModel;
import com.waben.stock.applayer.tactics.crawler.service.CrawlerService;
import com.waben.stock.applayer.tactics.crawler.task.CrawlerData;
import com.waben.stock.applayer.tactics.crawler.util.MD5;
import com.waben.stock.applayer.tactics.crawler.util.mvc.Response;
import com.waben.stock.applayer.tactics.crawler.util.prop.CustomProperties;

import io.swagger.annotations.ApiOperation;

/**
 * 自定义
 * 股票 爬虫 获取信息
 *
 * @author yangdehong
 * @version 2017年4月20日
 */
@RestController("tacticsCrawlerController")
@RequestMapping("/crawler")
public class CrawlerController {
    @Autowired
    CustomProperties springProperties;
    @Autowired
    private CrawlerService crawlerService;
    @Autowired
    private CrawlerData crawlerData;

    @RequestMapping(value = "/selectData", method = RequestMethod.GET)
    public Response selectData(String type, String code,
                               @RequestParam(value = "page", defaultValue = "0") Integer page,
                               @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        Assert.notNull(type, code);

        List<?> list = crawlerService.selectData(type, code, page, pageSize);

        if (list == null) {
            list = new ArrayList<>();
        }
        return Response.success(list);
    }


    @RequestMapping(value = "/selectCompanyProfile", method = RequestMethod.GET)
    public Response selectCompanyProfile(String code) {
        Assert.notNull(code);

        CompanyProfileModel model = crawlerService.selectCompanyProfileData(code);
        return Response.success(model);
    }

    @RequestMapping(value = "/newsDetail", method = RequestMethod.GET)
    public Response news(String id) {
        Assert.notNull(id);
        return Response.success(crawlerService.selectStockNews(id));
    }

    @RequestMapping(value = "/companyAnnounDetail", method = RequestMethod.GET)
    public Response companyAnnouncement(String id) {
        return Response.success(crawlerService.selectCompanyAnnouncement(id));
    }

    @RequestMapping(value = "/annualReportDetail", method = RequestMethod.GET)
    public Response annualReport(String id) {
        return Response.success(crawlerService.selectAnnualReport(id));
    }

    @RequestMapping("/delErrorInfo")
    @ApiOperation(value = "none", hidden = true)
    public Response delErrorInfo()  {

        crawlerService.doDelteTask(AnnualReportModel.class);
        crawlerService.doDelteTask(CompanyAnnouncementModel.class);
        crawlerService.doDelteTask(CompanyProfileModel.class);
        crawlerService.doDelteTask(StockNewsModel.class);

        return  Response.success();
    }

    @RequestMapping(value = "/initNews", method = RequestMethod.GET)
    public Response initNews()  {
        long ctime = System.currentTimeMillis() / 1000 / 100;
        String code = springProperties.getProperty("crawler.cai_lian_she.data.code", "20057");
        String secret = springProperties.getProperty("crawler.cai_lian_she.data.secret", "dk5aG3sjGfDlaHhi7g54ohG7FDaeo");
        String signString = code + secret + ctime;
        String sign = MD5.toMD5(signString);
        HashMap<String, String> params = new HashMap<>();
        params.put("code", code);
        params.put("sign", sign);
        params.put("rn", "20");

        crawlerData.initNewsData(params);

        return  Response.success();
    }

    @RequestMapping(value = "/informationData", method = RequestMethod.GET)
    @ApiOperation(value = "7*24小时资讯")
    public Response informationData(@RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        return Response.success(crawlerService.selectDailyReport(1, page, pageSize));
    }
    
    @RequestMapping(value = "/importantNews", method = RequestMethod.GET)
    @ApiOperation(value = "要闻")
    public Response importantNews(@RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        return Response.success(crawlerService.selectDailyReport(2, page, pageSize));
    }
    
    @RequestMapping(value = "/dailyReportDetail", method = RequestMethod.GET)
    @ApiOperation(value = "7*27资讯和要闻详情")
    public Response dailyReportDetail(String id) {
    	return Response.success(crawlerService.selectDailyReport(id));
    }

}

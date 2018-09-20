package com.waben.stock.applayer.tactics.crawler.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 公司简介
 * @author yangdehong
 * @version 2017年4月26日
 */
@Document(collection = "crawler_company_profile")
public class CompanyProfileModel implements Serializable {

	private static final long serialVersionUID = 2135003148321874390L;

    @Id
	private String id;
    private String stockCode;
    private String stockName;
    private String url;
    private String from;
    private Date createDate;

    private String companyName; // 公司名称;
    private String companyEnName; // 公司英文名称;
    private String shangshishichang; //  上市市场;
    private String shangshiriqi; //  上市日期;
    private String faxingjiage; //  发行价格;
    private String zhuchengxiaoshang; //  主承销商;
    private String chengliriqi; //  成立日期;
    private String zhuceziben; //  注册资本;
    private String jigouleixing; //  机构类型;
    private String zuzhixingshi; //  组织形式;
    private String dongshihuimishu; //  董事会秘书;
    private String gongsidianhua; //  公司电话;
    private String dongmidianhua; //  董秘电话;
    private String gongsichuanzhen; //  公司传真;
    private String dongmichuanzhen; //  董秘传真;
    private String gongsidianziyouxiang; //  公司电子邮箱;
    private String dongmidianziyouxiang; //  董秘电子邮箱;
    private String gongsiwangzhi; //  公司网址;
    private String youzhengbianma; //  邮政编码;
    private String xinxipiluwangzhi; //  信息披露网址;
    private String zhengquanjianchengengminglishi; //  证券简称更名历史;
    private String zhucedizhi; //  注册地址;
    private String bangongdizhi; //  办公地址;
    private String gongsijianjie; //  公司简介;
    private String jingyingfanwei; //  经营范围;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyEnName() {
        return companyEnName;
    }

    public void setCompanyEnName(String companyEnName) {
        this.companyEnName = companyEnName;
    }

    public String getShangshishichang() {
        return shangshishichang;
    }

    public void setShangshishichang(String shangshishichang) {
        this.shangshishichang = shangshishichang;
    }

    public String getShangshiriqi() {
        return shangshiriqi;
    }

    public void setShangshiriqi(String shangshiriqi) {
        this.shangshiriqi = shangshiriqi;
    }

    public String getFaxingjiage() {
        return faxingjiage;
    }

    public void setFaxingjiage(String faxingjiage) {
        this.faxingjiage = faxingjiage;
    }

    public String getZhuchengxiaoshang() {
        return zhuchengxiaoshang;
    }

    public void setZhuchengxiaoshang(String zhuchengxiaoshang) {
        this.zhuchengxiaoshang = zhuchengxiaoshang;
    }

    public String getChengliriqi() {
        return chengliriqi;
    }

    public void setChengliriqi(String chengliriqi) {
        this.chengliriqi = chengliriqi;
    }

    public String getZhuceziben() {
        return zhuceziben;
    }

    public void setZhuceziben(String zhuceziben) {
        this.zhuceziben = zhuceziben;
    }

    public String getJigouleixing() {
        return jigouleixing;
    }

    public void setJigouleixing(String jigouleixing) {
        this.jigouleixing = jigouleixing;
    }

    public String getZuzhixingshi() {
        return zuzhixingshi;
    }

    public void setZuzhixingshi(String zuzhixingshi) {
        this.zuzhixingshi = zuzhixingshi;
    }

    public String getDongshihuimishu() {
        return dongshihuimishu;
    }

    public void setDongshihuimishu(String dongshihuimishu) {
        this.dongshihuimishu = dongshihuimishu;
    }

    public String getGongsidianhua() {
        return gongsidianhua;
    }

    public void setGongsidianhua(String gongsidianhua) {
        this.gongsidianhua = gongsidianhua;
    }

    public String getDongmidianhua() {
        return dongmidianhua;
    }

    public void setDongmidianhua(String dongmidianhua) {
        this.dongmidianhua = dongmidianhua;
    }

    public String getGongsichuanzhen() {
        return gongsichuanzhen;
    }

    public void setGongsichuanzhen(String gongsichuanzhen) {
        this.gongsichuanzhen = gongsichuanzhen;
    }

    public String getDongmichuanzhen() {
        return dongmichuanzhen;
    }

    public void setDongmichuanzhen(String dongmichuanzhen) {
        this.dongmichuanzhen = dongmichuanzhen;
    }

    public String getGongsidianziyouxiang() {
        return gongsidianziyouxiang;
    }

    public void setGongsidianziyouxiang(String gongsidianziyouxiang) {
        this.gongsidianziyouxiang = gongsidianziyouxiang;
    }

    public String getDongmidianziyouxiang() {
        return dongmidianziyouxiang;
    }

    public void setDongmidianziyouxiang(String dongmidianziyouxiang) {
        this.dongmidianziyouxiang = dongmidianziyouxiang;
    }

    public String getGongsiwangzhi() {
        return gongsiwangzhi;
    }

    public void setGongsiwangzhi(String gongsiwangzhi) {
        this.gongsiwangzhi = gongsiwangzhi;
    }

    public String getYouzhengbianma() {
        return youzhengbianma;
    }

    public void setYouzhengbianma(String youzhengbianma) {
        this.youzhengbianma = youzhengbianma;
    }

    public String getXinxipiluwangzhi() {
        return xinxipiluwangzhi;
    }

    public void setXinxipiluwangzhi(String xinxipiluwangzhi) {
        this.xinxipiluwangzhi = xinxipiluwangzhi;
    }

    public String getZhengquanjianchengengminglishi() {
        return zhengquanjianchengengminglishi;
    }

    public void setZhengquanjianchengengminglishi(String zhengquanjianchengengminglishi) {
        this.zhengquanjianchengengminglishi = zhengquanjianchengengminglishi;
    }

    public String getZhucedizhi() {
        return zhucedizhi;
    }

    public void setZhucedizhi(String zhucedizhi) {
        this.zhucedizhi = zhucedizhi;
    }

    public String getBangongdizhi() {
        return bangongdizhi;
    }

    public void setBangongdizhi(String bangongdizhi) {
        this.bangongdizhi = bangongdizhi;
    }

    public String getGongsijianjie() {
        return gongsijianjie;
    }

    public void setGongsijianjie(String gongsijianjie) {
        this.gongsijianjie = gongsijianjie;
    }

    public String getJingyingfanwei() {
        return jingyingfanwei;
    }

    public void setJingyingfanwei(String jingyingfanwei) {
        this.jingyingfanwei = jingyingfanwei;
    }
}

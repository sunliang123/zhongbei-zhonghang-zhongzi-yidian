package com.waben.stock.interfaces.vo.stockoption;

/**
 * Created by Administrator on 2018/3/17.
 */
public class MailUrlInfoVo {
    private Long id;

    /**
     * 标的名称
     */
    private String underlying;

    /**
     * 标的代码
     */
    private String code;

    /**
     * 本地路径
     */
    private String localUrl;

    /**
     * 模板名称
     */
    private String templateName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUnderlying() {
        return underlying;
    }

    public void setUnderlying(String underlying) {
        this.underlying = underlying;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLocalUrl() {
        return localUrl;
    }

    public void setLocalUrl(String localUrl) {
        this.localUrl = localUrl;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }
}

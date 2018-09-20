package com.waben.stock.interfaces.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Created by yuyidi on 2017/11/29.
 * @desc
 */
public enum ExponentEnum {

    SHAHNGSECURITY("4353", "shang"),
    SHENSECURITY("4609","shen"),
    DEVELOPSECURITY("4621", "develop");

    ExponentEnum(String code, String type) {
        this.code = code;
        this.type = type;
    }

    private String code;
    private String type;

    public String getCode() {
        return code;
    }

    public String getType() {
        return type;
    }

    private static Map<String, ExponentEnum> valueMap = new HashMap<>();

    static {
        for (ExponentEnum _enum : ExponentEnum.values()) {
            valueMap.put(_enum.getCode(), _enum);
        }
    }

    public static ExponentEnum getByCode(String index) {
        ExponentEnum result = valueMap.get(index);
        if (result == null) {
            throw new IllegalArgumentException("No element matches " + index);
        }
        return result;
    }
}

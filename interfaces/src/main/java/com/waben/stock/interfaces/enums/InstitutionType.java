package com.waben.stock.interfaces.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Created by yuyidi on 2018/3/9.
 * @desc
 */
public enum InstitutionType implements CommonalityEnum {

    PLATFORM("1","平台"),
    CHANNEL("2", "渠道");

    private String index;
    private String type;

    InstitutionType(String index, String type) {
        this.index = index;
        this.type = type;
    }

    private static Map<String, InstitutionType> valueMap = new HashMap<>();

    static {
        for (InstitutionType _enum : InstitutionType.values()) {
            valueMap.put(_enum.getIndex(), _enum);
        }
    }

    public static InstitutionType getByType(String index) {
        InstitutionType result = valueMap.get(index);
        if (result == null) {
            throw new IllegalArgumentException("No element matches " + index);
        }
        return result;
    }

    @Override
    public String getIndex() {
        return index;
    }

    public String getType() {
        return type;
    }
}

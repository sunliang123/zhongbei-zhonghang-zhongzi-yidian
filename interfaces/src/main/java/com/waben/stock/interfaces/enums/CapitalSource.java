package com.waben.stock.interfaces.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Created by yuyidi on 2018/3/9.
 * @desc 资金流水来源
 */
public enum CapitalSource implements CommonalityEnum {
    FUNDING("1", "配资"),
    OPTION("2", "期权");
    private String index;
    private String source;

    CapitalSource(String index, String source) {
        this.index = index;
        this.source = source;
    }

    private static Map<String, CapitalSource> valueMap = new HashMap<>();

    static {
        for (CapitalSource _enum : CapitalSource.values()) {
            valueMap.put(_enum.getIndex(), _enum);
        }
    }

    public static CapitalSource getByType(String index) {
        CapitalSource result = valueMap.get(index);
        if (result == null) {
            throw new IllegalArgumentException("No element matches " + index);
        }
        return result;
    }

    @Override
    public String getIndex() {
        return index;
    }

    public String getSource() {
        return source;
    }
}

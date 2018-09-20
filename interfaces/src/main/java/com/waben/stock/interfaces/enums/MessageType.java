package com.waben.stock.interfaces.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Created by yuyidi on 2018/1/3.
 * @desc
 */
public enum MessageType implements CommonalityEnum {
    POIT("1", "点对点"), TOPIC("2", "订阅"), MULTICAST("3", "组播");

    private String index;
    private String type;

    MessageType(String index, String type) {
        this.index = index;
        this.type = type;
    }

    private static Map<String, MessageType> valueMap = new HashMap<>();

    static {
        for (MessageType _enum : MessageType.values()) {
            valueMap.put(_enum.getIndex(), _enum);
        }
    }

    public static MessageType getByType(String index) {
        MessageType result = valueMap.get(index);
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

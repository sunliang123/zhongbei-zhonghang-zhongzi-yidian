package com.waben.stock.interfaces.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Created by yuyidi on 2017/12/2.
 * @desc 委托状态
 */
public enum EntrustState implements CommonalityEnum {

    UNREPORTING("0", "未报"),
    TOBESUBMIT("1", "待报"),
    HASBEENREPORTED("2", "已报"),
    REPORTEDTOWITHDRAW("3", "已报待撤"),
    PORTOKTOWITHDRAW("4", "部成待撤"),
    PORTWITHDRAW("5", "部撤"),
    HASBEENWITHDRAW("6", "已撤"),
    PORTSUCCESS("7", "部成"),
    HASBEENSUCCESS("8", "已成"),
    WASTEORDER("9", "废单");

    private static Map<String, EntrustState> valueMap = new HashMap();

    static {
        for (EntrustState _enum : EntrustState.values()) {
            valueMap.put(_enum.getIndex(), _enum);
        }
    }

    private String index;
    private String state;

    EntrustState(String index, String state) {
        this.index = index;
        this.state = state;
    }

    public static EntrustState getByIndex(String index) {
        EntrustState result = valueMap.get(index);
        if (result == null) {
            throw new IllegalArgumentException("No element matches " + index);
        }
        return result;
    }

    @Override
    public String getIndex() {
        return index;
    }

    public String getState() {
        return state;
    }
}

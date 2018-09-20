package com.waben.stock.applayer.tactics.crawler.util.base.util;

import org.springframework.beans.BeanUtils;

/**
 * Created by 12 on 2017/4/27.
 */
public class VOUtil {


    public static <T>T cover(Object src, Object vo){
        if(src==null)return null;

        BeanUtils.copyProperties(src,vo);
        return (T) vo;
    }

}

package com.waben.stock.interfaces.util;

import java.util.Arrays;

/**
 * @author Created by yuyidi on 2017/12/7.
 * @desc
 */
public class ShareCodeUtil {
    /**
     * 最大值
     */
    public static final long MAX = 60466176; // 36 ^ 6

    /**
     * 乘法用的素数
     */
    public static final long P = 15485857;

    /**
     * 加法用的素数
     */
    public static final long Q = 9007;

    /**
     * 编码长度
     */
    public static final int LEN = 5;

    /**
     * 采用36进制
     */
    public static final int RADIX = 36;

    /**
     * 编码方法。
     *
     * @param number 序号
     * @return 5位编码
     * @throws IllegalArgumentException 如果序号超过范围
     */
    public static String encode(int number) {
        if (number <= 0 || number > MAX) {
            throw new IllegalArgumentException();
        }
        long x = ((long) number * P + Q) % MAX;
        char[] codes = new char[LEN];
        Arrays.fill(codes, '0');
        String str = Long.toString(x, RADIX);
        System.arraycopy(str.toCharArray(),
                0,
                codes,
                LEN - str.length(),
                str.length());
        reverse(codes);
        return new String(codes).toUpperCase();
    }

    private static void reverse(char[] codes) {
        for (int i = LEN >> 1; i-- > 0; ) {
            codes[i] ^= codes[LEN - i - 1];
            codes[LEN - i - 1] ^= codes[i];
            codes[i] ^= codes[LEN - i - 1];
        }
    }
}

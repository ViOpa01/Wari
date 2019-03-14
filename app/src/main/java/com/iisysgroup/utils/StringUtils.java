package com.iisysgroup.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 说 明：TODO()
 * 作 者：林维新
 * 版 本：V1.0
 * 创建时间：2017年09月15日
 * 版权所有：新大陆支付技术有限公司
 */

public class StringUtils {
    /**
     * str转换成map
     * @param str  格式如：  key=val,key1=val2,key2=val3
     * @return Map {key:val, key1:val2, key2:val3}
     */
    public static Map<String,String> convertStr2Map(String str){
        Map<String,String> map = new HashMap<String, String>();
        Pattern p = Pattern.compile("([\\w]+)=([^,]+)([,]?)");
        Matcher m = p.matcher(str.toString());
        while (m.find()){
            map.put(m.group(1), m.group(2));
        }
        return map;
    }

    public static boolean equals(String str1, String str2) {
        return str1 == null?str2 == null:str1.equals(str2);
    }

    public static boolean containsIgnoreCase(String str, String searchStr) {
        return str != null && searchStr != null?contains(str.toUpperCase(), searchStr.toUpperCase()):false;
    }
    public static boolean contains(String str, String searchStr) {
        return str != null && searchStr != null?str.indexOf(searchStr) >= 0:false;
    }
    public static boolean isNumeric(String str) {
        if(str == null) {
            return false;
        } else {
            int sz = str.length();

            for(int i = 0; i < sz; ++i) {
                if(!Character.isDigit(str.charAt(i))) {
                    return false;
                }
            }

            return true;
        }
    }

    public static String leftPad(String str, int size) {
        return leftPad(str, size, ' ');
    }

    public static String leftPad(String str, int size, char padChar) {
        if(str == null) {
            return null;
        } else {
            int pads = size - str.length();
            return pads <= 0?str:(pads > 8192?leftPad(str, size, String.valueOf(padChar)):padding(pads, padChar).concat(str));
        }
    }
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }
    private static String padding(int repeat, char padChar) throws IndexOutOfBoundsException {
        if(repeat < 0) {
            throw new IndexOutOfBoundsException("Cannot pad a negative amount: " + repeat);
        } else {
            char[] buf = new char[repeat];

            for(int i = 0; i < buf.length; ++i) {
                buf[i] = padChar;
            }

            return new String(buf);
        }
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static String leftPad(String str, int size, String padStr) {
        if(str == null) {
            return null;
        } else {
            if(isEmpty(padStr)) {
                padStr = " ";
            }

            int padLen = padStr.length();
            int strLen = str.length();
            int pads = size - strLen;
            if(pads <= 0) {
                return str;
            } else if(padLen == 1 && pads <= 8192) {
                return leftPad(str, size, padStr.charAt(0));
            } else if(pads == padLen) {
                return padStr.concat(str);
            } else if(pads < padLen) {
                return padStr.substring(0, pads).concat(str);
            } else {
                char[] padding = new char[pads];
                char[] padChars = padStr.toCharArray();

                for(int i = 0; i < pads; ++i) {
                    padding[i] = padChars[i % padLen];
                }

                return (new String(padding)).concat(str);
            }
        }
    }

}

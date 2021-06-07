package com.algorithm.util;

/**
 * @author by catface
 * @date 2021/4/28 3:01 下午
 */
public class StringUtil {

    public static String randomStrOfNumber(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append((char)(Math.random() * (57 - 49) + 49));
        }
        return sb.toString();
    }

    public static String randomStrOfChar(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append((char)(Math.random() * (90 - 65) + 65));
        }
        return sb.toString();
    }

    public static char[] preProcessForPalindrome(String str) {
        char[] preChars = new char[str.length() * 2 + 1];
        int preIndex = 0;
        for (int i = 0; i < str.length(); i++) {
            preChars[preIndex++] = '#';
            preChars[preIndex++] = str.charAt(i);
        }
        preChars[preIndex] = '#';
        return preChars;
    }

}

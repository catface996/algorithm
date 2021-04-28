package util;

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

}

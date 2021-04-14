package course.other;

import org.junit.Test;

/**
 * @author by catface
 * @date 2020/11/04
 */
public class ReplaceSpace {

    /**
     * 9:59 上午	info 解答成功: 执行耗时:7 ms,击败了5.79% 的Java用户 内存消耗:38.4 MB,击败了5.00% 的Java用户
     *
     * @param s
     * @return
     */
    public String replaceSpace(String s) {
        String result = "";
        char space = " ".charAt(0);
        for (int i = 0; i < s.length(); i++) {
            char symbol = s.charAt(i);
            if (symbol == space) {
                result = result + "%20";
            } else {
                result = result + symbol;
            }
        }
        return result;
    }

    /**
     * 10:32 上午	info 解答成功: 执行耗时:0 ms,击败了100.00% 的Java用户 内存消耗:36.4 MB,击败了84.14% 的Java用户
     *
     * @param s
     * @return
     */
    public String replaceSpace2(String s) {
        char[] result = new char[s.length() * 3];
        int resultIndex = 0;
        for (int i = 0; i < s.length(); i++) {
            char symbol = s.charAt(i);
            if (symbol == ' ') {
                result[resultIndex] = '%';
                resultIndex++;
                result[resultIndex] = '2';
                resultIndex++;
                result[resultIndex] = '0';
            } else {
                result[resultIndex] = symbol;
            }
            resultIndex++;
        }
        return new String(result, 0, resultIndex);
    }

    @Test
    public void test() {
        String s = "We are happy.";
        String result = replaceSpace(s);
        System.out.println(result);
    }

    @Test
    public void test2() {
        String s = "We are happy.";
        String result = replaceSpace2(s);
        System.out.println(result);
    }

}

package question.class3_统计字符串种类.code;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

/**
 * @author by catface
 * @date 2021/5/25 4:55 下午
 */
public class Solution {

    public int countTypes(String[] arr) {
        int type;
        Set<Integer> types = new HashSet<>();
        for (String s : arr) {
            type = 0;
            for (int i = 0; i < s.length(); i++) {
                // 第0到25位记录,是否出现相应的字符
                type |= 1 << (s.charAt(i) - 'a');
            }
            types.add(type);
        }
        return types.size();
    }

    public static class TestClass{

        @Test
        public void test(){
            int possibilities = 5;
            int strMaxSize = 10;
            int arrMaxSize = 100;
            int testTimes = 500000;
            Solution solution = new Solution();
            System.out.println("test begin, test time : " + testTimes);
            for (int i = 0; i < testTimes; i++) {
                String[] arr = Code02_HowManyTypes.getRandomStringArray(possibilities, strMaxSize, arrMaxSize);
                int ans1 = solution.countTypes(arr);
                int ans2 = Code02_HowManyTypes.types2(arr);
                if (ans1 != ans2) {
                    System.out.println("Oops!");
                }
            }
            System.out.println("test finish");
        }
    }

}

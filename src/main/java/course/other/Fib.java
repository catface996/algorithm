package course.other;

import org.junit.Test;

/**
 * @author by catface
 * @date 2020/11/10
 */
public class Fib {

    int[] tempResult = null;
    private int mod = 1000000007;

    public int fib(int n) {
        tempResult = new int[n + 1];
        return fibReal(n) % mod;
    }

    private int fibReal(int n) {
        int result;
        switch (n) {
            case 0:
                result = 0;
                break;
            case 1:
                result = 1;
                tempResult[1] = 1;
                break;
            default:
                result = tempResult[n];
                if (result != 0) {
                    return result;
                }
                result = fibReal(n - 1) + fibReal(n - 2);
                if (result > mod) {
                    result = result % mod;
                }
                tempResult[n] = result;
        }
        return result;
    }

    @Test
    public void test() {
        int result = fib(95);
        System.out.println(result);
    }

}

package question.class1_大于等于n且最接近n的2的某次方.code;

/**
 * @author by catface
 * @date 2021/5/20 3:29 下午
 */
public class Solution2 {

    public int celling2Pow(int n) {
        // 右移,按位或,最终能把低位全部变成0
        n--;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : n + 1;
    }

}

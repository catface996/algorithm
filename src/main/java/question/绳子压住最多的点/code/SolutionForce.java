package question.绳子压住最多的点.code;

/**
 * @author by catface
 * @date 2021/5/20 1:49 下午
 */
public class SolutionForce {

    /**
     * 暴力计算长度为k的绳子能压住的点数
     *
     * @param arr 数轴上有序排列的点
     * @param k   长度为k的绳子
     * @return 压住的点数
     */
    public int maxPointNum(int[] arr, int k) {
        int ans = 0;
        int i = 0;
        while (i < arr.length) {
            int j = i;
            while (j < arr.length) {
                if (arr[j] - arr[i] <= k) {
                    j++;
                    continue;
                }
                ans = Math.max(ans, j - i);
                break;
            }
            if (j >= arr.length) {
                // j已经到最后一个时,
                ans = Math.max(ans, j - i);
                break;
            }
            i++;
        }
        return ans;
    }
}

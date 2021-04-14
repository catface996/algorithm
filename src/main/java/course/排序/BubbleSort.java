package course.排序;

/**
 * @author by catface
 * @date 2021/2/20 5:13 下午
 */
public class BubbleSort {

    public static int[] bubbleSort(int[] sourceArray) {
        int[] result = new int[sourceArray.length];
        System.arraycopy(sourceArray, 0, result, 0, sourceArray.length);

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result.length - i - 1; j++) {
                if (result[j] >= result[j + 1]) {
                    result[j] = result[j] ^ result[j + 1];
                    result[j + 1] = result[j] ^ result[j + 1];
                    result[j] = result[j] ^ result[j + 1];
                }
            }
        }
        return result;
    }

}

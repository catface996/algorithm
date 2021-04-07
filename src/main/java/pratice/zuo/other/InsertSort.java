package pratice.zuo.other;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import pratice.util.ArrayUtil;

/**
 * 插入排序
 *
 * @author by catface
 * @date 2021/2/20 2:03 下午
 */
@Slf4j
public class InsertSort {

    @Test
    public void test() {
        int[] sourceArray = ArrayUtil.randomIntArray(10, 1, 100);
        int[] originalArray = ArrayUtil.clone(sourceArray);
        int[] result = insertSort(originalArray);
        log.info("原始数组:{}", sourceArray);
        log.info("结果数组:{}", result);
    }

    /**
     * 插入排序
     *
     * @param sourceArray 原始数组
     * @return 排序后的数组
     */
    public static int[] insertSort(int[] sourceArray) {

        for (int i = 1; i < sourceArray.length; i++) {
            int tempPosition = i;
            for (int j = i - 1; j >= 0; j--) {
                if (sourceArray[tempPosition] <= sourceArray[j]) {
                    sourceArray[tempPosition] = sourceArray[tempPosition] ^ sourceArray[j];
                    sourceArray[j] = sourceArray[tempPosition] ^ sourceArray[j];
                    sourceArray[tempPosition] = sourceArray[tempPosition] ^ sourceArray[j];
                    tempPosition = j;
                }
            }
        }
        return sourceArray;
    }

}

package course.排序;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import util.ArrayUtil;

/**
 * 选择排序
 *
 * @author by catface
 * @date 2021/2/20 2:20 下午
 */
@Slf4j
public class ChooseSort {

    /**
     * 选择排序
     *
     * @param sourceArray 原始数组
     * @return 排序后的数组
     */
    public static int[] chooseSort(int[] sourceArray) {

        for (int i = 0; i < sourceArray.length; i++) {
            int minNumIndex = i;
            for (int j = i + 1; j < sourceArray.length; j++) {
                if (sourceArray[j] < sourceArray[minNumIndex]) {
                    minNumIndex = j;
                }
            }
            if (minNumIndex != i) {
                sourceArray[i] = sourceArray[i] ^ sourceArray[minNumIndex];
                sourceArray[minNumIndex] = sourceArray[i] ^ sourceArray[minNumIndex];
                sourceArray[i] = sourceArray[i] ^ sourceArray[minNumIndex];
            }
        }
        return sourceArray;
    }

    @Test
    public void test() {
        int[] sourceArray = ArrayUtil.randomIntArray(10, 1, 100);
        int[] originalArray = ArrayUtil.clone(sourceArray);
        int[] resultArray = chooseSort(originalArray);
        log.info("sourceArray:{}", sourceArray);
        log.info("resultArray:{}", resultArray);
    }

}

package pratice.zuo.other;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * 一个数组中,只有一种数出现了K次,其他的均出现了M次,请找到出现了K次的数,要求空间复杂度为O(1)
 *
 * @author by catface
 * @date 2021/2/20 5:42 下午
 */
@Slf4j
public class KMProblem {

    @Test
    public void test() {
        int k = 1;
        int m = 2;
        int[] testData = generateTestData(k, m, 3, 1, 100);
        int kNum = findKNum(testData, k, m);
        int kNumO1 = findKNumO1(testData, k, m);
        log.info("kNum:{},kNumO1:{},testData:{}", kNum, kNumO1, testData);
    }

    public int[] generateTestData(int k, int m, int mKindNum, int minNum, int maxNum) {
        Set<Integer> mNumSet = new HashSet<Integer>();
        Random random = new Random();
        do {
            int mNumber = random.nextInt(maxNum - minNum) + minNum;
            mNumSet.add(mNumber);
        } while (mNumSet.size() < mKindNum);
        int kNumber = random.nextInt(maxNum - minNum) + minNum;
        while (mNumSet.contains(kNumber)) {
            kNumber = random.nextInt(maxNum - minNum) + minNum;
        }

        int[] testData = new int[k + m * mKindNum];
        int testDataIndex = 0;
        for (Integer mNumber : mNumSet) {
            for (int i = 0; i < m; i++) {
                testData[testDataIndex] = mNumber;
                testDataIndex++;
            }
        }
        for (int i = 0; i < k; i++) {
            testData[testDataIndex] = kNumber;
            testDataIndex++;
        }

        for (int i = 0; i < testData.length; i++) {
            int j = random.nextInt(testData.length - 1);
            if (i != j) {
                testData[i] = testData[i] ^ testData[j];
                testData[j] = testData[i] ^ testData[j];
                testData[i] = testData[i] ^ testData[j];
            }
        }

        return testData;
    }

    /**
     * 从数组中查找出现了K次的数字
     *
     * @param sourceArray 原始数组
     * @param k           k次
     * @param m           m次
     * @return 出现了K次的数字
     */
    public int findKNum(int[] sourceArray, int k, int m) {
        Map<Integer, Integer> numTimesMap = new HashMap<Integer, Integer>();
        for (int j : sourceArray) {
            Integer appearTimes = numTimesMap.get(j);
            if (appearTimes == null) {
                numTimesMap.put(j, 1);
            } else {
                numTimesMap.put(j, appearTimes + 1);
            }
        }
        for (Integer key : numTimesMap.keySet()) {
            Integer times = numTimesMap.get(key);
            if (times.equals(k)) {
                return key;
            }
        }
        return -1;
    }

    public int findKNumO1(int[] sourceArray, int k, int m) {
        return 1;
    }

}

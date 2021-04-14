package course.other;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author by catface
 * @date 2021/2/2 4:56 下午
 */
public class RedPackage {

    /**
     * 第一轮随机
     *
     * @param totalAmount 红包总金额(分)
     * @param packageNum  红包总数
     * @param maxAmount   最大金额(分)
     * @param minAmount   最小金额(分)
     * @return 红包列表
     */
    public List<Integer> firstRound(Integer totalAmount, Integer packageNum, Integer maxAmount, Integer minAmount) {
        int totalTempAmount = 0;
        List<Integer> packageList = new ArrayList<Integer>();
        for (int i = 0; i < packageNum; i++) {
            Integer randomAmount = random(maxAmount, minAmount);
            totalTempAmount = totalTempAmount + randomAmount;
            packageList.add(randomAmount);
        }
        int leftAmount = totalTempAmount - totalAmount;

        if (leftAmount == 0) {
            return packageList;
        }
        if (leftAmount > 0) {
            int j = 0;
            for (int i = 1; i <= leftAmount; i++) {
                while (true) {
                    int index = j % packageList.size();
                    int tempAmount = packageList.get(index) - 1;
                    if (tempAmount > minAmount && tempAmount < maxAmount) {
                        packageList.set(index, tempAmount);
                        break;
                    }
                    j++;
                }
                j++;
            }
            return packageList;
        }

        int j = 0;
        for (int i = 1; i <= -leftAmount; i++) {
            while (true) {
                int index = j % packageList.size();
                int tempAmount = packageList.get(index) + 1;
                if (tempAmount > minAmount && tempAmount < maxAmount) {
                    packageList.set(index, tempAmount);
                    break;
                }
                j++;
            }
            j++;
        }
        return packageList;
    }

    private Integer random(Integer maxAmount, Integer minAmount) {
        int range = maxAmount - minAmount;
        Random random = new Random();
        Integer randomNum = random.nextInt(range);
        return minAmount + randomNum;
    }

}

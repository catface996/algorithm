package course;

import org.junit.Test;

/**
 * @author by catface
 * @date 2020/11/11
 */
public class SpinMinNumber {

    public int minArray(int[] numbers) {
        int start = 0;
        int end = numbers.length - 1;
        while (start < end) {
            int middle = start + (end - start) / 2;
            if (numbers[middle] < numbers[end]) {
                end = middle;
            } else if (numbers[middle] > numbers[end]) {
                start = middle + 1;
            } else {
                end--;
            }
        }
        return numbers[start];
    }

    @Test
    public void test() {
        int[] arr = new int[] {1, 3, 3};
        int result = minArray(arr);
        System.out.println(result);
    }

}

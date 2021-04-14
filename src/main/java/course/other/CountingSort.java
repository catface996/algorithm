package course.other;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import util.ArrayUtil;

/**
 * @author by catface
 * @date 2021/2/25 3:27 下午
 */
@Slf4j
public class CountingSort {

	public int[] sort(int[] sourceArray) throws Exception {

		// 对 arr 进行拷贝，不改变参数内容
		int[] arr = ArrayUtil.clone(sourceArray);

		int maxValue = getMaxValue(arr);

		return countingSort(arr, maxValue);
	}

	private int[] countingSort(int[] arr, int maxValue) {

		int bucketLen = maxValue + 1;

		int[] bucket = new int[bucketLen];

		for (int value : arr) {
			bucket[value]++;
		}

		int sortedIndex = 0;
		for (int j = 0; j < bucketLen; j++) {
			while (bucket[j] > 0) {
				arr[sortedIndex++] = j;
				bucket[j]--;
			}
		}
		return arr;
	}

	private int getMaxValue(int[] arr) {
		int maxValue = arr[0];
		for (int value : arr) {
			if (maxValue < value) {
				maxValue = value;
			}
		}
		return maxValue;
	}

	public void sort2(int[] arr) {

		int maxValue = getMaxValue(arr);

		countingSort2(arr, maxValue);

	}

	public void countingSort2(int[] array, int maxValue) {

		int[] bucket = new int[maxValue + 1];
		for (int value : array) {
			bucket[value]++;
		}

		int resultIndex = 0;

		for (int value = 0; value < bucket.length; value++) {

			int count = bucket[value];
			for (int times = 1; times <= count; times++) {
				array[resultIndex] = value;
				resultIndex++;
			}
		}

	}

	@Test
	public void test() {
		int[] arr = ArrayUtil.randomIntArray(10, 1, 100);
		log.info("排序前:{}", arr);
		sort2(arr);
		log.info("排序后:{}", arr);
	}

}

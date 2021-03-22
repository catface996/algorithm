package pratice.zuo.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import pratice.ArrayUtil;

/**
 * 快速排序
 *
 * @author by catface
 * @date 2021/2/20 2:49 下午
 */
@Slf4j
public class QuickSort {

	/**
	 * @param arr        待排序列
	 * @param leftIndex  待排序列起始位置
	 * @param rightIndex 待排序列结束位置
	 */
	private static void quickSort(int[] arr, int leftIndex, int rightIndex) {
		if (leftIndex >= rightIndex) {
			return;
		}

		int left = leftIndex;
		int right = rightIndex;
		//待排序的第一个元素作为基准值
		int key = arr[left];

		//从左右两边交替扫描，直到left = right
		while (left < right) {

			while (right > left && arr[right] >= key) {
				//从右往左扫描，找到第一个比基准值小的元素
				right--;
			}

			//找到这种元素将arr[right]放入arr[left]中
			arr[left] = arr[right];

			while (left < right && arr[left] <= key) {
				//从左往右扫描，找到第一个比基准值大的元素
				left++;
			}

			//找到这种元素将arr[left]放入arr[right]中
			arr[right] = arr[left];
		}
		//基准值归位
		arr[left] = key;
		//对基准值左边的元素进行递归排序
		quickSort(arr, leftIndex, left - 1);
		//对基准值右边的元素进行递归排序。
		quickSort(arr, right + 1, rightIndex);
	}

	public static void quickSort2(int[] sourceArray, int leftIndex, int rightIndex) {

		int[] tempArray = ArrayUtil.clone(sourceArray);

		// 递归退出的条件
		if (leftIndex >= rightIndex) {
			return;
		}

		int start = leftIndex;
		int end = rightIndex;
		int pilot = sourceArray[leftIndex];

		do {
			// 首先判断右侧,是否大于等于标尺值,如果大于等于,继续比较右侧的下一个数字,否则,将右侧数值转移到左侧
			do {
				if (sourceArray[end] >= pilot) {
					end--;
				} else {
					sourceArray[start] = sourceArray[end];
					break;
				}
			} while (start < end);

			do {
				if (sourceArray[start] <= pilot) {
					start++;
				} else {
					sourceArray[end] = sourceArray[start];
					break;
				}
			} while (start < end);

		} while (start < end);

		// 以标尺为基准的比较结束,将吃表设置到start
		sourceArray[end] = pilot;

		// 对剩余的左右两侧进行排序,去除标尺所在的位置

		quickSort(sourceArray, leftIndex, start - 1);

		quickSort(sourceArray, start + 1, rightIndex);

	}

	/**
	 * 快速排序
	 *
	 * @param sourceArray 原始数组
	 * @return 排序后的数组
	 */
	public static int[] quickSort(int[] sourceArray) {
		if (sourceArray == null || sourceArray.length == 1) {
			return sourceArray;
		}

		int[] leftArray = new int[sourceArray.length];
		int[] rightArray = new int[sourceArray.length];
		int leftArrayIndex = 0;
		int rightArrayIndex = 0;

		int politeNum = sourceArray[0];

		for (int i = 1; i < sourceArray.length; i++) {
			if (sourceArray[i] < politeNum) {
				leftArray[leftArrayIndex] = sourceArray[i];
				leftArrayIndex++;
			} else {
				rightArray[rightArrayIndex] = sourceArray[i];
				rightArrayIndex++;
			}
		}

		int[] leftResult = ArrayUtil.clone(leftArray, leftArrayIndex);
		int[] rightResult = ArrayUtil.clone(rightArray, rightArrayIndex);

		int[] leftArraySorted = quickSort(leftResult);
		int[] rightArraySorted = quickSort(rightResult);
		return merge(leftArraySorted, politeNum, rightArraySorted, sourceArray.length);
	}

	/**
	 * 合并排序后的左右两侧数组
	 *
	 * @param leftArraySorted  已排序的左侧数组
	 * @param politeNum        标尺值
	 * @param rightArraySorted 已排序的右侧数组
	 * @return 合并后的有序数组
	 */
	private static int[] merge(int[] leftArraySorted, int politeNum, int[] rightArraySorted, int length) {

		int[] result = new int[length];
		int resultIndex = 0;

		if (leftArraySorted != null) {
			for (int j : leftArraySorted) {
				result[resultIndex] = j;
				resultIndex++;
			}
		}

		result[resultIndex] = politeNum;
		resultIndex++;

		if (rightArraySorted != null) {
			for (int j : rightArraySorted) {
				result[resultIndex] = j;
				resultIndex++;
			}
		}

		return result;
	}

	/**
	 * 快速排序-手撕之三
	 *
	 * @param sourceArray 原始数组
	 * @param leftIndex   起始位置
	 * @param rightIndex  结束为止
	 */
	public void quickSort3(int[] sourceArray, int leftIndex, int rightIndex) {

		// 递归退出条件
		if (leftIndex > rightIndex) {
			return;
		}

		int start = leftIndex;
		int end = rightIndex;

		// 首先取左侧第一个数字作为pilot,大于 pilot 的数据移动到右侧,小于pilot的移动到左侧
		int pilot = sourceArray[leftIndex];

		while (start < end) {

			while (start < end) {
				if (sourceArray[end] >= pilot) {
					end--;
				} else {
					sourceArray[start] = sourceArray[end];
					break;
				}
			}

			while (start < end) {
				if (sourceArray[start] <= pilot) {
					start++;
				} else {
					sourceArray[end] = sourceArray[start];
					break;
				}
			}

			sourceArray[start] = pilot;

		}

		quickSort3(sourceArray, leftIndex, start - 1);

		quickSort(sourceArray, start + 1, rightIndex);

	}

	@Test
	public void testRound() {
		int totalNum = 0;
		for (int size = 1; size < 100; size++) {
			for (int i = 0; i < 1000; i++) {
				int[] originalArray = ArrayUtil.randomIntArray(size, 1, 100);
				int[] bubbleSortResult = BubbleSort.bubbleSort(originalArray);
				int[] quickSortResult = quickSort(originalArray);
				boolean checkResult = ArrayUtil.checkSortResult(bubbleSortResult, quickSortResult);
				totalNum++;
				if (!checkResult) {
					log.info("原始数组:{}", originalArray);
					log.info("原始数组:{},冒泡排序后的数组{}", originalArray, bubbleSortResult);
					log.info("原始数组:{},快速排序后的数组{}", originalArray, quickSortResult);
					log.info("比对结果:{}", checkResult);
				}
			}
		}
		log.info("{}次全部比对通过", totalNum);
	}

	@Test
	public void test() {
		int passCount = 0;
		for (int i = 1; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				int[] sourceArray = ArrayUtil.randomIntArray(i, 1, 100);
				int[] originalArray = ArrayUtil.clone(sourceArray);
				quickSort3(originalArray, 0, originalArray.length - 1);
				int[] bobbleSortResult = BubbleSort.bubbleSort(sourceArray);
				boolean checkResult = ArrayUtil.checkSortResult(originalArray, bobbleSortResult);
				if (!checkResult) {
					log.info("原始数据:{}", sourceArray);
					log.info("结果数据:{}", originalArray);
				} else {
					passCount++;
				}
			}
		}
		log.info("比对通过次数:{}", passCount);

	}

	@Test
	public void test2() {
		int[] sourceArray = new int[] {16, 50, 14};
		quickSort3(sourceArray, 0, sourceArray.length - 1);
	}

}

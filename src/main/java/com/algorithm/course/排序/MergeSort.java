package com.algorithm.course.排序;

import com.algorithm.util.ArrayUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * 归并排序
 *
 * @author by catface
 * @date 2021/2/18 10:47 上午
 */
@Slf4j
public class MergeSort {

	@Test
	public void test() {
		int totalNum = 0;
		for (int size = 1; size < 100; size++) {
			for (int i = 0; i < 1000; i++) {
				int[] originalArray = ArrayUtil.randomIntArray(size, 1, 100);
				int[] bubbleSortResult = BubbleSort.bubbleSort(originalArray);
				int[] mergeSortResult = recursionMergeSort(originalArray, 0, originalArray.length - 1);
				boolean checkResult = ArrayUtil.checkSortResult(bubbleSortResult, mergeSortResult);
				totalNum++;
				if (!checkResult) {
					log.info("原始数组:{}", originalArray);
					log.info("原始数组:{},冒泡排序后的数组{}", originalArray, bubbleSortResult);
					log.info("原始数组:{},归并排序(递归)后的数组{}", originalArray, mergeSortResult);
					log.info("比对结果:{}", checkResult);
				}
			}
		}
		log.info("{}次全部比对通过", totalNum);
	}

	@Test
	public void testIterationMergeSort() {
		int totalNum = 0;
		for (int size = 1; size < 100; size++) {
			for (int i = 0; i < 1000; i++) {
				int[] originalArray = ArrayUtil.randomIntArray(size, 1, 100);
				int[] bubbleSortResult = BubbleSort.bubbleSort(originalArray);
				int[] mergeSortResult = iterationMergeSort(originalArray);
				boolean checkResult = ArrayUtil.checkSortResult(bubbleSortResult, mergeSortResult);
				totalNum++;
				if (!checkResult) {
					log.info("原始数组:{}", originalArray);
					log.info("原始数组:{},冒泡排序后的数组{}", originalArray, bubbleSortResult);
					log.info("原始数组:{},归并排序(迭代)后的数组{}", originalArray, mergeSortResult);
					log.info("比对结果:{}", checkResult);
				}
			}
		}
		log.info("{}次全部比对通过", totalNum);
	}

	@Test
	public void testI() {
		int[] originalArray = new int[] {59, 74, 58, 72, 63, 78, 34, 87, 30, 6};
		int[] mergeSortResult = iterationMergeSort(originalArray);
		log.info("{}", mergeSortResult);
	}

	public int[] recursionMergeSort(int[] sourceArray, int left, int right) {
		if (left == right) {
			return new int[] {sourceArray[left]};
		}
		int middle = left + ((right - left) >> 1);
		int[] leftSortArray = recursionMergeSort(sourceArray, left, middle);
		int[] rightSortArray = recursionMergeSort(sourceArray, middle + 1, right);
		return mergeSortArray(leftSortArray, rightSortArray);
	}

	public int[] mergeSortArray(int[] leftSortArray, int[] rightSortArray) {
		int leftIndex = 0;
		int rightIndex = 0;
		int resultIndex = 0;
		int[] resultArray = new int[leftSortArray.length + rightSortArray.length];

		while (true) {

			if (leftSortArray[leftIndex] < rightSortArray[rightIndex]) {
				resultArray[resultIndex] = leftSortArray[leftIndex];
				leftIndex++;
			} else {
				resultArray[resultIndex] = rightSortArray[rightIndex];
				rightIndex++;
			}

			resultIndex++;

			// 左侧已遍历结束,复制右侧剩余有序数字到结果数组中
			if (leftIndex >= leftSortArray.length) {
				while (rightIndex < rightSortArray.length) {
					resultArray[resultIndex] = rightSortArray[rightIndex];
					rightIndex++;
					resultIndex++;
				}
				return resultArray;
			}

			// 右侧已便利结束,复制左侧剩余有序数字到结果数组中
			if (rightIndex >= rightSortArray.length) {
				while (leftIndex < leftSortArray.length) {
					resultArray[resultIndex] = leftSortArray[leftIndex];
					leftIndex++;
					resultIndex++;
				}
				return resultArray;
			}
		}
	}

	public int[] iterationMergeSort(int[] sourceArray) {
		int[] result = new int[sourceArray.length];
		System.arraycopy(sourceArray, 0, result, 0, sourceArray.length);

		int stepSize = 1;
		while (true) {
			if (stepSize >= result.length) {
				return result;
			}
			int leftStartIndex = 0;
			do {
				sortSubPart(result, leftStartIndex, stepSize);
				leftStartIndex = leftStartIndex + stepSize * 2;
			} while (leftStartIndex < result.length);
			stepSize = stepSize << 1;
		}
	}

	public void sortSubPart(int[] sourceArray, int leftStartIndex, int stepSize) {
		int leftStart = leftStartIndex;
		int leftEnd = leftStart + stepSize - 1;
		if (leftEnd >= sourceArray.length - 1) {
			return;
		}
		int rightStart = leftEnd + 1;
		int rightEnd = rightStart + stepSize - 1;
		if (rightEnd >= sourceArray.length - 1) {
			rightEnd = sourceArray.length - 1;
		}
		int[] subPartSortResult = new int[rightEnd - leftStart + 1];
		int resultIndex = 0;

		while (true) {
			if (sourceArray[leftStart] < sourceArray[rightStart]) {
				subPartSortResult[resultIndex] = sourceArray[leftStart];
				leftStart++;
			} else {
				subPartSortResult[resultIndex] = sourceArray[rightStart];
				rightStart++;
			}

			resultIndex++;

			// 左侧排序数组已经归并完成,将右侧剩余数组全部归并
			if (leftStart > leftEnd) {
				while (rightStart <= rightEnd) {
					subPartSortResult[resultIndex] = sourceArray[rightStart];
					rightStart++;
					resultIndex++;
				}
				break;
			}

			// 右侧排序数组已经归并完成,将左侧侧剩余数组全部归并
			if (rightStart > rightEnd) {
				while (leftStart <= leftEnd) {
					subPartSortResult[resultIndex] = sourceArray[leftStart];
					leftStart++;
					resultIndex++;
				}
				break;
			}
		}

		// 已对部分数组归并完成,复制到原始数组中
		System.arraycopy(subPartSortResult, 0, sourceArray, leftStartIndex, subPartSortResult.length);
	}

	public int[] mergeSort3(int[] arr, int leftIndex, int rightIndex) {
		if (leftIndex > rightIndex) {
			return null;
		}
		if (leftIndex == rightIndex) {
			return new int[] {arr[leftIndex]};
		}

		int middleIndex = leftIndex + ((rightIndex - leftIndex) >> 1);
		int[] leftResult = mergeSort3(arr, leftIndex, middleIndex);
		int[] rightResult = mergeSort3(arr, middleIndex + 1, rightIndex);
		return merge3(leftResult, rightResult);

	}

	public int[] merge3(int[] leftResult, int[] rightResult) {

		int[] result = new int[leftResult.length + rightResult.length];
		int rightStart = 0;
		int leftStart = 0;
		int resultIndex = 0;

		while (resultIndex < result.length) {

			if (leftResult[leftStart] <= rightResult[rightStart]) {
				result[resultIndex] = leftResult[leftStart];
				leftStart++;
			} else {
				result[resultIndex] = rightResult[rightStart];
				rightStart++;
			}

			resultIndex++;

			// 左侧已经复制完成,对右侧进行复制
			if (leftStart >= leftResult.length) {
				while (rightStart < rightResult.length) {
					result[resultIndex] = rightResult[rightStart];
					rightStart++;
					resultIndex++;
				}
			}

			if (rightStart >= rightResult.length) {
				while (leftStart < leftResult.length) {
					result[resultIndex] = leftResult[leftStart];
					leftStart++;
					resultIndex++;
				}
			}

		}
		return result;

	}

	@Test
	public void test3() {
		int[] arr = ArrayUtil.randomIntArray(5, 1, 10);
		int[] result = mergeSort3(arr, 0, arr.length - 1);
		log.info("排序前:{}", arr);
		log.info("排序后:{}", result);
	}

}

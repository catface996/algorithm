package com.algorithm.course.排序;

import com.algorithm.util.ArrayUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author by catface
 * @date 2021/2/25 2:04 下午
 */
@Slf4j
public class ShellSort {

	public void sort(int[] arr) {

		for (int gap = arr.length / 2; gap > 0; gap = gap / 2) {

			log.info("gap:{}", gap);
			for (int i = gap; i < arr.length; i++) {

				log.info("gap:{},i:{}", gap, i);
				int end = i;
				int start = end - gap;

				while (true) {

					if (start < 0) {
						log.info("起始位置小于0,退出,start:{},end:{}", start, end);
						break;
					}

					int startValue = arr[start];
					int endValue = arr[end];
					log.info("处理前:{}", arr);
					log.info("start:{},startValue:{},end:{},endValue:{}", start, startValue, end, endValue);
					if (startValue <= endValue) {
						log.info("startValue <= endValue,无需交换,跳出 while,进入for循环下一轮");
						break;
					}
					log.info("交换首位位置的值,start:{},end:{}", start, end);
					swap(arr, start, end);
					log.info("交换后:{}", arr);
					end = end - gap;
					start = end - gap;
				}

			}

		}

	}

	public void swap(int[] arr, int a, int b) {
		arr[a] = arr[a] ^ arr[b];
		arr[b] = arr[a] ^ arr[b];
		arr[a] = arr[a] ^ arr[b];
	}

	@Test
	public void test() {
		int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
		log.info("排序前:{}", arr);
		sort(arr);
		log.info("排序后:{}", arr);
	}

	@Test
	public void testIterationMergeSort() {
		int totalNum = 0;
		for (int size = 1; size < 100; size++) {
			for (int i = 0; i < 1000; i++) {
				int[] originalArray = ArrayUtil.randomIntArray(size, 1, 100);
				int[] arrForBobble = ArrayUtil.clone(originalArray);
				int[] arrForShell = ArrayUtil.clone(originalArray);
				int[] bubbleSortResult = BubbleSort.bubbleSort(arrForBobble);
				sort(arrForShell);
				boolean checkResult = ArrayUtil.checkSortResult(bubbleSortResult, arrForShell);
				totalNum++;
				if (!checkResult) {
					log.info("原始数组:{}", originalArray);
					log.info("比对结果:{}", checkResult);
				}
			}
		}
		log.info("{}次全部比对通过", totalNum);
	}

}

package com.algorithm.course.other;

import com.algorithm.util.ArrayUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author by catface
 * @date 2021/2/26 11:06 上午
 */
@Slf4j
public class P327区间和的个数 {

	//给定一个整数数组 nums 。区间和 S(i, j) 表示在 nums 中，位置从 i 到 j 的元素之和，包含 i 和 j (i ≤ j)。
	//
	// 请你以下标 i （0 <= i <= nums.length ）为起点，元素个数逐次递增，计算子数组内的元素和。
	//
	// 当元素和落在范围 [lower, upper] （包含 lower 和 upper）之内时，记录子数组当前最末元素下标 j ，记作 有效 区间和 S(i,
	// j) 。
	//
	// 求数组中，值位于范围 [lower, upper] （包含 lower 和 upper）之内的 有效 区间和的个数。
	//
	//
	//
	// 注意：
	//最直观的算法复杂度是 O(n2) ，请在此基础上优化你的算法。
	//
	//
	//
	// 示例：
	//
	//
	//输入：nums = [-2,5,-1], lower = -2, upper = 2,
	//输出：3
	//解释：
	//下标 i = 0 时，子数组 [-2]、[-2,5]、[-2,5,-1]，对应元素和分别为 -2、3、2 ；其中 -2 和 2 落在范围 [lower =
	//-2, upper = 2] 之间，因此记录有效区间和 S(0,0)，S(0,2) 。
	//下标 i = 1 时，子数组 [5]、[5,-1] ，元素和 5、4 ；没有满足题意的有效区间和。
	//下标 i = 2 时，子数组 [-1] ，元素和 -1 ；记录有效区间和 S(2,2) 。
	//故，共有 3 个有效区间和。
	//
	//
	//
	// 提示：
	//
	//
	// 0 <= nums.length <= 10^4
	//
	// Related Topics 排序 树状数组 线段树 二分查找 分治算法
	// 👍 298 👎 0

	@Test
	public void test() {
		Solution solution = new Solution();
		int[] nums = ArrayUtil.randomIntArray(10, 1, 50);
		solution.countRangeSum(nums, 1, 40);
	}

	@Test
	public void test2() {
		Solution solution = new Solution();
		int[] nums = new int[] {-2, 5, -1};
		int count = solution.countSum3(nums, -2, 2);
		log.info("符合预期的数量:{}", count);
	}
	//leetcode submit region end(Prohibit modification and deletion)

	//leetcode submit region begin(Prohibit modification and deletion)
	class Solution {

		public int countRangeSum(int[] nums, int lower, int upper) {
			int count = 0;
			for (int length = 1; length <= nums.length; length++) {
				int start = 0;
				while (start + length <= nums.length) {
					long rangeValue = 0;
					for (int i = start; i < start + length; i++) {
						rangeValue = rangeValue + nums[i];
					}
					if (rangeValue >= lower && rangeValue <= upper) {
						count++;
					}
					start++;
				}
			}
			return count;
		}

		public int countRangeSum2(int[] nums, int lower, int upper) {
			int[] sum = new int[nums.length];
			sum[0] = nums[0];
			for (int i = 1; i < nums.length; i++) {
				sum[i] = sum[i - 1] + nums[i];
			}

			return mergeCount(sum, 0, sum.length - 1, lower, upper);

		}

		public int mergeCount(int[] sum, int leftIndex, int rightIndex, int lower, int upper) {

			if (leftIndex == rightIndex) {
				if (sum[leftIndex] >= lower && sum[leftIndex] <= upper) {
					return 1;
				}
			}

			int middleIndex = leftIndex + ((rightIndex - leftIndex) >> 1);
			int leftCount = mergeCount(sum, leftIndex, middleIndex, lower, upper);
			int rightCount = mergeCount(sum, middleIndex + 1, rightIndex, lower, upper);
			int mergeCount = mergeCount(sum, leftCount, middleIndex, rightCount, lower, upper);
			return leftCount + rightCount + mergeCount;
		}

		public int mergeCount(int[] arr, int L, int M, int R, int lower, int upper) {
			int ans = 0;
			int windowL = L;
			int windowR = L;
			// [windowL, windowR)
			for (int i = M + 1; i <= R; i++) {
				long min = arr[i] - upper;
				long max = arr[i] - lower;
				while (windowR <= M && arr[windowR] <= max) {
					windowR++;
				}
				while (windowL <= M && arr[windowL] < min) {
					windowL++;
				}
				ans += windowR - windowL;
			}
			long[] help = new long[R - L + 1];
			int i = 0;
			int p1 = L;
			int p2 = M + 1;
			while (p1 <= M && p2 <= R) {
				help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
			}
			while (p1 <= M) {
				help[i++] = arr[p1++];
			}
			while (p2 <= R) {
				help[i++] = arr[p2++];
			}
			for (i = 0; i < help.length; i++) {
				arr[L + i] = (int)help[i];
			}
			return ans;
		}

		public int countRangeSum3(int[] nums, int lower, int upper) {
			if (nums == null || nums.length == 0) {
				return 0;
			}
			long[] sum = new long[nums.length];
			sum[0] = nums[0];
			for (int i = 1; i < nums.length; i++) {
				sum[i] = sum[i - 1] + nums[i];
			}
			return process(sum, 0, sum.length - 1, lower, upper);
		}

		public int process(long[] sum, int L, int R, int lower, int upper) {
			if (L == R) {
				return sum[L] >= lower && sum[L] <= upper ? 1 : 0;
			}
			int M = L + ((R - L) >> 1);
			return process(sum, L, M, lower, upper) + process(sum, M + 1, R, lower, upper)
				+ merge(sum, L, M, R, lower, upper);
		}

		public int merge(long[] arr, int L, int M, int R, int lower, int upper) {
			int ans = 0;
			int windowL = L;
			int windowR = L;
			// [windowL, windowR)
			for (int i = M + 1; i <= R; i++) {
				long min = arr[i] - upper;
				long max = arr[i] - lower;
				while (windowR <= M && arr[windowR] <= max) {
					windowR++;
				}
				while (windowL <= M && arr[windowL] < min) {
					windowL++;
				}
				ans += windowR - windowL;
			}
			long[] help = new long[R - L + 1];
			int i = 0;
			int p1 = L;
			int p2 = M + 1;
			while (p1 <= M && p2 <= R) {
				help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
			}
			while (p1 <= M) {
				help[i++] = arr[p1++];
			}
			while (p2 <= R) {
				help[i++] = arr[p2++];
			}
			for (i = 0; i < help.length; i++) {
				arr[L + i] = help[i];
			}
			return ans;
		}

		public int countSum3(int[] arr, int lower, int upper) {

			long[] sum = getSumArr(arr);

			return mergeCount3(sum, 0, sum.length - 1, lower, upper);
		}

		public long[] getSumArr(int[] arr) {
			long[] sum = new long[arr.length];
			sum[0] = arr[0];
			for (int i = 1; i < arr.length; i++) {
				sum[i] = sum[i - 1] + arr[i];
			}
			return sum;
		}

		public int mergeCount3(long[] sum, int leftIndex, int rightIndex, int lower, int upper) {
			if (leftIndex == rightIndex) {
				if (sum[leftIndex] >= lower && sum[leftIndex] <= upper) {
					return 1;
				} else {
					return 0;
				}
			}
			int middleIndex = leftIndex + ((rightIndex - leftIndex) >> 1);
			int leftCount = mergeCount3(sum, leftIndex, middleIndex, lower, upper);
			int rightCount = mergeCount3(sum, middleIndex + 1, rightIndex, lower, upper);
			int mergeCount = mergerCount3(sum, leftIndex, middleIndex, rightIndex, lower, upper);
			return leftCount + rightCount + mergeCount;
		}

		public int mergerCount3(long[] sum, int leftIndex, int middleIndex, int rightIndex, int lower, int upper) {
			int windowL = leftIndex;
			int windowR = leftIndex;
			int count = 0;

			for (int i = middleIndex + 1; i <= rightIndex; i++) {
				long tempLower = sum[i] - upper;
				long tempUpper = sum[i] - lower;

				while (windowL <= middleIndex && sum[windowL] < tempLower) {
					windowL++;
				}
				while (windowR <= middleIndex && sum[windowR] <= tempUpper) {
					windowR++;
				}

				count = count + (windowR - windowL);
			}

			long[] result = new long[rightIndex - leftIndex + 1];
			int leftStart = leftIndex;
			int rightStart = middleIndex + 1;
			int resultIndex = 0;
			while (resultIndex < result.length) {
				if (sum[leftStart] <= sum[rightStart]) {
					result[resultIndex] = sum[leftStart];
					leftStart++;
				} else {
					result[resultIndex] = sum[rightStart];
					rightStart++;
				}
				resultIndex++;
				if (leftStart > middleIndex) {
					while (rightStart <= rightIndex) {
						result[resultIndex] = sum[rightStart];
						rightStart++;
						resultIndex++;
					}
					break;
				}

				if (rightStart > rightIndex) {
					while (leftStart <= middleIndex) {
						result[resultIndex] = sum[leftStart];
						leftStart++;
						resultIndex++;
					}
					break;
				}
			}
			for (int i = 0; i < result.length; i++) {
				sum[leftIndex + i] = result[i];
			}
			return count;
		}
	}

}

package course;

import java.util.PriorityQueue;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import util.ArrayUtil;

/**
 * @author by catface
 * @date 2021/2/24 10:58 上午
 */
@Slf4j
public class HeapSort {

    public static void heapInsert(int[] arr, int index) {

        if (index >= arr.length) {
            return;
        }

        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    public static void heapify(int[] arr, int index, int heapSize) {
        int leftChildIndex = index * 2 + 1;
        while (leftChildIndex < heapSize) {
            // 没有右侧孩子
            int rightChildIndex = index * 2 + 2;
            if (rightChildIndex >= heapSize) {
                log.info("parent:{},只有左孩子,没有右孩子", index);
                if (arr[leftChildIndex] > arr[index]) {
                    log.info("左孩子大于根节点,交换左孩子和根节点,根节点位置:{}", index);
                    swap(arr, leftChildIndex, index);
                }
            } else {
                if (arr[leftChildIndex] > arr[rightChildIndex] && arr[leftChildIndex] > arr[index]) {
                    log.info("左侧孩子大于右侧孩子,且大于根节点,交换左侧孩子和根节点,根节点位置:{}", index);
                    swap(arr, leftChildIndex, index);
                    return;
                }
                if (arr[rightChildIndex] > arr[leftChildIndex] && arr[rightChildIndex] > arr[index]) {
                    log.info("右侧孩子大于左侧孩子,且大于根节点,交换右侧孩子和根节点,根节点位置:{}", index);
                    swap(arr, leftChildIndex, index);
                    return;
                }
            }
            log.info("根节点最大,无需交换,根节点index:{},value:{}", index, arr[index]);

        }

    }

    public static void swap(int[] sourceArray, int a, int b) {
        int temp = sourceArray[a];
        sourceArray[a] = sourceArray[b];
        sourceArray[b] = temp;
    }

    public static void heapify2(int[] arr, int index, int heapSize) {
        log.info("开始调整的位置:{},节点值:{}", index, arr[index]);
        int currentIndex = index;
        int leftIndex = currentIndex * 2 + 1;
        while (leftIndex < heapSize) {
            // 检查largestIndex 是不是左孩子
            int largestIndex = arr[leftIndex] > arr[currentIndex] ? leftIndex : currentIndex;
            // 检查largestIndex 是不是右孩子
            largestIndex = (leftIndex + 1) < heapSize && arr[leftIndex + 1] > arr[largestIndex] ? leftIndex + 1
                : largestIndex;
            // 如果最大值是当前节点,直接退出
            if (largestIndex == currentIndex) {
                log.info("最大值是根节点,退出,位置:{}", currentIndex);
                return;
            }
            log.info("交换前:{}={},{}={}之后{}", currentIndex, arr[currentIndex], largestIndex, arr[largestIndex], arr);
            swap(arr, currentIndex, largestIndex);
            log.info("交换前:{}={},{}={}之后{}", currentIndex, arr[currentIndex], largestIndex, arr[largestIndex], arr);
            currentIndex = largestIndex;
            leftIndex = currentIndex * 2 + 1;
        }
        log.info("叶子节点,位置:{},节点值:{}", currentIndex, arr[currentIndex]);
    }

    public void sort1(int[] sourceArray) {

        // 构建最大堆,从最后一个非叶子节点开始,  最后一个节点是 sourceArray.length - 1,最后一个非叶子节点是 sourceArray.length / 2 - 1
        for (int i = sourceArray.length / 2 - 1; i >= 0; i--) {
            adjustHeap3(sourceArray, i, sourceArray.length);
        }

        // 调整堆结构+交换堆顶元素和末尾的袁术,堆顶元素下标始终为 0,每次都会把最堆顶的最大值,交换到数组的最
        for (int i = sourceArray.length - 1; i > 0; i--) {

            // 交换堆顶元素和末位元素
            swap(sourceArray, 0, i);

            // 交换后,只要做一次调整,就会是新的大顶堆
            adjustHeap3(sourceArray, 0, i);
        }

    }

    /**
     * 调整最大堆
     *
     * @param sourceArray 原始数组
     * @param i           当前元素
     * @param length      指定的长度位置,非数组的真实长度
     */
    public void adjustMaxHeap(int[] sourceArray, int i, int length) {
        // 先取出当前元素i
        int temp = sourceArray[i];
        // 从i结点的左子结点开始，也就是2i+1处开始
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            // 如果左子结点小于右子结点，k指向右子结点
            if (k + 1 < length && sourceArray[k] < sourceArray[k + 1]) {
                k++;
            }
            if (sourceArray[k] > temp) {
                // 如果子节点大于父节点，将子节点值赋给父节点（不用进行交换）
                sourceArray[i] = sourceArray[k];
                i = k;
            } else {
                break;
            }
        }
        // 将temp值放到最终的位置
        sourceArray[i] = temp;
    }

    public void adjustHeap2(int[] arr, int parent, int length) {

        int parentValue = arr[parent];
        int leftChildIndex = parent * 2 + 1;

        while (leftChildIndex < length) {
            int rightChildIndex = leftChildIndex + 1;
            if (rightChildIndex < leftChildIndex && arr[leftChildIndex] < arr[rightChildIndex]) {
                leftChildIndex++;
            }

            if (parentValue >= arr[leftChildIndex]) {
                break;
            }

            arr[parent] = arr[leftChildIndex];

            parent = leftChildIndex;

            leftChildIndex = parent * 2 + 1;

        }

        arr[parent] = parentValue;

    }

    public void adjustHeap3(int[] arr, int parentIndex, int length) {

        if (parentIndex >= length) {
            return;
        }

        int parentValue = arr[parentIndex];

        // 优先取
        int leftChildIndex = parentIndex * 2 + 1;
        int maxChildIndex = leftChildIndex;
        if (leftChildIndex >= length) {
            return;
        }

        int rightChildIndex = leftChildIndex + 1;
        if (rightChildIndex < length && arr[rightChildIndex] > arr[leftChildIndex]) {
            maxChildIndex = rightChildIndex;
        }

        if (parentValue >= arr[maxChildIndex]) {
            return;
        }

        arr[parentIndex] = arr[maxChildIndex];

        arr[maxChildIndex] = parentValue;

        adjustHeap3(arr, maxChildIndex, length);

    }

    public void sort4(int[] arr) {

        // 将原始数组调整成大顶堆,从最底层的,最末尾的叶子节点的父节点开始调整
        // parent--,代买
        for (int parent = arr.length / 2 - 1; parent >= 0; parent--) {
            adjustHeap4(arr, parent, arr.length);
        }

        // 每次交换头尾后,只需要重新从堆顶开始往下调整一遍即可
        for (int length = arr.length - 1; length > 0; length--) {
            swap(arr, 0, length);
            adjustHeap4(arr, 0, length);
        }

    }

    public void adjustHeap4(int[] arr, int parentIndex, int length) {

        if (parentIndex >= length) {
            return;
        }

        // 获取根节点的值
        int parentValue = arr[parentIndex];
        // 检查左侧子节点是否存在,不存在则直接退出
        int leftChildIndex = parentIndex * 2 + 1;
        if (leftChildIndex >= length) {
            return;
        }

        // 检查右侧子节点是否存在,如果存在,且节点值大约左侧节点,则最大子节点的下标取右侧,否则取左侧
        int maxChildIndex = leftChildIndex;
        int rightChildIndex = leftChildIndex + 1;
        if (rightChildIndex < length && arr[rightChildIndex] > arr[leftChildIndex]) {
            maxChildIndex = rightChildIndex;
        }

        // 比较最大子节点的值和根节点的值,如果根节点的值大于最大子节点的值,则无需调整,直接退出
        // 否则,交换根节点和最大子节点的值,让后以最大子节点为跟节点,继续调整
        if (parentValue >= arr[maxChildIndex]) {
            return;
        }

        arr[parentIndex] = arr[maxChildIndex];
        arr[maxChildIndex] = parentValue;
        adjustHeap4(arr, maxChildIndex, length);
    }

    @Test
    public void testSort1() {
        int[] sourceArray = new int[] {4, 6, 8, 5, 9};
        log.info("排序前:{}", sourceArray);
        sort4(sourceArray);
        log.info("排序后:{}", sourceArray);
    }

    @Test
    public void testHeapSort() {
        int totalNum = 0;
        for (int size = 1; size < 100; size++) {
            for (int i = 0; i < 1000; i++) {
                int[] originalArray = ArrayUtil.randomIntArray(size, 1, 100);
                int[] sourceArray = ArrayUtil.clone(originalArray);
                int[] bubbleSortResult = BubbleSort.bubbleSort(originalArray);
                sort4(originalArray);
                boolean checkResult = ArrayUtil.checkSortResult(bubbleSortResult, originalArray);
                totalNum++;
                if (!checkResult) {
                    log.info("原始数组:{}", sourceArray);
                    log.info("原始数组:{},冒泡排序后的数组{}", originalArray, bubbleSortResult);
                    log.info("原始数组:{},归并排序(迭代)后的数组{}", sourceArray, originalArray);
                    log.info("比对结果:{}", checkResult);
                }
            }
        }
        log.info("{}次全部比对通过", totalNum);
    }

    @Test
    public void testHeapInsert() {
        int[] arr = ArrayUtil.randomIntArray(10, 1, 100);
        log.info("arr:{}", arr);
        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, i);
            log.info("第{}次heapInsert后,arr:{}", i + 1, arr);
        }
        log.info("arr:{}", arr);
    }

    @Test
    public void testHeapify() {

        //int[] arr = ArrayUtil.randomIntArray(10, 1, 100);
        //int[] arr = new int[]{62, 90, 32, 93, 84, 99, 86, 9, 69, 8};
        //log.info("arr:{}", arr);
        //for (int i = arr.length - 1; i >= 0; i--) {
        //    log.info("开始第{}次heapify,arr:{}", arr.length - i, arr);
        //    heapify2(arr, i, arr.length);
        //    log.info("结束第{}次heapify,arr:{}", arr.length - i, arr);
        //    log.info("");
        //}
        //log.info("arr:{}", arr);

        PriorityQueue<Integer> heap = new PriorityQueue<Integer>();
        heap.add(1);
        heap.add(2);
        heap.add(10);
        heap.add(4);
        heap.add(12);

        Integer data1 = heap.poll();
        log.info("{}", data1);
        Integer data2 = heap.poll();
        log.info("{}", data2);
        Integer data3 = heap.poll();
        log.info("{}", data3);
        Integer data4 = heap.poll();
        log.info("{}", data4);
        Integer data5 = heap.poll();
        log.info("{}", data5);

    }

}

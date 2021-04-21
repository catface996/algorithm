package course.堆;

import org.junit.Test;
import util.ArrayUtil;

/**
 * @author by catface
 * @date 2021/4/21 3:43 下午
 */
public class Heap {

    int size;
    private int[] arr;

    public Heap(int size) {
        arr = new int[size];
        this.size = 0;
    }

    public Heap(int[] arr, int size) {
        this.arr = new int[size];
        for (int i = 0; i < arr.length; i++) {
            this.arr[i] = arr[i];
        }
        this.size = arr.length;
        adjust();
    }

    public void push(int value) {
        if (size >= arr.length) {
            int[] tempArr = new int[arr.length * 2];
            System.arraycopy(arr, 0, tempArr, 0, size);
            arr = tempArr;
        }
        arr[size] = value;
        heapInsert(size++);
    }

    public int pop() {
        int value = this.arr[0];
        arr[0] = arr[size - 1];
        size--;
        heapify(0);
        return value;
    }

    public boolean isEmpty() {
        return size <= 0;
    }

    public void heapInsert(int index) {
        while (arr[index] < arr[(index - 1) / 2]) {
            swap(index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    public void heapify(int index) {
        int left = index * 2 + 1;
        while (left < size) {
            int swapIndex = left + 1 < size && arr[left + 1] < arr[left] ? left + 1 : left;
            swapIndex = arr[swapIndex] < arr[index] ? swapIndex : index;
            if (swapIndex == index) {
                return;
            }
            swap(index, swapIndex);
            index = swapIndex;
            left = index * 2 + 1;
        }
    }

    /**
     * 从最后一个非叶子节点连续做heapify,可以实现复杂度为O(N)的堆构建
     */
    private void adjust() {
        for (int i = (size - 1) / 2; i >= 0; i--) {
            heapify(i);
        }
    }

    private void swap(int i, int j) {
        if (i == j) {
            return;
        }
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    public static class TestClass {
        @Test
        public void test1() {
            int[] arr = ArrayUtil.randomIntArray(14, 1, 40);
            Heap heap = new Heap(arr, 31);
            while (!heap.isEmpty()) {
                int value = heap.pop();
                System.out.println(value);
            }
        }

        @Test
        public void test2() {
            int value = -1 / 2;
            System.out.println(value);
        }

        @Test
        public void test3() {
            int[] arr = ArrayUtil.randomIntArray(15, 1, 100);
            int[] arr2 = ArrayUtil.randomIntArray(16, 1, 100);
            Heap heap = new Heap(arr, 15);
            for (int i : arr2) {
                heap.push(i);
            }
            while (!heap.isEmpty()) {
                int value = heap.pop();
                System.out.println(value);
            }
        }
    }
}

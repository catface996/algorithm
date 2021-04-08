package course;

import org.junit.Test;

/**
 * @author by catface
 * @date 2020/11/03
 */
public class ValidMountainArray {

    public boolean validMountainArray(int[] A) {

        int maxValueIndex = 0;
        for (int i = 0; i < A.length - 1; i++) {
            if (A[i] < A[i + 1]) {
                continue;
            }
            if (i == 0 || i == A.length - 1) {
                return false;
            }
            // 第一次发生反转,即发现 第i+1个数比第i个数小
            maxValueIndex = i;
            break;
        }

        for (int i = maxValueIndex; i < A.length - 1; i++) {
            if (A[i] > A[i + 1]) {
                continue;
            }
            if (i == maxValueIndex || i < A.length - 1) {
                return false;
            }
        }

        return true;
    }

    public boolean validMountainArray2(int[] A) {
        return validMountainArray(A, 0, A.length - 1);
    }

    public boolean validMountainArray(int[] A, int start, int end) {
        int range = end - start;
        if (range > 2) {
            int middle = (start + end) / 2;
            if (isAsc(A, middle - 1, middle + 1)) {
                boolean asc = isAsc(A, start, middle);
                if (!asc) {
                    return false;
                }
                boolean mountain = validMountainArray(A, middle, end);
                if (!mountain) {
                    return false;
                }
                return true;
            }

            if (isDesc(A, middle - 1, middle + 1)) {
                boolean desc = isDesc(A, middle, end);
                if (!desc) {
                    return false;
                }
                boolean mountain = validMountainArray(A, start, middle);
                if (!mountain) {
                    return false;
                }
                return true;
            }
            if (validMountainArray(A, middle - 1, middle + 1)) {
                boolean asc = isAsc(A, start, middle - 1);
                if (!asc) {
                    return false;
                }
                boolean desc = isDesc(A, middle + 1, end);
                if (!desc) {
                    return false;
                }
                return true;
            }

        }

        if (range == 2) {
            int middle = (start + end) / 2;
            if (A[middle] > A[start] && A[middle] > A[end]) {
                return true;
            } else {
                return false;
            }
        }

        return false;

    }

    private boolean isAsc(int[] A, int start, int end) {
        for (; start < end; start++) {
            if (A[start] < A[start + 1]) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    private boolean isDesc(int[] A, int start, int end) {
        for (; start < end; start++) {
            if (A[start] > A[start + 1]) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    @Test
    public void test() {
        int[] A = {1, 2, 3, 4, 5, 6, 3, 2};
        int[] B = {5, 4, 3, 2, 1};
        int[] C = {1, 2, 3, 4, 5};
        int[] D = {10, 20, 30, 40, 50, 20, 30, 10, 9};
        int[] E = {10, 20, 30, 40, 50, 50, 30, 10, 9};
        boolean resultA = validMountainArray(A);
        System.out.println(resultA);
        boolean resultB = validMountainArray(B);
        System.out.println(resultB);
        boolean resultC = validMountainArray(C);
        System.out.println(resultC);
        boolean resultD = validMountainArray(D);
        System.out.println(resultD);
        boolean resultE = validMountainArray(E);
        System.out.println(resultE);
    }

    @Test
    public void test2() {

        int[] A = {1, 2, 3, 4, 5, 6, 3, 2};
        int[] B = {5, 4, 3, 2, 1};
        int[] C = {1, 2, 3, 4, 5};
        int[] D = {10, 20, 30, 40, 50, 20, 30, 10, 9};
        int[] E = {10, 20, 30, 40, 50, 50, 30, 10, 9};
        boolean resultA = validMountainArray2(A);
        System.out.println(resultA);
        boolean resultB = validMountainArray2(B);
        System.out.println(resultB);
        boolean resultC = validMountainArray2(C);
        System.out.println(resultC);
        boolean resultD = validMountainArray2(D);
        System.out.println(resultD);
        boolean resultE = validMountainArray2(E);
        System.out.println(resultE);

    }

}

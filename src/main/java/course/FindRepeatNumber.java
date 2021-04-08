package course;

import org.junit.Test;

/**
 * @author by catface
 * @date 2020/11/03
 */
public class FindRepeatNumber {

    private Node[] nodeList;

    public int findRepeatNumber(int[] nums) {
        int size = nums.length;
        nodeList = new Node[size];
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            int index = num % size;
            Node node = nodeList[index];
            if (node == null) {
                nodeList[index] = new Node(num);
                continue;
            }

            do {
                if (node.getNum() == num) {
                    return num;
                }
                Node next = node.getNext();
                if (next == null) {
                    node.setNext(new Node(num));
                    break;
                }
                node = next;
            } while (true);
        }
        return -1;
    }

    class Node {

        int num;

        Node next;

        public Node(int num) {
            this.num = num;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

    }

    private int findRepeatNumber2(int[] nums) {
        boolean[] bitmap = new boolean[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            boolean repeat = bitmap[num];
            if (repeat) {
                return num;
            } else {
                bitmap[num] = true;
            }
        }
        return -1;
    }

    @Test
    public void test() {
        int[] numsA = {2, 3, 1, 0, 2, 5, 3};
        int resultA = findRepeatNumber(numsA);
        System.out.println(resultA);

        int[] numsB = {6, 3, 1, 0, 2, 5, 3};
        int resultB = findRepeatNumber(numsB);
        System.out.println(resultB);
    }

    @Test
    public void test2() {
        int[] numsA = {2, 3, 1, 0, 2, 5, 3};
        int resultA = findRepeatNumber2(numsA);
        System.out.println(resultA);

        int[] numsB = {6, 3, 1, 0, 2, 5, 3};
        int resultB = findRepeatNumber2(numsB);
        System.out.println(resultB);
    }

}

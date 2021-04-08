package course;

import java.util.HashSet;

/**
 * @author by catface
 * @date 2021/3/22 5:15 下午
 */
public class LinkList<T> {

    private LinkNode<T> head;

    public static <T> LinkNode<T> findJoinNodeWithHashSet(LinkList<T> list1, LinkList<T> list2) {
        HashSet<LinkNode<T>> set = new HashSet<>();
        LinkNode<T> a = list1.head;
        LinkNode<T> b = list2.head;
        while (a != null) {
            if (set.contains(a)) {
                break;
            }
            set.add(a);
            a = a.next;
        }

        while (b != null) {
            if (set.contains(b)) {
                return b;
            }
            b = b.next;
        }
        return null;
    }

    public static <T> LinkNode<T> findJoinNode(LinkList<T> list1, LinkList<T> list2) {
        LinkNode<T> loopNode1 = list1.findFirstEnterRingNode();
        LinkNode<T> loopNode2 = list2.findFirstEnterRingNode();

        if (loopNode1 == null && loopNode2 == null) {
            return noLoop(list1, list2);
        }

        if (loopNode1 != null && loopNode2 != null) {
            return bothLoop(list1, loopNode1, list2, loopNode2);
        }

        // 一条链表有环,另外一条链表无环,两条链表一定不相交
        return null;
    }

    /**
     * 对两个没有环的链表做相交检测
     *
     * @param list1 第一条链表
     * @param list2 第二条联调
     * @param <T>   泛型
     * @return 两条无环链表的相交节点
     */
    private static <T> LinkNode<T> noLoop(LinkList<T> list1, LinkList<T> list2) {
        LinkNode<T> head1 = list1.head;
        LinkNode<T> head2 = list2.head;
        if (head1 == null || head2 == null) {
            return null;
        }

        // 如果两个链表都没有环且相交,必定遍历到最后一个节点是相同的
        LinkNode<T> currentNode1 = head1;
        LinkNode<T> currentNode2 = head2;
        int n = 0;
        while (currentNode1.next != null) {
            n++;
            currentNode1 = currentNode1.next;
        }
        while (currentNode2.next != null) {
            n--;
            currentNode2 = currentNode2.next;
        }
        // 如果两个链表遍历到各自的最后节点后,最后节点非同一个节点,说明两个链表没有相交
        if (currentNode1 != currentNode2) {
            return null;
        }

        // 选取长度大的链表为第一条链表
        currentNode1 = n > 0 ? head1 : head2;
        // 选取剩余一条为第二条链表
        currentNode2 = currentNode1 == head1 ? head2 : head1;
        // 链表一和链表二的长度差为n的绝对值
        n = Math.abs(n);
        // 长度大的链表一,首先遍历n次
        while (n != 0) {
            if (currentNode1 == null) {
                return null;
            }
            n--;
            currentNode1 = currentNode1.next;
        }
        // 长度大的链表遍历n次后,此时两条链表
        while (currentNode1 != currentNode2) {
            if (currentNode1 == null || currentNode2 == null) {
                return null;
            }
            currentNode1 = currentNode1.next;
            currentNode2 = currentNode2.next;
        }
        return currentNode1;
    }

    /**
     * 如果两条链表均有环
     *
     * @param list1     链表1
     * @param loopNode1 链表1的入环节点
     * @param list2     链表2
     * @param loopNode2 链表2的入环节点
     * @param <T>       泛型
     * @return 两条均有环的链表的相交节点
     */
    private static <T> LinkNode<T> bothLoop(LinkList<T> list1, LinkNode<T> loopNode1, LinkList<T> list2,
                                            LinkNode<T> loopNode2) {
        LinkNode<T> currentNode1 = null;
        LinkNode<T> currentNode2 = null;
        if (loopNode1 == loopNode2) {
            // 第一条链表的入环节点和第二条链表的入环节点是同一个,说明链表先相交,后入环,或者相交和入环的节点是同一个
            currentNode1 = list1.head;
            currentNode2 = list2.head;
            int n = 0;
            // 两条链表均从头结点开始,遍历到入环处,较长的链表必定比较短的链表多遍历abs(n)个节点.
            while (currentNode1 != loopNode1) {
                n++;
                currentNode1 = currentNode1.next;
            }
            while (currentNode2 != loopNode2) {
                n--;
                currentNode2 = currentNode2.next;
            }

            // 取较长的链表的头结点为第node1,另外一条为node2
            currentNode1 = n > 0 ? list1.head : list2.head;
            currentNode2 = currentNode1 == list1.head ? list2.head : list1.head;
            n = Math.abs(n);
            while (n != 0) {
                n--;
                currentNode1 = currentNode1.next;
            }
            while (currentNode1 != currentNode2) {
                currentNode1 = currentNode1.next;
                currentNode2 = currentNode2.next;
            }
            return currentNode1;

        } else {
            // 两条链表的入环节点不一致,说明,两条链在不同的节点位置入环
            currentNode1 = loopNode1.next;
            while (currentNode1 != loopNode1) {
                if (currentNode1 == loopNode2) {
                    return currentNode1;
                }
                currentNode1 = currentNode1.next;
            }
            return null;
        }
    }

    public LinkNode<T> getHead() {
        return head;
    }

    public void setHead(LinkNode<T> head) {
        this.head = head;
    }

    public LinkNode<T> midOrUpperNode() {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }
        LinkNode<T> slow = head.next;
        LinkNode<T> fast = head.next.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public LinkNode<T> midOrDown() {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }
        LinkNode<T> slow = head.next;
        LinkNode<T> fast = head.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public boolean haveRing() {
        if (head == null) {
            return false;
        }
        if (head.next == null || head.next.next == null) {
            return false;
        }
        if (head.next == head) {
            return true;
        }

        LinkNode<T> oneStepPoint = head.next;
        LinkNode<T> twoStepPoint = head.next.next;

        while (twoStepPoint.next != null && twoStepPoint.next.next != null) {
            if (oneStepPoint == twoStepPoint) {
                return true;
            }
            oneStepPoint = oneStepPoint.next;
            twoStepPoint = twoStepPoint.next.next;
        }
        return false;
    }

    public LinkNode<T> findFirstEnterRingNode() {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        if (head.next == head) {
            return head;
        }
        LinkNode<T> fastPoint = head;
        LinkNode<T> slowPoint = head;
        while (fastPoint.next != null && fastPoint.next.next != null) {
            fastPoint = fastPoint.next.next;
            slowPoint = slowPoint.next;
            if (fastPoint == slowPoint) {
                fastPoint = head;
                while (fastPoint != slowPoint) {
                    fastPoint = fastPoint.next;
                    slowPoint = slowPoint.next;
                }
                return fastPoint;
            }
        }
        return null;
    }

    public static class LinkNode<T> {
        private T value;
        private LinkNode<T> next;

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public LinkNode<T> getNext() {
            return next;
        }

        public void setNext(LinkNode<T> next) {
            this.next = next;
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }

        @Override
        public boolean equals(Object o) {
            return this == o;
        }
    }
}

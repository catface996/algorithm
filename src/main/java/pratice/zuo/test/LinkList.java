package pratice.zuo.test;

/**
 * @author by catface
 * @date 2021/3/22 5:15 下午
 */
public class LinkList<T> {

    private LinkNode<T> head;

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
    }
}

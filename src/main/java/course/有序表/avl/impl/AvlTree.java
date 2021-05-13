package course.有序表.avl.impl;

import org.junit.Test;
import util.PrintAvlUtil;

/**
 * @author by catface
 * @date 2021/5/12 3:31 下午
 */
public class AvlTree<K extends Comparable<K>, V> {

    private AvlNode<K, V> root;
    private int size;

    public void put(K key, V value) {
        if (key == null) {
            return;
        }
        AvlNode<K, V> lastNode = findLastIndex(key);
        if (lastNode != null && key.compareTo(lastNode.key) == 0) {
            lastNode.value = value;
        } else {
            size++;
            root = add(root, key, value);
        }
    }

    private AvlNode<K, V> findLastIndex(K key) {
        AvlNode<K, V> pre = root;
        AvlNode<K, V> cur = root;
        while (cur != null) {
            pre = cur;
            if (key.compareTo(cur.key) == 0) {
                break;
            } else if (key.compareTo(cur.key) < 0) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        return pre;
    }

    /**
     * avl树种增加新节点
     *
     * @param cur   当前节点
     * @param key   要添加的key
     * @param value 要添加的value
     * @return 添加后的节点
     */
    private AvlNode<K, V> add(AvlNode<K, V> cur, K key, V value) {
        if (cur == null) {
            return new AvlNode<>(key, value);
        }

        if (key.compareTo(cur.key) < 0) {
            cur.left = add(cur.left, key, value);
        } else {
            cur.right = add(cur.right, key, value);
        }
        int leftHeight = cur.left == null ? 0 : cur.left.height;
        int rightHeight = cur.right == null ? 0 : cur.right.height;
        cur.height = Math.max(leftHeight, rightHeight) + 1;
        return maintain(cur);
    }

    private AvlNode<K, V> maintain(AvlNode<K, V> cur) {
        if (cur == null) {
            return null;
        }
        int leftHeight = cur.left == null ? 0 : cur.left.height;
        int rightHeight = cur.right == null ? 0 : cur.right.height;
        if (Math.abs(leftHeight - rightHeight) > 1) {
            if (leftHeight > rightHeight) {
                int leftLeftHeight = cur.left != null && cur.left.left != null ? cur.left.left.height : 0;
                int leftRightHeight = cur.left != null && cur.left.right != null ? cur.left.right.height : 0;
                if (leftLeftHeight < leftRightHeight) {
                    cur.left = leftRotate(cur.left);
                }
                cur = rightRotate(cur);
            } else {
                int rightLeftHeight = cur.right != null && cur.right.left != null ? cur.right.left.height : 0;
                int rightRightHeight = cur.right != null && cur.right.right != null ? cur.right.right.height : 0;
                if (rightRightHeight < rightLeftHeight) {
                    cur.right = rightRotate(cur.right);
                }
                cur = leftRotate(cur);
            }
        }
        return cur;
    }

    private AvlNode<K, V> rightRotate(AvlNode<K, V> cur) {
        AvlNode<K, V> newHead = cur.left;
        cur.left = newHead.right;
        newHead.right = cur;
        cur.height = Math.max(cur.left == null ? 0 : cur.left.height, cur.right == null ? 0 : cur.right.height) + 1;
        // left.right 一定不为空
        newHead.height = Math.max(newHead.left == null ? 0 : newHead.left.height, newHead.right.height) + 1;
        return newHead;
    }

    private AvlNode<K, V> leftRotate(AvlNode<K, V> cur) {
        AvlNode<K, V> newHead = cur.right;
        cur.right = newHead.left;
        newHead.left = cur;
        cur.height = Math.max(cur.right == null ? 0 : cur.right.height, cur.left == null ? 0 : cur.left.height) + 1;
        // newHead.left 一定不为空
        newHead.height = Math.max(newHead.left.height, newHead.right == null ? 0 : newHead.right.height) + 1;
        return newHead;
    }

    public static class TestClass {
        @Test
        public void test() {
            AvlTree<Integer, Integer> avlTree = new AvlTree<>();
            avlTree.put(1, 1);
            avlTree.put(2, 2);
            avlTree.put(3, 3);
            avlTree.put(4, 4);
            avlTree.put(5, 5);
            avlTree.put(6, 6);
            avlTree.put(7, 7);
            PrintAvlUtil.printTree(avlTree.root);
        }
    }
}

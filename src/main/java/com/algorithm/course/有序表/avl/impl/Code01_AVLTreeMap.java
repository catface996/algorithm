package com.algorithm.course.有序表.avl.impl;

import com.algorithm.util.PrintAvlUtil;
import org.junit.Test;

public class Code01_AVLTreeMap {

    public static class AVLTreeMap<K extends Comparable<K>, V> {
        private AvlNode<K, V> root;
        private int size;

        public AVLTreeMap() {
            root = null;
            size = 0;
        }

        private AvlNode<K, V> rightRotate(AvlNode<K, V> cur) {
            AvlNode<K, V> left = cur.left;
            cur.left = left.right;
            left.right = cur;
            cur.height = Math.max((cur.left != null ? cur.left.height : 0), (cur.right != null ? cur.right.height : 0))
                + 1;
            left.height = Math.max((left.left != null ? left.left.height : 0),
                (left.right != null ? left.right.height : 0)) + 1;
            return left;
        }

        private AvlNode<K, V> leftRotate(AvlNode<K, V> cur) {
            AvlNode<K, V> right = cur.right;
            cur.right = right.left;
            right.left = cur;
            cur.height = Math.max((cur.left != null ? cur.left.height : 0), (cur.right != null ? cur.right.height : 0))
                + 1;
            right.height = Math.max((right.left != null ? right.left.height : 0),
                (right.right != null ? right.right.height : 0)) + 1;
            return right;
        }

        private AvlNode<K, V> maintain(AvlNode<K, V> cur) {
            if (cur == null) {
                return null;
            }
            int leftHeight = cur.left != null ? cur.left.height : 0;
            int rightHeight = cur.right != null ? cur.right.height : 0;
            if (Math.abs(leftHeight - rightHeight) > 1) {
                if (leftHeight > rightHeight) {
                    int leftLeftHeight = cur.left != null && cur.left.left != null ? cur.left.left.height : 0;
                    int leftRightHeight = cur.left != null && cur.left.right != null ? cur.left.right.height : 0;
                    if (leftLeftHeight >= leftRightHeight) {
                        cur = rightRotate(cur);
                    } else {
                        cur.left = leftRotate(cur.left);
                        cur = rightRotate(cur);
                    }
                } else {
                    int rightLeftHeight = cur.right != null && cur.right.left != null ? cur.right.left.height : 0;
                    int rightRightHeight = cur.right != null && cur.right.right != null ? cur.right.right.height : 0;
                    if (rightRightHeight >= rightLeftHeight) {
                        cur = leftRotate(cur);
                    } else {
                        cur.right = rightRotate(cur.right);
                        cur = leftRotate(cur);
                    }
                }
            }
            return cur;
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

        private AvlNode<K, V> findLastNoSmallIndex(K key) {
            AvlNode<K, V> ans = null;
            AvlNode<K, V> cur = root;
            while (cur != null) {
                if (key.compareTo(cur.key) == 0) {
                    ans = cur;
                    break;
                } else if (key.compareTo(cur.key) < 0) {
                    ans = cur;
                    cur = cur.left;
                } else {
                    cur = cur.right;
                }
            }
            return ans;
        }

        private AvlNode<K, V> findLastNoBigIndex(K key) {
            AvlNode<K, V> ans = null;
            AvlNode<K, V> cur = root;
            while (cur != null) {
                if (key.compareTo(cur.key) == 0) {
                    ans = cur;
                    break;
                } else if (key.compareTo(cur.key) < 0) {
                    cur = cur.left;
                } else {
                    ans = cur;
                    cur = cur.right;
                }
            }
            return ans;
        }

        private AvlNode<K, V> add(AvlNode<K, V> cur, K key, V value) {
            if (cur == null) {
                return new AvlNode<K, V>(key, value);
            } else {
                if (key.compareTo(cur.key) < 0) {
                    cur.left = add(cur.left, key, value);
                } else {
                    cur.right = add(cur.right, key, value);
                }
                cur.height = Math.max(cur.left != null ? cur.left.height : 0, cur.right != null ? cur.right.height : 0)
                    + 1;
                return maintain(cur);
            }
        }

        // 在cur这棵树上，删掉key所代表的节点
        // 返回cur这棵树的新头部
        private AvlNode<K, V> delete(AvlNode<K, V> cur, K key) {
            if (key.compareTo(cur.key) > 0) {
                cur.right = delete(cur.right, key);
            } else if (key.compareTo(cur.key) < 0) {
                cur.left = delete(cur.left, key);
            } else {
                if (cur.left == null && cur.right == null) {
                    cur = null;
                } else if (cur.left == null && cur.right != null) {
                    cur = cur.right;
                } else if (cur.left != null && cur.right == null) {
                    cur = cur.left;
                } else {
                    AvlNode<K, V> des = cur.right;
                    while (des.left != null) {
                        des = des.left;
                    }
                    cur.right = delete(cur.right, des.key);
                    des.left = cur.left;
                    des.right = cur.right;
                    cur = des;
                }
            }
            if (cur != null) {
                cur.height = Math.max(cur.left != null ? cur.left.height : 0, cur.right != null ? cur.right.height : 0)
                    + 1;
            }
            return maintain(cur);
        }

        public int size() {
            return size;
        }

        public boolean containsKey(K key) {
            if (key == null) {
                return false;
            }
            AvlNode<K, V> lastNode = findLastIndex(key);
            return lastNode != null && key.compareTo(lastNode.key) == 0 ? true : false;
        }

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

        public void remove(K key) {
            if (key == null) {
                return;
            }
            if (containsKey(key)) {
                size--;
                root = delete(root, key);
            }
        }

        public V get(K key) {
            if (key == null) {
                return null;
            }
            AvlNode<K, V> lastNode = findLastIndex(key);
            if (lastNode != null && key.compareTo(lastNode.key) == 0) {
                return lastNode.value;
            }
            return null;
        }

        public K firstKey() {
            if (root == null) {
                return null;
            }
            AvlNode<K, V> cur = root;
            while (cur.left != null) {
                cur = cur.left;
            }
            return cur.key;
        }

        public K lastKey() {
            if (root == null) {
                return null;
            }
            AvlNode<K, V> cur = root;
            while (cur.right != null) {
                cur = cur.right;
            }
            return cur.key;
        }

        public K floorKey(K key) {
            if (key == null) {
                return null;
            }
            AvlNode<K, V> lastNoBigNode = findLastNoBigIndex(key);
            return lastNoBigNode == null ? null : lastNoBigNode.key;
        }

        public K ceilingKey(K key) {
            if (key == null) {
                return null;
            }
            AvlNode<K, V> lastNoSmallNode = findLastNoSmallIndex(key);
            return lastNoSmallNode == null ? null : lastNoSmallNode.key;
        }

    }

    public static class TestClass {
        @Test
        public void test() {
            AVLTreeMap<Integer, Integer> avlTree = new AVLTreeMap<>();
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

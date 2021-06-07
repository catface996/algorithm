package com.algorithm.course.有序表.avl.impl;

import com.algorithm.util.PrintAvlUtil;
import org.junit.Test;

/**
 * @author by catface
 * @date 2021/5/14 2:01 下午
 */
public class Solution {

    public static class AvlTree<K extends Comparable<K>, V> {

        private AvlNode<K, V> root;
        private int size = 0;

        /**
         * 根据key查找value
         *
         * @param key 待查找的key
         * @return key对应的value
         */
        public V get(K key) {
            return findNodeByKey(key).value;
        }

        /**
         * 向avl树种添加key->value,key存在,则更新value,否则新增
         *
         * @param key   待添加的key
         * @param value 待添加的value
         * @return 如果key对应的value已经存在, 返回原value
         */
        public V put(K key, V value) {
            AvlNode<K, V> target = findNodeByKey(key);
            if (target != null) {
                V oldValue = target.value;
                target.value = value;
                return oldValue;
            }
            root = add(root, key, value);
            size++;
            return null;
        }

        /**
         * 根据指定的key删除相应的value
         *
         * @param key 待删除的key
         * @return 被删除的value
         */
        public V remove(K key) {
            AvlNode<K, V> targetNode = findNodeByKey(key);
            if (targetNode == null) {
                return null;
            }
            root = delete(root, key);
            size--;
            return targetNode.value;
        }

        /**
         * 返回有序表节点数
         *
         * @return 有序表节点数
         */
        public int size() {
            return size;
        }

        /**
         * 查找第一个Key
         *
         * @return 第一个key, 默认从小大, 获取最小的key
         */
        public K firstKey() {
            AvlNode<K, V> cur = root;
            while (cur != null) {
                if (cur.left == null) {
                    return cur.key;
                }
                cur = cur.left;
            }
            return null;
        }

        /**
         * 查找最后一个key
         *
         * @return 最后一个key, 默认从小到大, 获取最大的key
         */
        public K lastKey() {
            AvlNode<K, V> cur = root;
            while (cur != null) {
                if (cur.right == null) {
                    return cur.key;
                }
                cur = cur.right;
            }
            return null;
        }

        /**
         * 返回小于等于指定key中的最大key
         *
         * @param key 限定key的范围
         * @return 在限定范围中, 小于等于指定key的最大key
         */
        public K floorKey(K key) {
            if (key == null) {
                return null;
            }
            AvlNode<K, V> floorNode = findLastNoBigNode(key);
            return floorNode == null ? null : floorNode.key;
        }

        /**
         * 查询比指定的key大或相等的所有key中的最小key
         *
         * @param key 指定的key
         * @return 所有大于等于指定key中的最小key
         */
        public K cellingKey(K key) {
            if (key == null) {
                return null;
            }
            AvlNode<K, V> cellingKey = findFistNoLessNode(key);
            return cellingKey == null ? null : cellingKey.key;
        }

        /**
         * 查找指定的key是否存在,如果存在,返回key对应的节点
         *
         * @param key 待查询的key
         * @return key对应的节点, 可以为null
         */
        private AvlNode<K, V> findNodeByKey(K key) {
            AvlNode<K, V> cur = root;
            while (cur != null) {
                int cmp = key.compareTo(cur.key);
                if (cmp == 0) {
                    break;
                }
                if (cmp > 0) {
                    cur = cur.right;
                } else {
                    cur = cur.left;
                }
            }
            return cur;
        }

        /**
         * 找到小于等于指定key的所有key中的最大值
         *
         * @param key 指定的key
         * @return 小于等于指定key的所有key中的最大key
         */
        private AvlNode<K, V> findLastNoBigNode(K key) {
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

        /**
         * 查找比指定的key大的所有key中的最小key
         *
         * @param key 指定的key
         * @return 比指定key大的所有key中的最小key
         */
        private AvlNode<K, V> findFistNoLessNode(K key) {
            AvlNode<K, V> ans = null;
            AvlNode<K, V> cur = root;
            while (cur != null) {
                if (key.compareTo(cur.key) == 0) {
                    ans = cur;
                    break;
                }
                if (key.compareTo(cur.key) > 0) {
                    cur = cur.right;
                    continue;
                }
                if (key.compareTo(cur.key) < 0) {
                    ans = cur;
                    cur = cur.left;
                }
            }
            return ans;
        }

        /**
         * 添加key->value到avl树种
         *
         * @param cur   当前节点
         * @param key   待添加的key
         * @param value 待添加的value
         * @return 添加到avl树中后, 返回调整后,针对当前节点的头结点(子树的头节点)
         */
        private AvlNode<K, V> add(AvlNode<K, V> cur, K key, V value) {
            if (cur == null) {
                return new AvlNode<>(key, value);
            }
            if (key.compareTo(cur.key) > 0) {
                cur.right = add(cur.right, key, value);
            } else {
                cur.left = add(cur.left, key, value);
            }
            cur.height = Math.max(cur.left == null ? 0 : cur.left.height,
                cur.right == null ? 0 : cur.right.height) + 1;
            return maintain(cur);
        }

        /**
         * 根据指定的key删除avl树种的节点
         *
         * @param cur 当前要检查的节点
         * @param key 待删除的key
         * @return 针对当前节点做删除后, 返回新的头节点
         */
        private AvlNode<K, V> delete(AvlNode<K, V> cur, K key) {
            if (cur == null) {
                return null;
            }
            if (cur.key.compareTo(key) == 0) {
                // 命中要删除的节点,检查节点是否有左右两侧的孩子
                if (cur.left == null && cur.right == null) {
                    cur = null;
                } else if (cur.left != null && cur.right == null) {
                    cur = cur.left;
                } else if (cur.left == null && cur.right != null) {
                    cur = cur.right;
                } else if (cur.left != null && cur.right != null) {
                    // 找到当前节右子树的最左节点,删除
                    AvlNode<K, V> mostLeft = cur.right;
                    while (mostLeft.left != null) {
                        mostLeft = mostLeft.left;
                    }
                    cur.right = delete(cur.right, mostLeft.key);
                    // 下面三句相当于把cur释放掉了,原cur节点处于游离状态,没有任何对象指向它,如果是C++,需要释放内存
                    mostLeft.left = cur.left;
                    mostLeft.right = cur.right;
                    // 把当前的cur返回即可,上一层的递归会接住返回值
                    cur = mostLeft;
                }
            } else if (key.compareTo(cur.key) > 0) {
                cur.right = delete(cur.right, key);
            } else if (key.compareTo(cur.key) < 0) {
                cur.left = delete(cur.left, key);
            }

            if (cur != null) {
                cur = maintain(cur);
            }

            return cur;
        }

        /**
         * 维护当前节点的平衡性,通过左旋和右旋来实现
         * <p>
         * 破坏平衡性主要有4中场景  LL,LR,RL,RR
         * <p>
         * LL和LR可能会同时出现,以LL为准进行调整
         * <p>
         * RL和RR可能会同时出现,以RR为准进行调整
         * <p>
         * LL和RR一定不会同时出现
         *
         * @param cur 当前节点
         * @return 重新调整平衡性后, 返回新的头节点
         */
        private AvlNode<K, V> maintain(AvlNode<K, V> cur) {

            if (cur == null) {
                return null;
            }

            int leftHeight = cur.left == null ? 0 : cur.left.height;
            int rightHeight = cur.right == null ? 0 : cur.right.height;
            if (Math.abs(leftHeight - rightHeight) <= 1) {
                // 当前节点的平衡性不违规,直接返回
                return cur;
            }

            if (leftHeight > rightHeight) {
                // LL型或者LR型,或者同时是LL和LR型,进一步判断是LL型还是LR型,LL型,只需要做一次右旋,LR型,需要做一次左旋,再做一次右旋
                int llHeight = cur.left != null && cur.left.left != null ? cur.left.left.height : 0;
                int lrHeight = cur.left != null && cur.left.right != null ? cur.left.right.height : 0;
                if (llHeight < lrHeight) {
                    // 当且仅当是LR型时,先做左旋
                    cur.left = leftRotate(cur.left);
                }
                return rightRotate(cur);
            }

            // RL型或者RR型,或者同时是RL和RR型
            int rlHeight = cur.right != null && cur.right.left != null ? cur.right.left.height : 0;
            int rrHeight = cur.right != null && cur.right.right != null ? cur.right.right.height : 0;
            if (rrHeight < rlHeight) {
                // 当且仅当是RL型时,先做右旋
                cur.right = rightRotate(cur.right);
            }
            return leftRotate(cur);
        }

        /**
         * 左旋,当前节点向左倒下去,当前节点的右孩子作为新的头结点
         *
         * @param cur 当前要左旋的节点
         * @return 旋转后, 返回的新头节点
         */
        private AvlNode<K, V> leftRotate(AvlNode<K, V> cur) {
            // 当前节点的右孩子,作为新头节点
            AvlNode<K, V> newHead = cur.right;
            // 新头结点的左孩子,作为当前节点的右孩子
            cur.right = newHead.left;
            // 当前节点作为新头节点的左孩子
            newHead.left = cur;
            // 重新计算高度,优先计算子节点
            cur.height = Math.max(cur.left == null ? 0 : cur.left.height, cur.right == null ? 0 : cur.right.height) + 1;
            newHead.height = Math.max(newHead.left.height, newHead.right == null ? 0 : newHead.right.height) + 1;
            return newHead;
        }

        /**
         * 右旋,当前节点向右侧倒下,当前节点的左孩子作为新的头结点
         *
         * @param cur 当前要右旋的节点
         * @return 右旋后, 返回的新头节点
         */
        private AvlNode<K, V> rightRotate(AvlNode<K, V> cur) {
            // 当前节点的左孩子,作为新的头节点
            AvlNode<K, V> newHead = cur.left;
            // 当前节接收新头节点的右孩子右孩子作为自己的左孩子
            cur.left = newHead.right;
            // 新头节点接收当前节点为自己的右孩子
            newHead.right = cur;
            // 重新计算高度,优先计算子节点,只有新头节点和当前节点发生变动
            cur.height = Math.max(cur.left == null ? 0 : cur.left.height, cur.right == null ? 0 : cur.right.height) + 1;
            newHead.height = Math.max(newHead.left == null ? 0 : newHead.left.height, newHead.right.height) + 1;
            return newHead;
        }

    }

    public static class TestClass {

        @Test
        public void testRightRotate() {
            AvlNode<Integer, Integer> root = new AvlNode<>(3, 3);
            root.height = 3;
            root.left = new AvlNode<>(2, 2);
            root.left.height = 2;
            root.left.left = new AvlNode<>(1, 1);
            root.left.left.height = 1;
            System.out.println("旋转前...");
            PrintAvlUtil.printTree(root);
            AvlTree<Integer, Integer> avlTree = new AvlTree<>();
            root = avlTree.rightRotate(root);
            System.out.println("旋转后");
            PrintAvlUtil.printTree(root);
        }

        @Test
        public void testLeftRotate() {
            AvlNode<Integer, Integer> root = new AvlNode<>(1, 1);
            root.height = 3;
            root.right = new AvlNode<>(2, 2);
            root.right.height = 2;
            root.right.right = new AvlNode<>(3, 3);
            root.right.right.height = 1;
            System.out.println("左旋前...");
            PrintAvlUtil.printTree(root);
            AvlTree<Integer, Integer> avlTree = new AvlTree<>();
            root = avlTree.leftRotate(root);
            System.out.println("左旋后...");
            PrintAvlUtil.printTree(root);
        }

        @Test
        public void testMaintainLL() {
            AvlNode<Integer, Integer> root = new AvlNode<>(3, 3);
            root.height = 3;
            root.left = new AvlNode<>(2, 2);
            root.left.height = 2;
            root.left.left = new AvlNode<>(1, 1);
            root.left.left.height = 1;
            System.out.println("维护前...");
            PrintAvlUtil.printTree(root);
            AvlTree<Integer, Integer> avlTree = new AvlTree<>();
            root = avlTree.maintain(root);
            System.out.println("维护后...");
            PrintAvlUtil.printTree(root);
        }

        @Test
        public void testMaintainLR() {
            AvlNode<Integer, Integer> root = new AvlNode<>(3, 3);
            root.height = 3;
            root.left = new AvlNode<>(2, 2);
            root.left.height = 2;
            root.left.right = new AvlNode<>(1, 1);
            root.left.right.height = 1;
            System.out.println("维护前...");
            PrintAvlUtil.printTree(root);
            AvlTree<Integer, Integer> avlTree = new AvlTree<>();
            root = avlTree.maintain(root);
            System.out.println("维护后...");
            PrintAvlUtil.printTree(root);
        }

        @Test
        public void testMaintainLLAndLR() {
            AvlNode<Integer, Integer> root = new AvlNode<>(4, 4);
            root.height = 3;
            root.left = new AvlNode<>(2, 2);
            root.left.height = 2;
            root.left.left = new AvlNode<>(1, 1);
            root.left.left.height = 1;
            root.left.right = new AvlNode<>(3, 3);
            root.left.right.height = 1;
            System.out.println("维护前...");
            PrintAvlUtil.printTree(root);
            AvlTree<Integer, Integer> avlTree = new AvlTree<>();
            root = avlTree.maintain(root);
            System.out.println("维护后...");
            PrintAvlUtil.printTree(root);
        }

        @Test
        public void testMaintainRR() {
            AvlNode<Integer, Integer> root = new AvlNode<>(1, 1);
            root.height = 3;
            root.right = new AvlNode<>(2, 2);
            root.right.height = 2;
            root.right.right = new AvlNode<>(3, 3);
            root.right.right.height = 1;
            System.out.println("维护前...");
            PrintAvlUtil.printTree(root);
            AvlTree<Integer, Integer> avlTree = new AvlTree<>();
            root = avlTree.maintain(root);
            System.out.println("维护后...");
            PrintAvlUtil.printTree(root);
        }

        @Test
        public void testMaintainRL() {
            AvlNode<Integer, Integer> root = new AvlNode<>(1, 1);
            root.height = 3;
            root.right = new AvlNode<>(3, 3);
            root.right.height = 2;
            root.right.left = new AvlNode<>(2, 2);
            root.right.left.height = 1;
            System.out.println("维护前...");
            PrintAvlUtil.printTree(root);
            AvlTree<Integer, Integer> avlTree = new AvlTree<>();
            root = avlTree.maintain(root);
            System.out.println("维护后...");
            PrintAvlUtil.printTree(root);
        }

        @Test
        public void testMaintainRLAndRR() {
            AvlNode<Integer, Integer> root = new AvlNode<>(1, 1);
            root.height = 3;
            root.right = new AvlNode<>(3, 3);
            root.right.height = 2;
            root.right.left = new AvlNode<>(2, 2);
            root.right.left.height = 1;
            root.right.right = new AvlNode<>(4, 4);
            root.right.right.height = 1;
            System.out.println("维护前...");
            PrintAvlUtil.printTree(root);
            AvlTree<Integer, Integer> avlTree = new AvlTree<>();
            root = avlTree.maintain(root);
            System.out.println("维护后...");
            PrintAvlUtil.printTree(root);
        }

        @Test
        public void testAdd() {
            AvlTree<Integer, Integer> avlTree = new AvlTree<>();
            AvlNode<Integer, Integer> root = null;
            for (int i = 0; i < 9; i++) {
                root = avlTree.add(root, i, i);
                PrintAvlUtil.printTree(root);
            }
        }

        @Test
        public void testPut() {
            AvlTree<Integer, Integer> avlTree = new AvlTree<>();

            for (int i = 0; i < 10; i++) {
                avlTree.put(i, i);
                PrintAvlUtil.printTree(avlTree.root);
                System.out.println("数量:" + avlTree.size);
            }

            for (int i = 0; i < 10; i++) {
                avlTree.put(i, i + 10);
                PrintAvlUtil.printTree(avlTree.root);
                System.out.println("数量:" + avlTree.size);
            }
        }

        @Test
        public void testDelete() {
            AvlTree<Integer, Integer> avlTree = new AvlTree<>();
            for (int i = 0; i < 11; i++) {
                avlTree.put(i, i);
            }
            PrintAvlUtil.printTree(avlTree.root);
            System.out.println("数量:" + avlTree.size);
            avlTree.root = avlTree.delete(avlTree.root, 7);
            PrintAvlUtil.printTree(avlTree.root);
        }

        @Test
        public void testDelete2() {
            AvlTree<Integer, Integer> avlTree = new AvlTree<>();
            for (int i = 0; i < 22; i++) {
                avlTree.put(i, i);
            }
            PrintAvlUtil.printTree(avlTree.root);
            System.out.println("数量:" + avlTree.size);
            avlTree.root = avlTree.delete(avlTree.root, 15);
            PrintAvlUtil.printTree(avlTree.root);
        }

        @Test
        public void testGet() {
            AvlTree<Integer, Integer> avlTree = new AvlTree<>();
            for (int i = 0; i < 20; i++) {
                avlTree.put(i, i);
            }
            for (int i = 0; i < 20; i++) {
                Integer value = avlTree.get(i);
                System.out.println(value);
            }
        }

        @Test
        public void testRemove() {
            AvlTree<Integer, Integer> avlTree = new AvlTree<>();
            for (int i = 0; i < 20; i++) {
                avlTree.put(i * 2, i);
            }
            for (int i = 0; i < 40; i++) {
                Integer value = avlTree.remove(i);
                int size = avlTree.size;
                System.out.println("被移除的value:" + value + ",剩余节点数:" + size);
                PrintAvlUtil.printTree(avlTree.root);
            }
        }

        @Test
        public void testFirstKey() {
            AvlTree<Integer, Integer> avlTree = new AvlTree<>();
            for (int i = 100; i > 0; i--) {
                avlTree.put(i, i);
                Integer firstKey = avlTree.firstKey();
                assert firstKey.equals(i);
            }
        }

        @Test
        public void testLastKey() {
            AvlTree<Integer, Integer> avlTree = new AvlTree<>();
            for (int i = 0; i < 100; i++) {
                avlTree.put(i, i);
                Integer lastKey = avlTree.lastKey();
                assert lastKey.equals(i);
            }
            for (int i = 99; i > 0; i--) {
                avlTree.remove(i);
                Integer lastKey = avlTree.lastKey();
                assert lastKey.equals(i - 1);
            }
        }

        @Test
        public void testFloorKey() {
            AvlTree<Integer, Integer> avlTree = new AvlTree<>();
            avlTree.put(0, 0);
            for (int i = 1; i <= 100; i++) {
                avlTree.put(i * 2, i);
                int limitKey = (int)(Math.random() * avlTree.lastKey());
                Integer floorKey = avlTree.floorKey(limitKey);
                Integer ansKey = limitKey % 2 == 0 ? limitKey : limitKey - 1;
                assert floorKey.equals(ansKey);
            }
        }

        @Test
        public void testCellingKey() {
            AvlTree<Integer, Integer> avlTree = new AvlTree<>();
            avlTree.put(0, 0);
            for (int i = 1; i <= 100; i++) {
                avlTree.put(i * 2, i);
                int limitKey = (int)(Math.random() * avlTree.lastKey());
                Integer cellingKey = avlTree.cellingKey(limitKey);
                Integer ansKey = limitKey % 2 == 0 ? limitKey : limitKey + 1;
                assert cellingKey.equals(ansKey);
            }
        }
    }
}

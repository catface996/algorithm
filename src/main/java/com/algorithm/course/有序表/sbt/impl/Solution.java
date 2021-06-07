package com.algorithm.course.有序表.sbt.impl;

import com.algorithm.util.PrintSbtUtil;
import org.junit.Test;

/**
 * @author by catface
 * @date 2021/5/15 2:58 下午
 */
public class Solution {

    public static class SbtTree<K extends Comparable<K>, V> {

        /**
         * 根节点
         */
        private SbtNode<K, V> root;

        /**
         * 向SBT中添加键值对,如果key已经存在,更新value,并且返回更新前的value
         *
         * @param key   目标key
         * @param value 待添加的value
         * @return 更新前的value
         */
        public V put(K key, V value) {
            SbtNode<K, V> targetNode = findByKey(key);
            if (targetNode == null) {
                root = add(root, key, value);
                return null;
            } else {
                V oldValue = targetNode.value;
                targetNode.value = value;
                return oldValue;
            }
        }

        /**
         * 从SBT中移除key,如果key已经存在,移除的同时返回key对应的value
         *
         * @param key 期望移除的key
         * @return 被移除的key对应的value.
         */
        public V remove(K key) {
            SbtNode<K, V> targetNode = findByKey(key);
            if (targetNode != null) {
                root = delete(root, key);
                return targetNode.value;
            }
            return null;
        }

        /**
         * 获取sbt树的所有节点个数
         *
         * @return 树上所有节点的个数
         */
        public int size() {
            if (root == null) {
                return 0;
            }
            return root.size;
        }

        /**
         * 获取key对应的value
         *
         * @param key 目标key
         * @return key对应的value
         */
        public V get(K key) {
            SbtNode<K, V> targetNode = findByKey(key);
            if (targetNode != null) {
                return targetNode.value;
            }
            return null;
        }

        /**
         * 在所有小于等于指定key的结婚中,查找最大值
         *
         * @param key 指定的key
         * @return 小于等于key的最大值
         */
        public K floorKey(K key) {
            SbtNode<K, V> targetNode = findNoBigNode(key);
            return targetNode == null ? null : targetNode.key;
        }

        /**
         * 根据指定的key查找相应的节点
         *
         * @param key 指定的key
         * @return key指向的节点
         */
        private SbtNode<K, V> findByKey(K key) {
            SbtNode<K, V> cur = root;
            while (cur != null) {
                if (key.compareTo(cur.key) == 0) {
                    return cur;
                }
                if (key.compareTo(cur.key) > 0) {
                    cur = cur.right;
                    continue;
                }
                if (key.compareTo(cur.key) < 0) {
                    cur = cur.left;
                }
            }
            return cur;
        }

        /**
         * 在所有小于等于指定key的集合中,查找最大的key
         *
         * @param key 指定的key
         * @return 小于等于指定key的集合中, 最大的key
         */
        private SbtNode<K, V> findNoBigNode(K key) {
            SbtNode<K, V> cur = root;
            SbtNode<K, V> ans = null;
            int cmp;
            while (cur != null) {
                cmp = key.compareTo(cur.key);
                if (cmp == 0) {
                    ans = cur;
                    break;
                }
                if (cmp > 0) {
                    cur = cur.right;
                    continue;
                }
                if (cmp < 0) {
                    ans = cur;
                    cur = cur.right;
                }
            }
            return ans;
        }

        /**
         * 添加节点到sbt中
         *
         * @param cur   指定的基准节点
         * @param key   要添加的key,调用该方法的前提是,key在sbt中不存在
         * @param value 要添加的value
         * @return 添加节点后, 基于基准节点, 返回新的头部节点
         */
        private SbtNode<K, V> add(SbtNode<K, V> cur, K key, V value) {
            if (cur == null) {
                return new SbtNode<>(key, value);
            }
            cur.size++;
            if (key.compareTo(cur.key) > 0) {
                cur.right = add(cur.right, key, value);
            } else {
                cur.left = add(cur.left, key, value);
            }
            return maintain(cur);
        }

        /**
         * 删除sbt中指定的key,删除key的前提是key已经存在
         * <p>
         * 删除节点时,可以不做平衡性的维护,在下次添加时会重新调整
         *
         * @param cur 当前检查到的节点
         * @param key 期望被删除的key
         * @return 删除期望的key, 重新维护平衡性后返回的新头部
         */
        private SbtNode<K, V> delete(SbtNode<K, V> cur, K key) {
            // cur一定不会为空,调用delete方法时,保证key是存在的
            cur.size--;
            int cmp = key.compareTo(cur.key);

            if (cmp > 0) {
                cur.right = delete(cur.right, key);
                return cur;
            }

            if (cmp < 0) {
                cur.left = delete(cur.left, key);
                return cur;
            }

            // 以下逻辑为当前节点即为要删除的节点
            if (cur.left != null && cur.right == null) {
                // 当前节点有左孩子,无右孩子,用左孩子代替当前节点
                cur = cur.left;
                return cur;
            }
            if (cur.left == null && cur.right != null) {
                // 当前节点有右孩子,无左孩子,用右孩子代替当前节点
                cur = cur.right;
                return cur;
            }

            if (cur.left == null && cur.right == null) {
                // 当前节点既没有左孩子,也没有右孩子,返回空即可
                return null;
            }

            // 当前节点,即有左孩子,也有右孩子,用当前节点右孩子的最左节点代替当前节点
            SbtNode<K, V> mostLeft = cur.right;
            while (mostLeft.left != null) {
                mostLeft = mostLeft.left;
            }
            // 此处递归删除即可
            delete(cur.right, mostLeft.key);
            mostLeft.left = cur.left;
            mostLeft.right = cur.right;
            return mostLeft;

        }

        /**
         * 维护指定节点的平衡性
         * <p>
         * 需要递归向下维护整棵子树的平衡性
         *
         * @param cur 指定要维护的节点
         * @return 调整平衡性后, 返回新的头节点
         */
        private SbtNode<K, V> maintain(SbtNode<K, V> cur) {
            if (cur == null) {
                return null;
            }
            // 检查指定节点是否打破了平衡性
            int llSize = cur.left != null && cur.left.left != null ? cur.left.left.size : 0;
            int lrSize = cur.left != null && cur.left.right != null ? cur.left.right.size : 0;
            int rSize = cur.right != null ? cur.right.size : 0;
            int rlSize = cur.right != null && cur.right.left != null ? cur.right.left.size : 0;
            int rrSize = cur.right != null && cur.right.right != null ? cur.right.right.size : 0;
            int lSize = cur.left != null ? cur.left.size : 0;

            if (llSize > rSize) {
                // LL型; LL型和LR型
                cur = rightRotate(cur);
                cur.right = maintain(cur.right);
                return maintain(cur);
            }

            if (lrSize > rSize) {
                // 仅LR型;
                cur.left = leftRotate(cur.left);
                cur = rightRotate(cur);
                cur.left = maintain(cur.left);
                cur.right = maintain(cur.right);
                return maintain(cur);
            }

            if (rrSize > lSize) {
                // RR型; 或者 RL型和RR型
                cur = leftRotate(cur);
                cur.left = maintain(cur.left);
                return maintain(cur);
            }

            if (rlSize > lSize) {
                cur.right = rightRotate(cur.right);
                cur = leftRotate(cur);
                cur.left = maintain(cur.left);
                cur.right = maintain(cur.right);
                return maintain(cur);
            }

            // 不违规
            return cur;

        }

        /**
         * 右旋,以传入的参数为基准点,基准点向右侧下沉
         *
         * @param cur 基准点
         * @return 右旋之后的新头节点
         */
        private SbtNode<K, V> rightRotate(SbtNode<K, V> cur) {
            SbtNode<K, V> newHead = cur.left;
            cur.left = newHead.right;
            newHead.right = cur;
            // cur的左子树发生变化,需要重新计算以cur为头节点的子树节点总数
            cur.size = (cur.left == null ? 0 : cur.left.size) + (cur.right == null ? 0 : cur.right.size) + 1;
            // 新晋升的头节点的右子树发生变化,需要重新计算新晋升头节点的子树中的节点个数
            newHead.size = (newHead.left == null ? 0 : newHead.left.size) + newHead.right.size + 1;
            return newHead;
        }

        /**
         * 左旋,以传入的参数为基准点,记住点向左下沉
         *
         * @param cur 基准点
         * @return 左旋之后的新头节点
         */
        private SbtNode<K, V> leftRotate(SbtNode<K, V> cur) {
            SbtNode<K, V> newHead = cur.right;
            cur.right = newHead.left;
            newHead.left = cur;
            // cur的左子树发生变化,需要重新计算以cur为头节点的子树节点总数
            cur.size = (cur.left == null ? 0 : cur.left.size) + (cur.right == null ? 0 : cur.right.size) + 1;
            // 新晋升的头节点的右子树发生变化,需要重新计算新晋升头节点的子树中的节点个数
            newHead.size = newHead.left.size + (newHead.right == null ? 0 : newHead.right.size) + 1;
            return newHead;
        }

    }

    public static class TestClass {

        @Test
        public void testLeftRotate() {
            SbtTree<Integer, Integer> sbtTree = new SbtTree<>();
            sbtTree.root = new SbtNode<>(1, 1);
            sbtTree.root.right = new SbtNode<>(2, 2);
            sbtTree.root.right.right = new SbtNode<>(3, 3);
            sbtTree.root.size = 3;
            sbtTree.root.right.size = 2;
            sbtTree.root.right.right.size = 1;
            System.out.println("左旋之前...");
            PrintSbtUtil.printTree(sbtTree.root);
            sbtTree.root = sbtTree.leftRotate(sbtTree.root);
            System.out.println("左旋之后...");
            PrintSbtUtil.printTree(sbtTree.root);
        }

        @Test
        public void testRightRotate() {
            SbtTree<Integer, Integer> sbtTree = new SbtTree<>();
            sbtTree.root = new SbtNode<>(3, 3);
            sbtTree.root.left = new SbtNode<>(2, 2);
            sbtTree.root.left.left = new SbtNode<>(1, 1);
            sbtTree.root.size = 3;
            sbtTree.root.left.size = 2;
            sbtTree.root.left.left.size = 1;
            System.out.println("右旋之前...");
            PrintSbtUtil.printTree(sbtTree.root);
            sbtTree.root = sbtTree.rightRotate(sbtTree.root);
            System.out.println("右旋之后...");
            PrintSbtUtil.printTree(sbtTree.root);
        }

        @Test
        public void testAdd() {
            SbtTree<Integer, Integer> sbtTree = new SbtTree<>();
            for (int i = 0; i < 18; i++) {
                sbtTree.root = sbtTree.add(sbtTree.root, i, i);
                PrintSbtUtil.printTree(sbtTree.root);
            }
        }

        @Test
        public void testPut() {
            SbtTree<Integer, Integer> sbtTree = new SbtTree<>();
            for (int i = 0; i < 18; i++) {
                sbtTree.put(i, i);
                PrintSbtUtil.printTree(sbtTree.root);
            }
        }

        @Test
        public void testRemove() {
            SbtTree<Integer, Integer> sbtTree = new SbtTree<>();
            for (int i = 0; i < 20; i++) {
                sbtTree.put(i, i + 30);
            }
            PrintSbtUtil.printTree(sbtTree.root);
            for (int i = 0; i <= 10; i++) {
                Integer value = sbtTree.remove(i);
                System.out.println("被移除的key:" + i + ",被移除的value:" + value);
            }
            PrintSbtUtil.printTree(sbtTree.root);
            sbtTree.put(21, 21);
            PrintSbtUtil.printTree(sbtTree.root);
            sbtTree.maintain(sbtTree.root.left);
            PrintSbtUtil.printTree(sbtTree.root);
        }

        @Test
        public void testRemoveThenAdd() {
            SbtTree<Integer, Integer> sbtTree = new SbtTree<>();
            for (int i = 0; i < 20; i++) {
                sbtTree.put(i, i + 30);
            }
            PrintSbtUtil.printTree(sbtTree.root);
            for (int i = 0; i <= 10; i++) {
                sbtTree.remove(i);
            }
            PrintSbtUtil.printTree(sbtTree.root);
            sbtTree.put(10, 10);
            PrintSbtUtil.printTree(sbtTree.root);
            sbtTree.put(9, 9);
            PrintSbtUtil.printTree(sbtTree.root);
            sbtTree.put(8, 8);
            PrintSbtUtil.printTree(sbtTree.root);
            sbtTree.put(7, 7);
            PrintSbtUtil.printTree(sbtTree.root);
            sbtTree.put(6, 6);
            PrintSbtUtil.printTree(sbtTree.root);
        }
    }
}

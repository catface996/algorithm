package course.有序表.sbt.impl;

import org.junit.Test;
import util.PrintSbtUtil;

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
            if (key.compareTo(cur.key) > 0) {
                cur.right = add(cur.right, key, value);
            } else {
                cur.left = add(cur.left, key, value);
            }
            cur.size++;
            return maintain(cur);
        }

        /**
         * 删除sbt中指定的key
         *
         * @param cur 当前检查到的节点
         * @param key 期望被删除的key
         * @return 删除期望的key, 重新维护平衡性后返回的新头部
         */
        private SbtNode<K, V> delete(SbtNode<K, V> cur, K key) {
            if (cur == null) {
                return null;
            }
            int cmp = key.compareTo(cur.key);
            if (cmp == 0) {
                // 检查当前节点是否有左右子节点
                if (cur.left != null && cur.right != null) {
                    // 左右孩子都存在,删除起来比较麻烦
                    SbtNode<K, V> mostLeft = cur.right;
                    while (mostLeft.left != null) {
                        mostLeft = mostLeft.left;
                    }
                    cur.right = delete(cur.right, mostLeft.key);
                    mostLeft.left = cur.left;
                    mostLeft.right = cur.right;
                    cur = mostLeft;
                } else if (cur.left != null && cur.right == null) {
                    cur = cur.left;
                } else if (cur.left == null && cur.right != null) {
                    cur = cur.right;
                } else if (cur.left == null && cur.right == null) {
                    cur = null;
                }
            } else if (cmp > 0) {
                cur.right = delete(cur.right, key);
            } else if (cmp < 0) {
                cur.left = delete(cur.left, key);
            }
            if (cur != null) {
                cur.size--;
                cur = maintain(cur);
            }
            return cur;
        }

        /**
         * 维护指定节点的平衡性
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
            if (llSize > rSize || lrSize > rSize) {
                // 为了代码易于理解,不做if分支优化
                // 左孩子的子节点中出现节点数大于叔叔的情况
                if (llSize > rSize) {
                    // LL 型,进行一次右旋即可
                    cur = rightRotate(cur);
                } else {
                    // LR型,做一次左旋,再做一次右旋
                    cur.left = leftRotate(cur.left);
                    cur = rightRotate(cur);
                }
            }

            int rlSize = cur.right != null && cur.right.left != null ? cur.right.left.size : 0;
            int rrSize = cur.right != null && cur.right.right != null ? cur.right.right.size : 0;
            int lSize = cur.left != null ? cur.left.size : 0;
            if (rlSize > lSize || rrSize > lSize) {
                // 右孩子中的子节点出现数量大于叔叔的情况
                if (rrSize > lSize) {
                    //RR型,只做一次左旋即可
                    cur = leftRotate(cur);
                } else {
                    // 先做一次右旋,再做一次左旋
                    cur.right = rightRotate(cur.right);
                    cur = leftRotate(cur);
                }
            }
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
            for (int i = 0; i < 20; i++) {
                Integer value = sbtTree.remove(i);
                System.out.println("被移除的key:" + i + ",被移除的value:" + value);
                PrintSbtUtil.printTree(sbtTree.root);
            }
        }
    }
}

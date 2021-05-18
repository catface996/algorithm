package course.有序表.skip.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import util.PrintSkipListUtil;

/**
 * @author by catface
 * @date 2021/5/18 11:34 上午
 */
public class Solution2 {

    public static class SkipNode<K extends Comparable<K>, V> {
        public K key;
        public V value;
        public List<SkipNode<K, V>> nextNodes;

        public SkipNode(K key, V value) {
            this.key = key;
            this.value = value;
            nextNodes = new ArrayList<>();
        }

        public SkipNode(K key, V value, int level) {
            this.key = key;
            this.value = value;
            nextNodes = new ArrayList<>();
            for (int i = 0; i <= level; i++) {
                nextNodes.add(null);
            }
        }

        public boolean isEqualKey(K otherKey) {
            if (this.key == null && otherKey == null) {
                return true;
            }
            if (this.key != null && otherKey != null && this.key.compareTo(otherKey) == 0) {
                return true;
            }
            return false;
        }

        public boolean isLessKey(K otherKey) {
            if (otherKey != null && this.key == null) {
                return true;
            }
            if (otherKey != null && this.key != null && this.key.compareTo(otherKey) < 0) {
                return true;
            }
            return false;
        }
    }

    public static class SkipMap<K extends Comparable<K>, V> {
        /**
         * 小于等于probability,在当前层插入节点,大于probability,升层
         */
        private static final double PROBABILITY = 0.5;
        /**
         * 跳表头
         */
        private SkipNode<K, V> head;
        /**
         * 跳表的大小
         */
        private int size;
        /**
         * 最大高度
         */
        private int maxLevel;

        public SkipMap() {

            // 跳表的大小默认是0
            this.size = 0;

            // 最大高度默认是0
            this.maxLevel = 0;

            // 头节点不存储key->value
            // 头节点默认指向null
            this.head = new SkipNode<>(null, null);
            head.nextNodes.add(null);
        }

        public V put(K key, V value) {

            // 首先检查要加入的key是否已经存在于跳表中,如果已经存在,更新key对应的value即可
            SkipNode<K, V> mostRightLessNode = mostRightLessInMap(key);
            SkipNode<K, V> nextNode = mostRightLessNode.nextNodes.get(0);
            if (nextNode != null && nextNode.isEqualKey(key)) {
                V oldValue = nextNode.value;
                nextNode.value = value;
                return oldValue;
            }

            // key未存在与跳表中
            // 首先决定key最高要插入到那一层
            // 1/2的概率留在第0层,1/4的概率留在第1层,1/8概率留在第2层,1/16的概率留在第3层...
            int newLevel = 0;
            while (Math.random() >= PROBABILITY) {
                newLevel++;
            }

            // 检查头节点是否要升层,如果要,升层
            while (newLevel > maxLevel) {
                head.nextNodes.add(null);
                maxLevel++;
            }

            // 加入map的节点数++
            size++;

            // 构造待插入的节点,最大层数为newLevel
            SkipNode<K, V> newNode = new SkipNode<>(key, value, newLevel);

            // 高于newLevel的层,只做遍历
            SkipNode<K, V> pre = head;
            for (int i = maxLevel; i > newLevel; i--) {
                pre = mostRightLessInLevel(pre, key, i);
            }

            // 小于等于newLevel的层,做连接
            for (int i = newLevel; i >= 0; i--) {
                pre = mostRightLessInLevel(pre, key, i);
                newNode.nextNodes.set(i, pre.nextNodes.get(i));
                pre.nextNodes.set(i, newNode);
            }

            return null;
        }

        /**
         * 在整个跳表中查找不超过指定key值的最右节点
         *
         * @param key 指定的key
         * @return 小于指定key值的最右节点
         */
        private SkipNode<K, V> mostRightLessInMap(K key) {
            int currentLevel = maxLevel;
            SkipNode<K, V> cur = head;
            while (currentLevel >= 0) {
                cur = mostRightLessInLevel(cur, key, currentLevel--);
            }
            return cur;
        }

        /**
         * 在指定的层中查找不超过指定key值的最右节点
         *
         * @param startNode 开始节点
         * @param key       指定key
         * @param level     指定的层数
         * @return 在指定层数中, 不超过指定key值的最右节点
         */
        private SkipNode<K, V> mostRightLessInLevel(SkipNode<K, V> startNode, K key, int level) {
            SkipNode<K, V> cur = startNode;
            while (cur != null && cur.nextNodes.get(level) != null && cur.nextNodes.get(level).isLessKey(key)) {
                cur = cur.nextNodes.get(level);
            }
            return cur;
        }

        /**
         * 随机一个level值,供新插入的key使用
         *
         * @return 随机的level
         */
        private int randomLevel() {
            // 1/2的概率留在第0层,1/4的概率留在第1层,1/8概率留在第2层,1/16的概率留在第3层...
            int level = 0;
            while (Math.random() >= PROBABILITY) {
                level++;
            }
            return level;
        }

    }

    public static class TestClass {

        @Test
        public void testPut() {
            SkipMap<Integer, Integer> skipMap = new SkipMap<>();
            for (int i = 0; i < 16; i++) {
                skipMap.put((int)(Math.random() * 20), i);
            }
            PrintSkipListUtil.print(skipMap.head);
        }

    }

}

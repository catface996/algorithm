package course.有序表.skip.impl;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import util.PrintSkipListUtil;

/**
 * @author by catface
 * @date 2021/5/18 11:34 上午
 */
@Slf4j
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
         * 查找指定key对应的value
         *
         * @param key 指定的key
         * @return 指定key对应的value
         */
        public V get(K key) {
            SkipNode<K, V> less = mostRightLessInMap(key);
            SkipNode<K, V> next = less.nextNodes.get(0);
            if (next != null && next.isEqualKey(key)) {
                return next.value;
            }
            return null;
        }

        /**
         * 是否包含指定的key
         *
         * @param key 指定的key
         * @return true:包含;false:不包含;
         */
        public boolean containsKey(K key) {
            SkipNode<K, V> less = mostRightLessInMap(key);
            SkipNode<K, V> next = less.nextNodes.get(0);
            return next != null && next.isEqualKey(key);
        }

        /**
         * 删除指定的key,如果key存在于跳表中,返回key对应的value
         *
         * @param key 指定的key
         * @return key对应的value
         */
        public V remove(K key) {

            SkipNode<K, V> pre = head;
            SkipNode<K, V> next;
            V oldValue = null;
            int currentLevel = maxLevel;
            boolean delete = false;
            while (currentLevel >= 0) {
                pre = mostRightLessInLevel(pre, key, currentLevel);
                next = pre.nextNodes.get(currentLevel);
                if (next != null && next.isEqualKey(key)) {

                    // 删除当前层
                    oldValue = next.value;
                    // 当前层发现指定的key,调整pre的指向
                    pre.nextNodes.set(currentLevel, next.nextNodes.get(currentLevel));
                    delete = true;

                    // 检查删除节点后,pre节点是否是头节点,pre节点是否指向null,并且不是第0层,删除
                    if (pre == head && pre.nextNodes.get(currentLevel) == null && currentLevel > 0) {
                        // 删除当前层
                        head.nextNodes.remove(currentLevel);
                        maxLevel--;
                    }
                }
                currentLevel--;
            }
            if (delete) {
                size--;
            }
            return oldValue;
        }

        /**
         * 返回跳表的第一个key
         *
         * @return 如果跳表不为空, 返回第一个key, 否则, 返回null
         */
        public K firstKey() {
            SkipNode<K, V> firstNode = head.nextNodes.get(0);
            if (firstNode != null) {
                return firstNode.key;
            }
            return null;
        }

        /**
         * 返回跳表的最后一个key
         *
         * @return 如果跳表不为空, 返回最后一个key, 否则, 返回null
         */
        public K lastKey() {
            SkipNode<K, V> cur = head;
            int currentLevel = maxLevel;
            while (currentLevel >= 0) {
                while (cur.nextNodes.get(currentLevel) != null) {
                    cur = cur.nextNodes.get(currentLevel);
                }
                currentLevel--;
            }
            return cur.key;
        }

        /**
         * 查询小于等于指定key的集合中的最大key(前驱)
         *
         * @param key 指定的key
         * @return 小于等于指定key的集合中, 最大的key
         */
        public K floorKey(K key) {
            SkipNode<K, V> less = mostRightLessInMap(key);
            SkipNode<K, V> next = less.nextNodes.get(0);
            if (next != null && next.isEqualKey(key)) {
                return next.key;
            } else {
                return less.key;
            }
        }

        /**
         * 查询大于等于指定key的集合中的最小值(后驱)
         *
         * @param key 指定的key
         * @return 所有大于等于指定key集合中, 最小的key
         */
        public K cellingKey(K key) {
            SkipNode<K, V> less = mostRightLessInMap(key);
            SkipNode<K, V> next = less.nextNodes.get(0);
            if (next != null) {
                return next.key;
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

        @Test
        public void testGet() {
            SkipMap<Integer, Integer> skipMap = new SkipMap<>();
            for (int i = 0; i < 16; i++) {
                skipMap.put(i, i + 10);
            }
            PrintSkipListUtil.print(skipMap.head);
            for (int i = 0; i < 16; i++) {
                Integer value = skipMap.get(i);
                assert value == i + 10;
            }
        }

        @Test
        public void testContainsKey() {
            SkipMap<Integer, Integer> skipMap = new SkipMap<>();
            for (int i = 0; i < 16; i++) {
                skipMap.put(i, i + 10);
            }
            PrintSkipListUtil.print(skipMap.head);
            for (int i = -16; i < 0; i++) {
                boolean value = skipMap.containsKey(i);
                assert !value;
            }
            for (int i = 0; i < 16; i++) {
                boolean value = skipMap.containsKey(i);
                assert value;
            }
            for (int i = 16; i < 32; i++) {
                boolean value = skipMap.containsKey(i);
                assert !value;
            }
        }

        @Test
        public void testRemove() {
            SkipMap<Integer, Integer> skipMap = new SkipMap<>();
            for (int i = 0; i < 16; i++) {
                skipMap.put(i, i);
            }
            PrintSkipListUtil.print(skipMap.head);
            for (int i = 0; i < 16; i++) {
                Integer value = skipMap.remove(i);
                log.info("remove key:{},value:{}", i, value);
                PrintSkipListUtil.print(skipMap.head);
            }
        }

        @Test
        public void testFirstKey() {
            SkipMap<Integer, Integer> skipMap = new SkipMap<>();
            for (int i = 0; i < 16; i++) {
                skipMap.put(i, i);
                Integer firstKey = skipMap.firstKey();
                assert Integer.valueOf(0).equals(firstKey);
            }
            for (int i = 0; i < 15; i++) {
                skipMap.remove(i);
                Integer firstKey = skipMap.firstKey();
                assert Integer.valueOf(i + 1).equals(firstKey);
            }
        }

        @Test
        public void testLastKey() {
            SkipMap<Integer, Integer> skipMap = new SkipMap<>();
            for (int i = 0; i < 16; i++) {
                skipMap.put(i, i);
                Integer lastKey = skipMap.lastKey();
                assert Integer.valueOf(i).equals(lastKey);
            }
            for (int i = 15; i > 0; i--) {
                skipMap.remove(i);
                Integer lastKey = skipMap.lastKey();
                assert Integer.valueOf(i - 1).equals(lastKey);
            }
        }

        @Test
        public void testFloorKey() {
            SkipMap<Integer, Integer> skipMap = new SkipMap<>();
            for (int i = 0; i < 16; i++) {
                skipMap.put(i * 2, i);
            }
            for (int i = 0; i < 16; i++) {
                Integer floorKey = skipMap.floorKey(i * 2 + 1);
                assert floorKey.equals(i * 2);
                floorKey = skipMap.floorKey(i * 2);
                assert floorKey.equals(i * 2);
            }
        }

        @Test
        public void testCellingKey() {
            SkipMap<Integer, Integer> skipMap = new SkipMap<>();
            for (int i = 0; i < 16; i++) {
                skipMap.put(i * 2, i);
            }
            for (int i = 0; i < 16; i++) {
                Integer targetKey = skipMap.cellingKey(i * 2 - 1);
                assert Integer.valueOf(i * 2).equals(targetKey);
            }
        }

    }

}

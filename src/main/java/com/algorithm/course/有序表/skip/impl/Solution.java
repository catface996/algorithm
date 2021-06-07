package com.algorithm.course.有序表.skip.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * @author by catface
 * @date 2021/5/17 10:33 上午
 */
public class Solution {

    public static class SkipListNode<K extends Comparable<K>, V> {
        /**
         * 节点的key值
         */
        private final K key;
        /**
         * 节点的value
         */
        private V value;
        /**
         * 节点的后续,下标代表所在层数,值代表所在层的下一个节点
         */
        private List<SkipListNode<K, V>> nextNodes;

        public SkipListNode(K key, V value) {
            this.key = key;
            this.value = value;
            this.nextNodes = new ArrayList<>();
        }

        public boolean isKeyLess(K otherKey) {
            return otherKey != null && (key == null || key.compareTo(otherKey) < 0);
        }

        public boolean isKeyEqual(K otherKey) {
            return (key == null && otherKey == null) || (key != null && otherKey != null && key.compareTo(otherKey)
                == 0);
        }
    }

    public static class SkipListMap<K extends Comparable<K>, V> {
        /**
         * 决定因子,0~1中的随机数,小于决定因子,停留在当前层,超过随机因子,继续下一层
         */
        private static final double PROBABILITY = 0.5;
        /**
         * 跳表的头结点,头结点中不存key和value,只用来记录每层的下一跳
         */
        private SkipListNode<K, V> head;
        /**
         * 跳表中的节点个数
         */
        private int size;
        /**
         * 跳表的最高层数
         */
        private int maxLevel;

        public SkipListMap() {
            head = new SkipListNode<>(null, null);
            head.nextNodes.add(null);
            size = 0;
            maxLevel = 0;
        }

        /**
         * 获取:小于指定key,且最右侧的key
         *
         * @param key 指定key
         * @return 最右侧且小于指定key值的key
         */
        private SkipListNode<K, V> mostRightLessNodeInTree(K key) {
            if (key == null) {
                return null;
            }
            int level = maxLevel;
            SkipListNode<K, V> cur = head;
            while (level >= 0) {
                cur = mostRightLessNodeInLevel(key, cur, level--);
            }
            return cur;
        }

        private SkipListNode<K, V> mostRightLessNodeInLevel(K key, SkipListNode<K, V> cur, int level) {
            SkipListNode<K, V> next = cur.nextNodes.get(level);
            while (next != null && next.isKeyLess(key)) {
                cur = next;
                next = cur.nextNodes.get(level);
            }
            return cur;
        }

        /**
         * 是否包含指定的key
         *
         * @param key 指定的key
         * @return true:包含指定的key,false:不包含指定的key
         */
        public boolean containsKey(K key) {
            if (key == null) {
                return false;
            }
            SkipListNode<K, V> less = mostRightLessNodeInTree(key);
            SkipListNode<K, V> next = less.nextNodes.get(0);
            return next != null && next.isKeyEqual(key);
        }

        /**
         * 添加键值对,如果key不存在,添加,存在,更新;
         *
         * @param key   指定的key
         * @param value key对应的value
         */
        public void put(K key, V value) {
            if (key == null) {
                return;
            }
            SkipListNode<K, V> less = mostRightLessNodeInTree(key);
            SkipListNode<K, V> find = less.nextNodes.get(0);
            if (find != null && find.isKeyEqual(key)) {
                find.value = value;
            } else {
                size++;
                int newNodeLevel = 0;
                while (Math.random() < PROBABILITY) {
                    newNodeLevel++;
                }
                while (newNodeLevel > maxLevel) {
                    head.nextNodes.add(null);
                    maxLevel++;
                }
                SkipListNode<K, V> newNode = new SkipListNode<>(key, value);
                for (int i = 0; i <= newNodeLevel; i++) {
                    newNode.nextNodes.add(null);
                }
                int level = maxLevel;
                SkipListNode<K, V> pre = head;
                while (level >= 0) {
                    pre = mostRightLessNodeInLevel(key, pre, level);
                    if (level <= newNodeLevel) {
                        newNode.nextNodes.set(level, pre.nextNodes.get(level));
                        pre.nextNodes.set(level, newNode);
                    }
                    level--;
                }
            }
        }

        /**
         * 获取key对应的value
         *
         * @param key 指定的key
         * @return key对应的value
         */
        public V get(K key) {
            if (key == null) {
                return null;
            }
            SkipListNode<K, V> less = mostRightLessNodeInTree(key);
            SkipListNode<K, V> next = less.nextNodes.get(0);
            return next != null && next.isKeyEqual(key) ? next.value : null;
        }

        /**
         * 删除指定的key
         *
         * @param key 待删除的key
         */
        public void remove(K key) {
            if (containsKey(key)) {
                size--;
                int level = maxLevel;
                SkipListNode<K, V> pre = head;
                while (level >= 0) {
                    pre = mostRightLessNodeInLevel(key, pre, level);
                    SkipListNode<K, V> next = pre.nextNodes.get(level);
                    if (next != null && next.isKeyEqual(key)) {
                        pre.nextNodes.set(level, next.nextNodes.get(level));
                    }
                    if (level != 0 && pre == head && pre.nextNodes.get(level) == null) {
                        head.nextNodes.remove(level);
                        maxLevel--;
                    }
                    level--;
                }
            }
        }

        /**
         * 获取第一个key
         *
         * @return 第一个key, 可能是null
         */
        public K firstKey() {
            return head.nextNodes.get(0) != null ? head.nextNodes.get(0).key : null;
        }

        /**
         * 查询跳表中的最后一个key
         *
         * @return 最右一个key
         */
        public K lastKey() {
            int level = maxLevel;
            SkipListNode<K, V> cur = head;
            while (level >= 0) {
                SkipListNode<K, V> next = cur.nextNodes.get(level);
                while (next != null) {
                    cur = next;
                    next = cur.nextNodes.get(level);
                }
                level--;
            }
            return cur.key;
        }

        /**
         * 在所有小于等于指定key的集合中,获取最大的key
         *
         * @param key 指定key
         * @return 小于等于指定key的最大值
         */
        public K floorKey(K key) {
            if (key == null) {
                return null;
            }
            SkipListNode<K, V> less = mostRightLessNodeInTree(key);
            SkipListNode<K, V> next = less.nextNodes.get(0);
            return next != null && next.isKeyEqual(key) ? next.key : less.key;
        }

        /**
         * 在所有大于等于指定key的集合中,获取最小的key
         *
         * @param key 指定key
         * @return 所有大于等于指定key的集合中, 最小的key
         */
        public K cellingKey(K key) {
            if (key == null) {
                return null;
            }
            SkipListNode<K, V> less = mostRightLessNodeInTree(key);
            SkipListNode<K, V> next = less.nextNodes.get(0);
            return next != null ? next.key : null;
        }

        /**
         * 获取跳表的大小
         *
         * @return 跳表的大小
         */
        public int size() {
            return size;
        }
    }

    public static class TestClass {

        @Test
        public void testPutAndGet() {
            SkipListMap<Integer, Integer> skip = new SkipListMap<>();
            for (int i = 0; i < 100; i++) {
                skip.put(i, i + 1);
            }
            for (int i = 0; i < 100; i++) {
                Integer value = skip.get(i);
                System.out.println("key:" + i + "-> value:" + value);
            }
        }

        @Test
        public void testRemove() {
            SkipListMap<Integer, Integer> skip = new SkipListMap<>();
            for (int i = 0; i < 100; i++) {
                skip.put(i, i + 1);
            }
            for (int i = 0; i < 100; i++) {
                skip.remove(i);
                Integer value = skip.get(i);
                System.out.println(value);
            }
        }

        @Test
        public void testFirstKeyLastKey() {
            SkipListMap<Integer, Integer> skip = new SkipListMap<>();
            for (int i = 0; i < 100; i++) {
                skip.put(i, i + 1);
                Integer firstKey = skip.firstKey();
                Integer lastKey = skip.lastKey();
                System.out.println("firstKey:" + firstKey + ",lastKey:" + lastKey);
            }
        }

        @Test
        public void testSize() {
            SkipListMap<Integer, Integer> skip = new SkipListMap<>();
            for (int i = 0; i < 100; i++) {
                skip.put(i, i + 1);
                int size = skip.size();
                System.out.println("size:" + size);
            }
        }

        @Test
        public void testFloorKey() {
            SkipListMap<Integer, Integer> skip = new SkipListMap<>();
            for (int i = 0; i < 100; i++) {
                skip.put(i * 2, i);
            }
            for (int i = 0; i < 100; i++) {
                Integer floorKey = skip.floorKey(i * 2 + 1);
                assert floorKey.equals(i * 2);
            }
        }

        @Test
        public void cellingKey() {
            SkipListMap<Integer, Integer> skip = new SkipListMap<>();
            for (int i = 0; i < 100; i++) {
                skip.put(i * 2, i);
            }
            for (int i = 0; i < 99; i++) {
                Integer floorKey = skip.cellingKey(i * 2 - 1);
                assert floorKey.equals(i * 2);
            }
        }
    }

}

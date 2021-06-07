package com.algorithm.question.class2.class2_设计有setAll功能的哈希表;

import java.util.HashMap;

import org.junit.Test;

/**
 * @author by catface
 * @date 2021/5/24 10:56 上午
 */
public class Solution {

    public static class MyHashMap<K, V> {
        // 发生setAll时的时间
        private Long timestamp;
        // 发生setAll是设置的value
        private V setAllValue;

        private HashMap<K, Long> keyToTimeStamp;
        private HashMap<K, V> keyToValue;

        public MyHashMap() {
            timestamp = 0L;
            setAllValue = null;
            keyToTimeStamp = new HashMap<>();
            keyToValue = new HashMap<>();
        }

        public void put(K key, V value) {
            keyToTimeStamp.put(key, timestamp);
            keyToValue.put(key, value);
        }

        public void setAll(V value) {
            ++timestamp;
            setAllValue = value;
        }

        public V get(K key) {
            Long ketTimeStamp = keyToTimeStamp.get(key);
            if (timestamp.compareTo(ketTimeStamp) > 0) {
                return setAllValue;
            }
            return keyToValue.get(key);
        }
    }

    public static class TestClass {
        @Test
        public void test() {
            MyHashMap<Integer, Integer> myHashMap = new MyHashMap<>();
            for (int i = 1; i < 100; i++) {
                myHashMap.put(i, i);
            }
            int ans = myHashMap.get(60);
            System.out.println(ans);
            myHashMap.setAll(121);

            ans = myHashMap.get(60);
            System.out.println(ans);

            myHashMap.put(60, 60);
            ans = myHashMap.get(60);
            System.out.println(ans);

            ans = myHashMap.get(50);
            System.out.println(ans);

        }
    }
}

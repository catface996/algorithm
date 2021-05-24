package question.class2_设计有setAll功能的哈希表;

import java.util.HashMap;

import org.junit.Test;

/**
 * @author by catface
 * @date 2021/5/24 11:27 上午
 */
public class Solution2 {

    public static class MyValue<V> {
        private V value;
        private Long timestamp;

        public MyValue(V value, Long timestamp) {
            this.value = value;
            this.timestamp = timestamp;
        }
    }

    public static class MyHashMap<K, V> {
        private Long timestamp;
        private V allValue;
        private HashMap<K, MyValue<V>> map;

        public MyHashMap() {
            timestamp = 0L;
            allValue = null;
            map = new HashMap<>();
        }

        public V put(K key, V value) {
            V oldValue = get(key);
            map.put(key, new MyValue<>(value, timestamp + 1));
            return oldValue;
        }

        public V get(K key) {
            MyValue<V> myValue = map.get(key);
            if (myValue != null) {
                return myValue.timestamp > timestamp ? myValue.value : allValue;
            }
            return null;
        }

        public void setAll(V value) {
            timestamp++;
            allValue = value;
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

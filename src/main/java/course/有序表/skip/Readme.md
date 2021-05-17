# 跳表

https://zhuanlan.zhihu.com/p/68516038

## 特点

* 构造出的结构与数据样本无关.(通过随机数决定某个key出现在哪一层)

* 第一层有个N个节点,第二层有N/2,第三层有N/4,第四层有N/8,...

## 复杂度

* 插入复杂度 log(N)

* 删除复杂度 log(N)

* 查询某个指定的key复杂度 log(N)

## 实现方案

### 跳表节点

~~~ java
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
    }
~~~

### 跳表

~~~java

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
        // 头结点不存储key和value
        head = new SkipListNode<>(null, null);
        // 初始化头节点
        head.nextNodes.add(null);
        // 跳表的大小
        size = 0;
        // 跳表的最高层
        maxLevel = 0;
    }
    
    public V put(K key,V value);
    public V remove(K key,V value);
    public V get(K key);
    public K containsKey(K key);
    public K firstKey();
    public K lastKey();
    public int size();
}
~~~

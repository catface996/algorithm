package com.algorithm.leetcode.设计.P146缓存机制;

//运用你所掌握的数据结构，设计和实现一个 LRU (最近最少使用) 缓存机制 。
//
//
//
// 实现 LRUCache 类：
//
//
// LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存
// int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
// void put(int key, int value) 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。当缓存容量达到上
//限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
//
//
//
//
//
//
// 进阶：你是否可以在 O(1) 时间复杂度内完成这两种操作？
//
//
//
// 示例：
//
//
//输入
//["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
//[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
//输出
//[null, null, null, 1, null, -1, null, -1, 3, 4]
//
//解释
//LRUCache lRUCache = new LRUCache(2);
//lRUCache.put(1, 1); // 缓存是 {1=1}
//lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
//lRUCache.get(1);    // 返回 1
//lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
//lRUCache.get(2);    // 返回 -1 (未找到)
//lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
//lRUCache.get(1);    // 返回 -1 (未找到)
//lRUCache.get(3);    // 返回 3
//lRUCache.get(4);    // 返回 4
//
//
//
//
// 提示：
//
//
// 1 <= capacity <= 3000
// 0 <= key <= 3000
// 0 <= value <= 104
// 最多调用 3 * 104 次 get 和 put
//
// Related Topics 设计
// 👍 1441 👎 0

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

//leetcode submit region begin(Prohibit modification and deletion)
public class LRUCache {

    // 2:26 下午	info
    //				解答成功:
    //				执行耗时:69 ms,击败了9.71% 的Java用户
    //				内存消耗:108.2 MB,击败了5.04% 的Java用户
    //TODO 需要优化

    final Node head;
    Node tail;
    int size;
    Map<Integer, Node> nodeMap;
    int maxSize;

    public LRUCache(int capacity) {
        head = new Node();
        tail = head;
        maxSize = capacity;
        size = 0;
        nodeMap = new HashMap<>(capacity);
    }

    public int get(int key) {
        // 移动到队列尾部
        Node node = nodeMap.get(key);
        if (node == null) {
            return -1;
        }

        // 如果已经在链表尾部,直接返回value即可
        if (node == tail) {
            return node.value;
        }

        // 从链表中摘除
        Node pre = node.pre;
        Node next = node.next;
        pre.next = next;
        next.pre = pre;

        // 摘除的节点放到队尾
        node.pre = tail;
        node.next = null;
        tail.next = node;
        tail = node;
        // 返回value
        return node.value;
    }

    public void put(int key, int value) {
        // 检查key是否存在,如果存在,更新value,放到队尾
        if (nodeMap.containsKey(key)) {

            Node node = nodeMap.get(key);
            node.value = value;
            // 如果已经在尾部,无需移动
            if (node == tail) {
                return;
            }
            // 从链表中摘除
            Node pre = node.pre;
            Node next = node.next;
            pre.next = next;
            next.pre = pre;

            // 摘除的节点放到队尾
            node.pre = tail;
            node.next = null;
            tail.next = node;
            tail = node;
        } else {
            // 如果不存在,检查是否需要淘汰已有的缓存对象
            if (size >= maxSize) {
                // 淘汰head,新加入的放到队尾
                Node first = head.next;
                Node second = first.next;
                head.next = first.next;
                if (second != null) {
                    second.pre = head;
                }
                nodeMap.remove(first.key);
            } else {
                size++;
            }
            //加入map
            Node node = new Node(key, value);
            nodeMap.put(key, node);
            // 加入链表尾部
            node.pre = tail;
            node.next = null;
            tail.next = node;
            tail = node;
        }

    }

    public static class Node {
        int key;
        int value;
        Node next;
        Node pre;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
            next = null;
            pre = null;
        }

        public Node() {

        }
    }

    public static class TestClass {
        // 示例：
        //
        //
        //输入
        //["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
        //[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
        //输出
        //[null, null, null, 1, null, -1, null, -1, 3, 4]
        //
        //解释
        //LRUCache lRUCache = new LRUCache(2);
        //lRUCache.put(1, 1); // 缓存是 {1=1}
        //lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
        //lRUCache.get(1);    // 返回 1
        //lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        //lRUCache.get(2);    // 返回 -1 (未找到)
        //lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        //lRUCache.get(1);    // 返回 -1 (未找到)
        //lRUCache.get(3);    // 返回 3
        //lRUCache.get(4);    // 返回 4
        @Test
        public void test1() {
            LRUCache lruCache = new LRUCache(2);
            lruCache.put(1, 1);
            lruCache.put(2, 2);
            int value1 = lruCache.get(1);
            System.out.println(value1);
            lruCache.put(3, 3);
            int value2 = lruCache.get(2);
            System.out.println(value2);
            lruCache.put(4, 4);
            int value3 = lruCache.get(1);
            System.out.println(value3);
            int value4 = lruCache.get(3);
            System.out.println(value4);
            int value5 = lruCache.get(4);
            System.out.println(value5);
        }

        // 2:22 下午	info
        //				运行失败:
        //				java.lang.NullPointerException
        //				at line 71, LRUCache.put
        //				at line 66, __Driver__.__helperSelectMethod__
        //				at line 88, __Driver__.__helper__
        //				at line 109, __Driver__.main
        //				测试用例:
        //				["LRUCache","put","get","put","get","get"]
        //				[[1],[2,1],[2],[3,2],[2],[3]]
        @Test
        public void test2() {
            LRUCache lruCache = new LRUCache(1);
            lruCache.put(2, 1);
            lruCache.get(2);
            lruCache.put(3, 2);
            lruCache.get(2);
            lruCache.get(3);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such: LRUCache obj = new LRUCache(capacity); int param_1 =
 * obj.get(key); obj.put(key,value);
 */
//leetcode submit region end(Prohibit modification and deletion)


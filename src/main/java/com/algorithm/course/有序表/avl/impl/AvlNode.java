package com.algorithm.course.有序表.avl.impl;

/**
 * @author by catface
 * @date 2021/5/12 5:25 下午
 */
public class AvlNode<K, V> {

    /**
     * 节点的key,用于排序
     */
    public K key;
    /**
     * 节点的值,记录业务数据
     */
    public V value;
    /**
     * 左孩子
     */
    public AvlNode<K, V> left;
    /**
     * 右孩子
     */
    public AvlNode<K, V> right;
    /**
     * avl树的高度
     */
    public int height;

    public AvlNode(K key, V value) {
        this.key = key;
        this.value = value;
        height = 1;
    }
}

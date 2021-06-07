package com.algorithm.course.有序表.sbt.impl;

/**
 * @author by catface
 * @date 2021/5/15 2:54 下午
 */
public class SbtNode<K extends Comparable<K>, V> {
    /**
     * 排序key,需要实现Comparable接口
     */
    public K key;
    /**
     * 节点存储的值
     */
    public V value;
    /**
     * 左孩子
     */
    public SbtNode<K, V> left;
    /**
     * 右孩子
     */
    public SbtNode<K, V> right;
    /**
     * 子树节点个数
     */
    public int size;

    public SbtNode(K key, V value) {
        this.key = key;
        this.value = value;
        this.size = 1;
    }
}

package com.algorithm.util;

import java.util.HashMap;
import java.util.Map;

import com.algorithm.course.有序表.skip.impl.Solution2.SkipNode;

/**
 * @author by catface
 * @date 2021/5/18 3:05 下午
 */
public class PrintSkipListUtil {

    public static void print(SkipNode head) {
        if (head.nextNodes.size() < 1) {
            System.out.println("空跳表");
            return;
        }
        // 9,支持两位数,11 支持三位数
        int nodeWidth = 9;
        int maxLevel = head.nextNodes.size();
        int length = 0;

        // 统计长度和每个节点在第0层的下标
        SkipNode cur = head;
        Map<SkipNode, Integer> nodeIndexMap = new HashMap<>();
        nodeIndexMap.put(head, 0);
        while (cur.nextNodes.get(0) != null) {
            cur = (SkipNode)cur.nextNodes.get(0);
            length++;
            nodeIndexMap.put(cur, length);
        }

        // 初始化位图
        char[][] bitmap = new char[maxLevel + 1][(length + 2) * nodeWidth];
        for (int i = 0; i < maxLevel; i++) {
            for (int j = 0; j < (length + 2) * nodeWidth; j++) {
                bitmap[i][j] = '-';
            }
        }

        // 首先打印头
        String context = "|n,n|";
        for (int level = 0; level < head.nextNodes.size(); level++) {
            for (int c = 0; c < context.length(); c++) {
                bitmap[level][c] = context.charAt(c);
            }
        }

        // 打印中间
        int currentLevel = maxLevel - 1;
        while (currentLevel >= 0) {
            cur = head;
            while (cur != null) {
                cur = (SkipNode)cur.nextNodes.get(currentLevel);
                if (cur != null) {
                    int index = nodeIndexMap.get(cur);
                    context = ">|" + cur.key.toString() + "," + cur.value.toString() + "|";
                    for (int c = 0; c < context.length(); c++) {
                        bitmap[currentLevel][index * nodeWidth + c] = context.charAt(c);
                    }
                }
            }
            currentLevel--;
        }

        // 打印尾部
        length++;
        context = ">|n,n|";
        for (int level = 0; level < head.nextNodes.size(); level++) {
            for (int c = 0; c < nodeWidth; c++) {
                char symbol = ' ';
                if (c < context.length()) {
                    symbol = context.charAt(c);
                }
                bitmap[level][length * nodeWidth + c] = symbol;
            }
        }

        // 输出位图
        for (int i = 0; i < bitmap[0].length; i++) {
            System.out.print('*');
        }
        System.out.println();
        for (int i = bitmap.length - 1; i >= 0; i--) {
            for (int j = 0; j < bitmap[0].length; j++) {
                System.out.print(bitmap[i][j]);
            }
            System.out.println();
        }
        System.out.println();
        for (int i = 0; i < bitmap[0].length; i++) {
            System.out.print('*');
        }
        System.out.println();
    }

}

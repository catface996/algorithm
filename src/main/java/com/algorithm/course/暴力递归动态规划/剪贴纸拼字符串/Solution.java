//package com.algorithm.course.暴力递归动态规划.剪贴纸拼字符串;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import org.junit.Test;
//
///**
// * @author by catface
// * @date 2021/4/12 4:09 下午
// */
//public class Solution {
//
//    //给定一个字符串str，给定一个字符串类型的数组arr，出现的字符都是小写英文
//    //arr每一个字符串，代表一张贴纸，你可以把单个字符剪开使用，目的是拼出str来
//    //返回需要至少多少张贴纸可以完成这个任务。
//    //例子：str= "babac"，arr = {"ba","c","abcd"}
//    //ba + ba + c  3  abcd + abcd 2  abcd+ba 2
//    //所以返回2
//
//    public int calculatePaperNums(String[] papers, String str) {
//        Map<Character, Integer>[] paperMapArr = new HashMap[papers.length];
//        for (int i = 0; i < papers.length; i++) {
//            Map<Character, Integer> map = new HashMap<>();
//            paperMapArr[i] = map;
//            for (int j = 0; j < papers[i].length(); j++) {
//                char ch = papers[i].charAt(j);
//                Integer count = map.getOrDefault(ch, 0);
//                count = count + 1;
//                map.put(ch, count);
//            }
//        }
//        StrInfo strInfo = new StrInfo(str);
//        return process(paperMapArr, strInfo);
//    }
//
//    /**
//     * 贴纸转字符数组
//     *
//     * @param papers  贴纸剪碎之后的字符数组,以及每个字符出现的次数
//     * @param strInfo 字符剪碎之后的数组,以及每个字符出现的次数
//     * @return 需要的最小贴纸数
//     */
//    private int process(Map<Character, Integer>[] papers, StrInfo strInfo) {
//        if (strInfo.currentNum == 0) {
//            return 0;
//        }
//        int min = Integer.MAX_VALUE;
//        for (Map<Character, Integer> paper : papers) {
//            StrInfo newStrInfo = minus(paper, strInfo);
//            if (newStrInfo.currentNum != strInfo.currentNum) {
//                min = Math.min(min, process(papers, newStrInfo));
//            }
//        }
//        return min + (min == Integer.MAX_VALUE ? 0 : 1);
//    }
//
//    private StrInfo minus(Map<Character, Integer> paper, StrInfo strInfo) {
//        StrInfo newStrInfo = new StrInfo(strInfo);
//        newStrInfo.charMap.forEach((k, v) -> {
//            Integer paperContainCount = paper.get(k);
//            if (paperContainCount != null && v > 0) {
//                int left = Math.max(v - paperContainCount, 0);
//                newStrInfo.charMap.put(k, left);
//                newStrInfo.currentNum = strInfo.currentNum - (v - left);
//            }
//        });
//        return newStrInfo;
//    }
//
//    public static class StrInfo {
//        int currentNum;
//        Map<Character, Integer> charMap;
//
//        public StrInfo(String str) {
//            this.currentNum = str.length();
//            charMap = new HashMap<>();
//            for (int i = 0; i < str.length(); i++) {
//                char ch = str.charAt(i);
//                Integer count = charMap.getOrDefault(ch, 0);
//                count = count + 1;
//                charMap.put(ch, count);
//            }
//        }
//
//        public StrInfo(StrInfo oldInfo) {
//            this.currentNum = oldInfo.currentNum;
//            charMap = new HashMap<>();
//            oldInfo.charMap.forEach((k, v) -> {
//                charMap.put(k, v);
//            });
//        }
//    }
//
//    public static class TestClass {
//
//        @Test
//        public void test1() {
//            Solution solution = new Solution();
//            int ans = solution.calculatePaperNums(new String[] {"ba", "c", "abcd"}, "babac");
//            System.out.println(ans);
//        }
//
//    }
//
//}

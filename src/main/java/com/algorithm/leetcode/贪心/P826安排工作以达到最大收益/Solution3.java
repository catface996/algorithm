package com.algorithm.leetcode.贪心.P826安排工作以达到最大收益;

import java.util.Arrays;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author by catface
 * @date 2021/5/24 5:26 下午
 */
@Slf4j
public class Solution3 {

    // 1:01 下午	info
    //				解答成功:
    //				执行耗时:38 ms,击败了49.07% 的Java用户
    //				内存消耗:39.9 MB,击败了26.03% 的Java用户

    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        TreeMap jobMap = new TreeMap();
        Job[] jobs = new Job[difficulty.length];
        for (int i = 0; i < difficulty.length; i++) {
            jobs[i] = new Job(difficulty[i], profit[i]);
        }
        Arrays.sort(jobs, (o1, o2) -> {
            if (o1.difficulty == o2.difficulty) {
                return o1.profit - o2.profit;
            }
            return o1.difficulty - o2.difficulty;
        });
        for (Job job : jobs) {
            jobMap.put(job.difficulty,job.profit);
        }
        int bestProfit = 0;
        for (int j : worker) {
            Integer tempProfit = jobMap.getBestProfit(j);
            if (tempProfit != null) {
                bestProfit += tempProfit;
            }
        }
        return bestProfit;
    }

    public static class Job {
        private int difficulty;
        private int profit;

        public Job(int difficulty, int profit) {
            this.difficulty = difficulty;
            this.profit = profit;
        }
    }

    /**
     * 使用avl树实现有序表
     */
    public static class TreeMap {

        private Node root;

        public TreeMap() {
            root = null;
        }

        public Integer getBestProfit(Integer key) {
            int ans = 0;
            Node cur = root;
            while (cur != null) {
                if (key.compareTo(cur.key) == 0) {
                    ans = Math.max(ans, cur.value);
                    break;
                } else if (key.compareTo(cur.key) > 0) {
                    ans = Math.max(ans, cur.value);
                    cur = cur.right;
                } else {
                    cur = cur.left;
                }
            }
            return ans;
        }

        /**
         * 如果key已经存在,当value大于原有的value时,更新否则不更新
         * <p>
         * 加入的key和value 与之前加入的key和value 要保持单调性,破坏单调性的不加入
         *
         * @param key   key,在本题中是工作难度
         * @param value value,在本题中是工作收益
         */
        public void put(Integer key, Integer value) {
            Node floorNode = getFloorNode(key);
            if (floorNode != null && floorNode.key.compareTo(key) == 0) {
                if (value.compareTo(floorNode.value) > 0) {
                    floorNode.value = value;
                }
                return;
            }
            if (floorNode == null || (floorNode.key.compareTo(key) < 0 && floorNode.value.compareTo(value) < 0)) {
                root = add(root, key, value);
            }
        }

        private Node add(Node cur, Integer key, Integer value) {
            if (cur == null) {
                return new Node(key, value);
            }
            if (key.compareTo(cur.key) == 0) {
                // 只更新value,不会发生高度变化,进而不会出现违规
                cur.value = value;
                return cur;
            } else if (key.compareTo(cur.key) > 0) {
                cur.right = add(cur.right, key, value);
            } else {
                cur.left = add(cur.left, key, value);
            }
            // 增加节点后,高度发生变化,需要重新计算当前节点的高度
            cur.height = Math.max(cur.left != null ? cur.left.height : 0, cur.right != null ? cur.right.height : 0) + 1;
            // 新添加节点后,因高度发生变化,可能会出现违规,需要维护
            return maintain(cur);
        }

        /**
         * 查找小于等于指定key的所有key的集合中,最大的key对应的节点
         *
         * @param key 指定的key
         * @return floor 节点
         */
        private Node getFloorNode(Integer key) {
            Node ans = null;
            Node cur = root;
            while (cur != null) {
                if (key.compareTo(cur.key) == 0) {
                    ans = cur;
                    break;
                } else if (key.compareTo(cur.key) > 0) {
                    ans = cur;
                    cur = cur.right;
                } else {
                    cur = cur.left;
                }
            }
            return ans;
        }

        /**
         * 检查当前节点是否违规,如果违规,做调整
         * <p>
         * avl 树有四种违规,分别是 LL,LR,RL,RR
         * <p>
         * 可能只出现LL,只出现LR,只出现RL,只出现RR,
         * <p>
         * 也可能同时出现LL,LR, 以及 RL和RR ,不会同时出现LL和RR,不会同时出现LR和RL
         * <p>
         * LL和LR同时出现,当LL处理
         * <p>
         * RL和RR同时出现,当RR处理
         *
         * @param cur 当前要维护的节点
         * @return 维护后返回的新头节点
         */
        private Node maintain(Node cur) {

            // 获取左右子树的高度,做LL,LR 和 RL,RR的划分
            int lh = cur.left != null ? cur.left.height : 0;
            int rh = cur.right != null ? cur.right.height : 0;

            if (Math.abs(lh - rh) <= 1) {
                // 未发生违规,直接返回
                return cur;
            }

            if (lh > rh) {
                // 左侧子树违规
                int llh = cur.left != null && cur.left.left != null ? cur.left.left.height : 0;
                int lrh = cur.left != null && cur.left.right != null ? cur.left.right.height : 0;
                if (llh < lrh) {
                    // 仅是LR违规,需要做一次左旋
                    cur.left = leftRotate(cur.left);
                }
                // 无论如何,都要做一次右旋
                cur = rightRotate(cur);
            } else {
                // 因为差值的绝对值大于1,所以要么lh大于rh,要么rh大于lh,一定不会有lh=rh
                int rlh = cur.right != null && cur.right.left != null ? cur.right.left.height : 0;
                int rrh = cur.right != null && cur.right.right != null ? cur.right.right.height : 0;
                if (rlh > rrh) {
                    // 仅是RL违规,需要先做一次右旋
                    cur.right = rightRotate(cur.right);
                }
                cur = leftRotate(cur);
            }

            return cur;
        }

        /**
         * 右旋,头节点的左孩子作为新的头结点
         * <p>
         * 原头节点作为新头节点的右孩子
         * <p>
         * 新头节点原有的右孩子作为原头节点的左孩子
         *
         * @param head 待旋转的头结点
         * @return 旋转后的新头节点
         */
        private Node rightRotate(Node head) {
            // 头节点变成左孩子的右节点
            // 左孩子原有的右节点变成原头节点的左孩子
            Node newHead = head.left;
            Node leftRight = newHead.right;
            newHead.right = head;
            head.left = leftRight;
            // 更新发生变动节点的高度
            head.height = Math.max(head.right != null ? head.right.height : 0,
                head.left != null ? head.left.height : 0) + 1;
            newHead.height = Math.max(newHead.left != null ? newHead.left.height : 0, newHead.right.height) + 1;
            return newHead;
        }

        /**
         * 左旋
         * <p>
         * 原头节点的右孩子提升为新的头结点
         * <p>
         * 原头节点下沉为新头节点的左孩子
         * <p>
         * 新头节点原有的左孩子变为原头节点的右孩子
         *
         * @param head 待旋转的头结点
         * @return 旋转后的新头节点
         */
        private Node leftRotate(Node head) {
            Node newHead = head.right;
            Node rightLeft = newHead.left;
            newHead.left = head;
            head.right = rightLeft;
            head.height = Math.max(head.left != null ? head.left.height : 0,
                head.right != null ? head.right.height : 0) + 1;
            newHead.height = Math.max(newHead.left.height, newHead.right != null ? newHead.right.height : 0) + 1;
            return newHead;
        }
    }

    public static class Node {
        public final Integer key;
        public Integer value;
        public Node left;
        public Node right;
        public int height;

        public Node(Integer key, Integer value) {
            this.key = key;
            this.value = value;
            height = 1;
            left = null;
            right = null;
        }
    }

    public static class TestClass {

        // 示例：
        //
        // 输入: difficulty = [2,4,6,8,10], profit = [10,20,30,40,50], worker = [4,5,6,7]
        //输出: 100
        //解释: 工人被分配的工作难度是 [4,4,6,6] ，分别获得 [20,20,30,30] 的收益。
        @Test
        public void test1() {
            int[] difficulty = {2, 4, 6, 8, 10}, profit = {10, 20, 30, 40, 50}, worker = {4, 5, 6, 7};
            Solution3 solution3 = new Solution3();
            int bestProfit = solution3.maxProfitAssignment(difficulty, profit, worker);
            log.info("bestProfit:{}", bestProfit);
        }

        // 示例：
        //         解答失败:
        //        测试用例:[68,35,52,47,86]
        //            [67,17,1,81,3]
        //            [92,10,85,84,82]
        //        测试结果:204
        //        期望结果:324
        @Test
        public void test2() {
            int[] difficulty = {68, 35, 52, 47, 86}, profit = {67, 17, 1, 81, 3}, worker = {92, 10, 85, 84, 82};
            Solution3 solution3 = new Solution3();
            int bestProfit = solution3.maxProfitAssignment(difficulty, profit, worker);
            log.info("bestProfit:{}", bestProfit);
        }

    }
}

package com.algorithm.leetcode.拓扑排序.P207课程表;

//你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
//
// 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表
//示如果要学习课程 ai 则 必须 先学习课程 bi 。
//
//
// 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
//
//
// 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
//
//
//
// 示例 1：
//
//
//输入：numCourses = 2, prerequisites = [[1,0]]
//输出：true
//解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。
//
// 示例 2：
//
//
//输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
//输出：false
//解释：总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0 ；并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。
//
//
//
// 提示：
//
//
// 1 <= numCourses <= 105
// 0 <= prerequisites.length <= 5000
// prerequisites[i].length == 2
// 0 <= ai, bi < numCourses
// prerequisites[i] 中的所有课程对 互不相同
//
// Related Topics 深度优先搜索 广度优先搜索 图 拓扑排序
// 👍 785 👎 0

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import org.junit.Test;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution2 {

    // 10:30 上午	info
    //					解答成功:
    //					执行耗时:8 ms,击败了27.51% 的Java用户
    //					内存消耗:39.2 MB,击败了42.83% 的Java用户

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites == null || prerequisites.length <= 0) {
            return true;
        }
        int resolveCount = 0;
        Map<Integer, Course> courseNodeMap = new HashMap<>();
        for (int[] prerequisite : prerequisites) {
            int firstId = prerequisite[1];
            int nextId = prerequisite[0];
            Course firstCourse = getCourseNode(courseNodeMap, firstId);
            Course nextCourse = getCourseNode(courseNodeMap, nextId);
            firstCourse.nextCourses.add(nextCourse);
            nextCourse.inDegree += 1;
        }
        Queue<Course> queue = new LinkedList<>();
        for (Course value : courseNodeMap.values()) {
            if (value.inDegree <= 0) {
                resolveCount++;
                queue.add(value);
            }
        }
        while (!queue.isEmpty()) {
            Course course = queue.poll();
            for (Course nextCourse : course.nextCourses) {
                nextCourse.inDegree -= 1;
                if (nextCourse.inDegree == 0) {
                    resolveCount++;
                    queue.add(nextCourse);
                }
            }
        }
        for (Course value : courseNodeMap.values()) {
            if (value.inDegree > 0) {
                return false;
            }
        }
        return true;
    }

    private Course getCourseNode(Map<Integer, Course> courseNodeMap, int courseId) {
        Course course = courseNodeMap.get(courseId);
        if (course == null) {
            course = new Course(courseId);
            courseNodeMap.put(courseId, course);
        }
        return course;
    }

    public static class Course {
        private Integer courseId;
        private int inDegree;
        private Set<Course> nextCourses;

        public Course(Integer courseId) {
            this.courseId = courseId;
            this.nextCourses = new HashSet<>();
            this.inDegree = 0;
        }
    }

    public static class TestClass {
        @Test
        public void test1() {
            int[][] course = {{1, 0}, {0, 1}};
            Solution2 solution = new Solution2();
            boolean ans = solution.canFinish(2, course);
            System.out.println(ans);
        }

        @Test
        public void test2() {
            int[][] course = {{1, 2}, {1, 3}, {2, 3}, {3, 4}, {3, 5}, {4, 5}, {4, 6}};
            Solution2 solution = new Solution2();
            boolean ans = solution.canFinish(6, course);
            System.out.println(ans);
        }
    }

}
//leetcode submit region end(Prohibit modification and deletion)


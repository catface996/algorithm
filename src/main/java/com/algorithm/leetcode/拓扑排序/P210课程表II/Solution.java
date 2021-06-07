package com.algorithm.leetcode.拓扑排序.P210课程表II;
//现在你总共有 n 门课需要选，记为 0 到 n-1。
//
// 在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们: [0,1]
//
// 给定课程总量以及它们的先决条件，返回你为了学完所有课程所安排的学习顺序。
//
// 可能会有多个正确的顺序，你只要返回一种就可以了。如果不可能完成所有课程，返回一个空数组。
//
// 示例 1:
//
// 输入: 2, [[1,0]]
//输出: [0,1]
//解释: 总共有 2 门课程。要学习课程 1，你需要先完成课程 0。因此，正确的课程顺序为 [0,1] 。
//
// 示例 2:
//
// 输入: 4, [[1,0],[2,0],[3,1],[3,2]]
//输出: [0,1,2,3] or [0,2,1,3]
//解释: 总共有 4 门课程。要学习课程 3，你应该先完成课程 1 和课程 2。并且课程 1 和课程 2 都应该排在课程 0 之后。
//     因此，一个正确的课程顺序是 [0,1,2,3] 。另一个正确的排序是 [0,2,1,3] 。
//
//
// 说明:
//
//
// 输入的先决条件是由边缘列表表示的图形，而不是邻接矩阵。详情请参见图的表示法。
// 你可以假定输入的先决条件中没有重复的边。
//
//
// 提示:
//
//
// 这个问题相当于查找一个循环是否存在于有向图中。如果存在循环，则不存在拓扑排序，因此不可能选取所有课程进行学习。
// 通过 DFS 进行拓扑排序 - 一个关于Coursera的精彩视频教程（21分钟），介绍拓扑排序的基本概念。
//
// 拓扑排序也可以通过 BFS 完成。
//
//
// Related Topics 深度优先搜索 广度优先搜索 图 拓扑排序
// 👍 390 👎 0

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * @author by catface
 * @date 2021/4/23 10:49 上午
 */
public class Solution {

    // 11:08 上午	info
    //					解答成功:
    //					执行耗时:9 ms,击败了29.22% 的Java用户
    //					内存消耗:39.7 MB,击败了52.95% 的Java用户

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] ans = new int[numCourses];
        if (prerequisites == null || prerequisites.length <= 0) {
            for (int i = 0; i < numCourses; i++) {
                ans[i] = i;
            }
            return ans;
        }
        Map<Integer, Course> courseNodeMap = new HashMap<>();
        for (int i = 0; i < numCourses; i++) {
            courseNodeMap.put(i,new Course(i));
        }
        int index = 0;
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
                ans[index++] = value.courseId;
                queue.add(value);
            }
        }
        while (!queue.isEmpty()) {
            Course course = queue.poll();
            for (Course nextCourse : course.nextCourses) {
                nextCourse.inDegree -= 1;
                if (nextCourse.inDegree == 0) {
                    ans[index++] = nextCourse.courseId;
                    queue.add(nextCourse);
                }
            }
        }
        for (Course value : courseNodeMap.values()) {
            if (value.inDegree > 0) {
                return new int[0];
            }
        }
        return ans;
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
}

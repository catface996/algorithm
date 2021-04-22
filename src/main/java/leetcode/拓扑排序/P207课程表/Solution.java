package leetcode.拓扑排序.P207课程表;

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
import java.util.Map;
import java.util.Set;

import org.junit.Test;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // 8:27 下午	info
    //				解答成功:
    //				执行耗时:15 ms,击败了16.52% 的Java用户
    //				内存消耗:39.5 MB,击败了18.38% 的Java用户
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, Course> courseNodeMap = new HashMap<>();
        for (int[] prerequisite : prerequisites) {
            int firstId = prerequisite[1];
            int nextId = prerequisite[0];
            Course firstCourse = getCourseNode(courseNodeMap, firstId);
            Course nextCourse = getCourseNode(courseNodeMap, nextId);
            firstCourse.nextCourses.add(nextCourse);
            nextCourse.inDegree += 1;
        }
        Heap heap = new Heap(courseNodeMap.size());
        for (Course value : courseNodeMap.values()) {
            heap.push(value);
        }

        while (!heap.isEmpty()) {
            Course course = heap.pop();
            if (course.inDegree > 0) {
                return false;
            }
            for (Course nextCourse : course.nextCourses) {
                nextCourse.inDegree -= 1;
                heap.adjust(nextCourse);
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

    public static class Heap {
        Course[] arr;
        private int size;
        private Map<Course, Integer> indexMap;

        public Heap(int size) {
            this.arr = new Course[size];
            this.size = 0;
            this.indexMap = new HashMap<>();
        }

        public void push(Course course) {
            arr[size] = course;
            indexMap.put(course, size);
            heapInsert(size++);
        }

        public Course pop() {
            Course ans = arr[0];
            swap(0, size - 1);
            arr[size - 1] = null;
            indexMap.remove(ans);
            size--;
            heapify(0);
            return ans;
        }

        public void adjust(Course course) {
            Integer index = indexMap.get(course);
            if (index != null) {
                heapify(index);
                heapInsert(index);
            }
        }

        public boolean isEmpty() {
            return size <= 0;
        }

        private void heapInsert(int index) {
            while (arr[index].inDegree < arr[(index - 1) / 2].inDegree) {
                swap(index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        private void heapify(int index) {
            int left = index * 2 + 1;
            while (left < size) {
                int swapIndex = left + 1 < size && arr[left + 1].inDegree < arr[left].inDegree ? left + 1 : left;
                swapIndex = arr[index].inDegree < arr[swapIndex].inDegree ? index : swapIndex;
                if (swapIndex == index) {
                    return;
                }
                swap(index, swapIndex);
                index = swapIndex;
                left = index * 2 + 1;
            }
        }

        private void swap(int i, int j) {
            if (i == j) {
                return;
            }
            Course iCourse = arr[i];
            Course jCourse = arr[j];
            arr[i] = jCourse;
            indexMap.put(jCourse, i);
            arr[j] = iCourse;
            indexMap.put(iCourse, j);
        }
    }

    public static class TestClass {
        @Test
        public void test1() {
            int[][] course = {{1, 0}, {0, 1}};
            Solution solution = new Solution();
            boolean ans = solution.canFinish(2, course);
            System.out.println(ans);
        }

        @Test
        public void test2() {
            int[][] course = {{1, 2}, {1, 3}, {2, 3}, {3, 4}, {3, 5}, {4, 5}, {4, 6}};
            Solution solution = new Solution();
            boolean ans = solution.canFinish(2, course);
            System.out.println(ans);
        }
    }

}
//leetcode submit region end(Prohibit modification and deletion)


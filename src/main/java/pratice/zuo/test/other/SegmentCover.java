package pratice.zuo.test.other;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author by catface
 * <p>
 * 线段最大重合数
 * @date 2021/3/15 3:41 下午
 */
@Slf4j
public class SegmentCover {

    public static int calculateMaxCover(List<Segment> segments) {
        int maxCover = 0;
        PriorityQueue<Integer> heap = new PriorityQueue<>(segments.size());
        segments.sort(Comparator.comparingInt(Segment::getStart));

        for (Segment segment : segments) {
            while (!heap.isEmpty() && heap.peek() <= segment.getStart()) {
                heap.poll();
            }
            heap.add(segment.end);
            maxCover = Math.max(maxCover, heap.size());
        }
        return maxCover;
    }

    public static void sort(int[][] segments) {
        for (int i = segments.length; i >= 0; i--) {
            for (int j = 0; j < i - 1; j++) {
                if (segments[j][0] > segments[j + 1][0]) {
                    int[] segment = segments[j];
                    segments[j] = segments[j + 1];
                    segments[j + 1] = segment;
                }
            }
        }
    }

    public static int calculateMaxCoverCheck(List<Segment> segments) {
        int maxCover = 0;
        int minStart = 0;
        int maxEnd = 0;
        for (Segment segment : segments) {
            minStart = Math.min(segment.getStart(), minStart);
            maxEnd = Math.max(segment.getEnd(), maxEnd);
        }
        for (double i = minStart; i <= maxEnd; i = i + 0.5) {
            int tempMaxCover = 0;
            for (Segment segment : segments) {
                if (segment.getStart() < i && segment.getEnd() > i) {
                    tempMaxCover++;
                }
            }
            maxCover = Math.max(maxCover, tempMaxCover);
        }
        return maxCover;
    }

    public List<Segment> randomSegments(int nums, int start, int end) {
        Random random = new Random();
        int range = end - start;
        int count = 0;
        List<Segment> segments = new ArrayList<Segment>(nums);
        while (count < nums) {
            int tempStart = random.nextInt(range);
            int maxLength = end - tempStart;
            if (maxLength == 0) {
                continue;
            }
            int tempEnd = tempStart + random.nextInt(maxLength);
            if (tempEnd == tempStart) {
                continue;
            }
            segments.add(new Segment(tempStart, tempEnd));
            count++;
        }
        return segments;
    }

    public int[][] randomSegmentsArr(int nums, int start, int end) {
        Random random = new Random();
        int range = end - start;
        int count = 0;
        int[][] segments = new int[nums][2];
        while (count < nums) {
            int tempStart = random.nextInt(range);
            int maxLength = end - tempStart;
            if (maxLength == 0) {
                continue;
            }
            int tempEnd = tempStart + random.nextInt(maxLength);
            if (tempEnd == tempStart) {
                continue;
            }
            segments[count][0] = tempStart;
            segments[count][1] = tempEnd;
            count++;
        }
        return segments;
    }

    @Test
    public void testRandomSegments() {
        List<Segment> segments = randomSegments(10, 1, 100);
        log.info("random segments:{}", segments);
        segments.sort(Comparator.comparingInt(Segment::getStart));
        log.info("sort segments:{}", segments);
    }

    @Test
    public void testCalculateMaxCover() {
        int count = 0;
        for (int i = 0; i < 1000000; i++) {
            List<Segment> segments = randomSegments(10, 1, 100);
            //log.info("random segments:{}", segments);
            int maxCover = calculateMaxCover(segments);
            //log.info("最大重叠线段数:{}", maxCover);
            int checkResult = calculateMaxCoverCheck(segments);
            //log.info("对数器结果:{}", checkResult);
            boolean same = maxCover == checkResult;
            if (!same) {
                log.info("重叠数不一致");
            } else {
                count++;
            }
        }
        log.info("全部执行成功次数:{}", count);
    }

    @Test
    public void testRandomSegmentsArr() {
        int[][] segments = randomSegmentsArr(10, 1, 100);
        for (int[] segment : segments) {
            System.out.print(segment[0] + "-->" + segment[1] + ";");
        }
        System.out.println("");
        sort(segments);

        for (int[] segment : segments) {
            System.out.print(segment[0] + "-->" + segment[1] + ";");
        }
        System.out.println("");
    }

    @Data
    @AllArgsConstructor
    public static class Segment {
        private int start;
        private int end;

        @Override
        public String toString() {
            return start + "-->" + end;
        }
    }

}

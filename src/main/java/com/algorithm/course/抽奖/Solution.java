package com.algorithm.course.抽奖;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author by catface
 * @date 2021/4/30 4:07 下午
 */
@Slf4j
public class Solution {

    public int[] getTenPeople(int joinPeopleNum) {
        if (joinPeopleNum <= 10) {
            int[] people = new int[joinPeopleNum];
            for (int i = 1; i <= joinPeopleNum; i++) {
                people[i - 1] = i;
            }
            return people;
        }
        int[] people = new int[10];
        for (int i = 1; i <= 10; i++) {
            people[i - 1] = i;
        }
        for (int i = 11; i <= joinPeopleNum; i++) {
            int rate = (int)(Math.random() * i);
            // 进入中奖池
            if (rate < 10) {
                people[rate] = i;
            }
        }
        return people;
    }

    public static class TestClass {
        @Test
        public void testLess10() {
            int peopleNum = 7;
            Solution solution = new Solution();
            int[] people = solution.getTenPeople(peopleNum);
            log.info("中奖人数:{}", people);
        }

        @Test
        public void testMoreThan10() {
            int peopleNum = 1000000;
            Solution solution = new Solution();
            int[] people = solution.getTenPeople(peopleNum);
            log.info("中奖人数:{}", people);
        }
    }
}

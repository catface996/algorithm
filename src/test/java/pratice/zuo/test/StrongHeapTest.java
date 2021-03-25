package pratice.zuo.test;

import java.util.Comparator;
import java.util.Random;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import pratice.data.Student;
import pratice.zuo.test.堆.加强堆.StrongHeap;

/**
 * @author by catface
 * @date 2021/3/22 11:06 上午
 */
@Slf4j
public class StrongHeapTest {

    @Test
    public void testStudent() {
        StrongHeap<Student> studentStrongHeap = new StrongHeap<>(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getAge() - o2.getAge();
            }
        });

        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int age = random.nextInt(100);
            Student student = new Student();
            student.setAge(age);
            student.setName(age + "岁");
            student.setId(i);
            studentStrongHeap.push(student);
        }

        for (int i = 0; i < 10; i++) {
            Student student = studentStrongHeap.peek();
            studentStrongHeap.remove(student);
        }

        log.info("strongHeap:{}", studentStrongHeap);

    }
}


package pratice.data;

import lombok.Data;

/**
 * @author by catface
 * @date 2021/3/22 11:06 上午
 */
@Data
public class Student {

    private Integer age;

    private String name;

    private Integer id;

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        Student student = (Student)o;
        return student.equals(this);
    }

}

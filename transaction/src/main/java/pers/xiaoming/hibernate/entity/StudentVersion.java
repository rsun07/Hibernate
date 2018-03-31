package pers.xiaoming.hibernate.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StudentVersion extends Student {
    private int version;

    public StudentVersion(Student student) {
        this.setId(student.getId());
        this.setAge(student.getAge());
        this.setName(student.getName());
        this.setScore(student.getScore());
    }
}

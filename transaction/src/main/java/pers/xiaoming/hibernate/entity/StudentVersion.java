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
    private Integer id;
    private String name;
    private Integer age;
    private Double score;
    private int version;

    public StudentVersion(Student student) {
        this.id = student.getId();
        this.age = student.getAge();
        this.name = student.getName();
        this.score = student.getScore();
    }
}

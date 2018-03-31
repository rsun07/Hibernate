package pers.xiaoming.hibernate.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StudentTimestamp extends Student {
    // if inherit fields from super class
    // the db mapping cannot find fields
    private Integer id;
    private String name;
    private Integer age;
    private Double score;
    private Timestamp timestamp;

    public StudentTimestamp(Student student) {
        this.id = student.getId();
        this.age = student.getAge();
        this.name = student.getName();
        this.score = student.getScore();
    }
}

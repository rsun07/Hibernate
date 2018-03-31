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
    private Timestamp timestamp;

    public StudentTimestamp(Student student) {
        this.setId(student.getId());
        this.setAge(student.getAge());
        this.setName(student.getName());
        this.setScore(student.getScore());
    }
}

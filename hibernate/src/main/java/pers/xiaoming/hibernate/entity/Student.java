package pers.xiaoming.hibernate.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Student {
    private Integer id;
    private String name;
    private Integer age;
    private Double score;
}

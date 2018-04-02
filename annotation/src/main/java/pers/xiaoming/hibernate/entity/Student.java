package pers.xiaoming.hibernate.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity
@Table(name = "t_student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "t_id")
    private Integer id;

    @Column(name = "t_name")
    private String name;

    // @Basic is optional, means this field should be mapping into DB
    @Basic
    @Column(name = "t_age")
    private Integer age;

    // @Transient Don't map this field into db attribute
    @Transient
    private Double score;

    // Constructor for projection age and score
    public Student(Integer age, Double score) {
        this.age = age;
        this.score = score;
    }
}

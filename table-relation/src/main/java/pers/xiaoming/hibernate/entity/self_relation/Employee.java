package pers.xiaoming.hibernate.entity.self_relation;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class Employee {
    private Integer id;
    private String name;
    private String title;
    private Employee manager;
    private Set<Employee> subordinators;

    public Employee() {
        this.subordinators = new HashSet<>();
    }

    public Employee(String name, String title) {
        this.name = name;
        this.title = title;
        this.subordinators = new HashSet<>();
    }
}

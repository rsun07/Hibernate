package pers.xiaoming.hibernate.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class City {
    private Integer id;
    private String name;
    private Set<Person> residents;

    public City(String name) {
        this.name = name;
        this.residents = new HashSet<>();
    }

    public City(String name, Set<Person> residents) {
        this.name = name;
        this.residents = residents;
    }
}

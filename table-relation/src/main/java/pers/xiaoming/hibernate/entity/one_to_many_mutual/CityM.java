package pers.xiaoming.hibernate.entity.one_to_many_mutual;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pers.xiaoming.hibernate.entity.one_to_many.Person;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CityM {
    private Integer id;
    private String name;
    private Set<Person> residents;

    public CityM(String name) {
        this.name = name;
        this.residents = new HashSet<>();
    }

    public CityM(String name, Set<Person> residents) {
        this.name = name;
        this.residents = residents;
    }
}

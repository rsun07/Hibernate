package pers.xiaoming.hibernate.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.collection.internal.PersistentSet;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@AllArgsConstructor
public class City {
    private Integer id;
    private String name;
    private Set<Person> residents;

    public City() {
        this.residents = new HashSet<>();
    }

    public City(String name) {
        this.name = name;
        this.residents = new HashSet<>();
    }

    public City(String name, Set<Person> residents) {
        this.name = name;
        this.residents = residents;
    }

    public City(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return Objects.equals(id, city.id) &&
                Objects.equals(name, city.name) &&
                checkSetEquals(city.residents);
    }

    private boolean checkSetEquals(Set<Person> set) {
        if (residents == set) return true;

        if (set == null) return false;
        if (residents.size() != set.size()) return false;

        // return residents.containsAll(set);

        // Here the Set will be converted to "org.hibernate.collection.internal.PersistentSet"
        // the PersistentSet's contains() and containsAll() function returns false
        // for two equal sets (at least equals by the following comparison)

        /*
        for (Person personToCheck : set) {
            boolean contains = false;
            for (Person person : residents) {
                if (person.equals(personToCheck)) {
                    contains = true;
                }
            }
            if (!contains) {
                return false;
            }
        }
        return true;
        */

        Set<Person> residentHashSet = new HashSet<>(residents);
        Set<Person> hashSet = new HashSet<>(set);

        return residentHashSet.containsAll(hashSet);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name);
    }
}

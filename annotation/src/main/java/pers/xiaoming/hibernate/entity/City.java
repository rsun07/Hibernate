package pers.xiaoming.hibernate.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Basic
    private String name;

    @OneToMany(targetEntity = Person.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "country_id")
    private Set<Person> residents;

    public City(String name) {
        this.name = name;
        this.residents = new HashSet<>();
    }

    public City(String name, Set<Person> residents) {
        this.name = name;
        this.residents = residents;
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

        // Doesn't work
        // return residents.containsAll(set);

        // Here the Set will be converted to "org.hibernate.collection.internal.PersistentSet"
        // the PersistentSet's contains() and containsAll() function returns false
        // for two equal sets (at least equals by the following comparison)

        // A potential cause is, the Persistent set is a proxy

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

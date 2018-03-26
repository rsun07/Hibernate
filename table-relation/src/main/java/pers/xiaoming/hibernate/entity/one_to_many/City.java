package pers.xiaoming.hibernate.entity.one_to_many;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class City {
    private Integer cid;
    private String cname;
    private Set<Person> residents;

    public City(String cname, Set<Person> residents) {
        this.cname = cname;
        this.residents = residents;
    }
}

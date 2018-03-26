package pers.xiaoming.hibernate.entity.one_to_many_mutual;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonM {
    private Integer id;
    private String name;
    private CityM city;

    public PersonM(String name) {
        this.name = name;
    }
}

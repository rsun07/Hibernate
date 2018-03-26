package pers.xiaoming.hibernate.entity.one_to_many;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private Integer pid;
    private String pname;

    public Person(String pname) {
        this.pname = pname;
    }
}

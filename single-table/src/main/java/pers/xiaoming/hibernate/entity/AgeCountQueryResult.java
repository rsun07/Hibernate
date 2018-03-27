package pers.xiaoming.hibernate.entity;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
// Must have No Args constructor
// because aliasToBean() will create an empty instance
// And then use the setter function to inject values;

// That's to say, the Other constructors are not necessary
@NoArgsConstructor

// Cannot be a inner class within the Get Class
public class AgeCountQueryResult {
    private Integer age;
    private Long ageCount;
}

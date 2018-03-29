package pers.xiaoming.hibernate.entity.self_relation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DBEmployee {
    private Integer id;
    private String name;
    private String title;
    private Integer manager_id;
}

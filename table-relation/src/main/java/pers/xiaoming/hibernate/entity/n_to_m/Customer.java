package pers.xiaoming.hibernate.entity.n_to_m;

import lombok.Data;

import java.util.List;

@Data
public class Customer {
    private Integer id;
    private String name;
    private List<Store> stores;
}

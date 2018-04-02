package pers.xiaoming.hibernate.entity.n_to_m;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class Customer {
    private Integer id;
    private String name;
    private Set<Store> stores;

    public Customer(String name) {
        this.name = name;
        this.stores = new HashSet<>();
    }
}

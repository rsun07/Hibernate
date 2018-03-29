package pers.xiaoming.hibernate.entity.n_to_m;

import lombok.Data;

import java.util.List;

@Data
public class Store {
    private Integer id;
    private String name;
    private String address;
    private List<Customer> customers;
}

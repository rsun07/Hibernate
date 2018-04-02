package pers.xiaoming.hibernate.n_to_m;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pers.xiaoming.hibernate.SessionManager;
import pers.xiaoming.hibernate.command.n_to_m.CreateCustomer;
import pers.xiaoming.hibernate.entity.n_to_m.Customer;
import pers.xiaoming.hibernate.entity.n_to_m.Store;

public class CreateTest {
    private Customer customer1;
    private Customer customer2;

    @BeforeTest
    public void setup() {

        customer1 = new Customer("Person1");
        customer2 = new Customer("Person2");

        final Store store1 = new Store("Store1", "Store1 Address");
        final Store store2 = new Store("Store2", "Store2 Address");
        final Store store3 = new Store("Store3", "Store3 Address");

        customer1.getStores().add(store1);
        customer1.getStores().add(store2);

        customer2.getStores().add(store1);
        customer2.getStores().add(store3);
    }

    @Test
    public void testCreate() throws Exception {
        CreateCustomer createCustomer = new CreateCustomer();
        createCustomer.create(customer1);
        createCustomer.create(customer2);
    }
}

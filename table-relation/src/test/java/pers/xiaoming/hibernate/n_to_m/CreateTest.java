package pers.xiaoming.hibernate.n_to_m;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pers.xiaoming.hibernate.SessionFactory;
import pers.xiaoming.hibernate.command.GetEntity;
import pers.xiaoming.hibernate.command.n_to_m.CreateCustomer;
import pers.xiaoming.hibernate.command.one_to_many.CreateCity;
import pers.xiaoming.hibernate.command.one_to_many.GetPerson;
import pers.xiaoming.hibernate.entity.n_to_m.Customer;
import pers.xiaoming.hibernate.entity.n_to_m.Store;
import pers.xiaoming.hibernate.entity.one_to_many.Person;

public class CreateTest {
    private Customer customer1;
    private Customer customer2;

    @BeforeTest
    public void setup() {

        customer1 = new Customer("Person1");
        customer2 = new Customer("Person2");

        Store store1 = new Store("Store1", "Store1 Address");
        Store store2 = new Store("Store1", "Store1 Address");
        Store store3 = new Store("Store1", "Store1 Address");

        customer1.getStores().add(store1);
        customer1.getStores().add(store2);

        customer2.getStores().add(store1);
        customer2.getStores().add(store3);
    }

    @Test
    public void testCreate() throws Exception {
        CreateCustomer createCustomer = new CreateCustomer();
        createCustomer.create(SessionFactory.getSession(), customer1);
        createCustomer.create(SessionFactory.getSession(), customer2);
//        verifyCreate();
    }

    private void verifyCreate() {
//        GetEntity<Person> getPerson = new GetPerson();
//        for (Person person : residents) {
//            Assert.assertEquals(person,
//                    getPerson.get(
//                            SessionFactory.getSession(),
//                            person.getId()));
//        }
    }
}

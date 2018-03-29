package pers.xiaoming.hibernate.self_relation;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pers.xiaoming.hibernate.SessionFactory;
import pers.xiaoming.hibernate.command.GetEntity;
import pers.xiaoming.hibernate.command.self_relation.CreateEmployee;
import pers.xiaoming.hibernate.command.self_relation.GetEmployee;
import pers.xiaoming.hibernate.entity.self_relation.Employee;

public class CreateTest {
    private Employee manager;

    @BeforeTest(enabled = false)
    public void setup() {
        manager = new Employee("John", "CEO");
        manager.getSubordinators().add(new Employee("Marry", "HR"));
        manager.getSubordinators().add(new Employee("Mike", "SDE"));
    }

    // fix after
    @Test(enabled = false)
    public void testCreate() throws Exception {
        CreateEmployee createEmployee = new CreateEmployee();
        createEmployee.create(SessionFactory.getSession(), manager);
        verifyCreate();
    }

    private void verifyCreate() throws Exception {
        GetEmployee getEmployee = new GetEmployee();

        for (Employee employee : manager.getSubordinators()) {
//             Assert.assertEquals(getEmployee.getManager(SessionFactory.getSession(), employee.getId()), manager);
        }
    }
}

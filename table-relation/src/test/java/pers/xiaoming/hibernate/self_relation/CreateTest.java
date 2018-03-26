package pers.xiaoming.hibernate.self_relation;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pers.xiaoming.hibernate.SessionFactory;
import pers.xiaoming.hibernate.command.GetEntity;
import pers.xiaoming.hibernate.command.self_relation.CreateEmployee;
import pers.xiaoming.hibernate.command.self_relation.GetEmployee;
import pers.xiaoming.hibernate.entity.self_relation.Employee;

public class CreateTest {
    private Employee manager;

    @BeforeTest
    public void setup() {
        manager = new Employee("John", "CEO");
        manager.getSubordinators().add(new Employee("Marry", "HR"));
        manager.getSubordinators().add(new Employee("Mike", "SDE"));
    }

    @Test
    public void testCreate() throws Exception {
        CreateEmployee createEmployee = new CreateEmployee();
        createEmployee.create(SessionFactory.getSession(), manager);
        verifyCreate();
    }

    private void verifyCreate() throws Exception {
        GetEntity<Employee> getEmployee = new GetEmployee();
        // TODO: select from foreigner key
        // Assert.assertEquals(manager, getEmployee.get(SessionFactory.getSession(), manager.getId()));

        // Cannot get the manager field from employee
        for (Employee employee : manager.getSubordinators()) {
            // Assert.assertEquals(employee, getEmployee.get(SessionFactory.getSession(), employee.getId()));
        }
    }
}

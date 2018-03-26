package pers.xiaoming.hibernate.self_relation;

import org.testng.annotations.Test;
import pers.xiaoming.hibernate.SessionManager;
import pers.xiaoming.hibernate.command.self_relation.CreateEmployee;
import pers.xiaoming.hibernate.entity.self_relation.Employee;

public class CreateTest {

    @Test
    public void testCreate() throws Exception {
        Employee manager = new Employee("John", "CEO");
        Employee hr = new Employee("Marry", "HR");
        Employee sde = new Employee("Mike", "SDE");

        manager.getSubordinators().add(hr);
        manager.getSubordinators().add(sde);

        CreateEmployee createEmployee = new CreateEmployee();
        createEmployee.create(SessionManager.getSession(), manager);
    }
}

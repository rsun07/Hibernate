package pers.xiaoming.hibernate.self_relation;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pers.xiaoming.hibernate.SessionManager;
import pers.xiaoming.hibernate.command.self_relation.CreateEmployee;
import pers.xiaoming.hibernate.command.self_relation.GetEmployee;
import pers.xiaoming.hibernate.entity.self_relation.DBEmployee;
import pers.xiaoming.hibernate.entity.self_relation.Employee;

public class CreateTest {
    private Employee manager;

    @BeforeTest
    public void setup() {
        manager = new Employee("John", "CEO");
        manager.getSubordinators().add(new Employee("Marry", "HR"));
        manager.getSubordinators().add(new Employee("Mike", "SDE"));
    }

    // fix after
    @Test
    public void testCreate() throws Exception {
        CreateEmployee createEmployee = new CreateEmployee();
        createEmployee.create(SessionManager.getSession(), manager);
        verifyCreate();
    }

    private void verifyCreate() throws Exception {
        GetEmployee getEmployee = new GetEmployee();

        for (Employee employee : manager.getSubordinators()) {

            DBEmployee managerReturn = getEmployee.getManager(SessionManager.getSession(), employee.getId());

            Assert.assertNull(managerReturn.getManager_id());
            Assert.assertEquals(managerReturn.getName(), manager.getName());
            Assert.assertEquals(managerReturn.getTitle(), manager.getTitle());
        }
    }
}

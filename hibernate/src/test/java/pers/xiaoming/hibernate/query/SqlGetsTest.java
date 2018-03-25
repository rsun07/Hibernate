package pers.xiaoming.hibernate.query;

import org.testng.annotations.Test;
import pers.xiaoming.hibernate.command.GetStudent;
import pers.xiaoming.hibernate.command.sql.BasicGet;
import pers.xiaoming.hibernate.entity.Student;
import pers.xiaoming.hibernate.session_factory.Server;

public class SqlGetsTest {
    @Test
    public void testGetStudent() {
        GetStudent getStudent = new BasicGet();
        Student student = getStudent.get(Server.getSession(), 1);
        System.out.println(student);
    }
}

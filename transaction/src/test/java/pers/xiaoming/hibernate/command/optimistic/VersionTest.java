package pers.xiaoming.hibernate.command.optimistic;

import org.testng.annotations.Test;
import pers.xiaoming.hibernate.InitDb;
import pers.xiaoming.hibernate.command.GetStudent;
import pers.xiaoming.hibernate.entity.Student;
import pers.xiaoming.hibernate.entity.StudentVersion;
import pers.xiaoming.hibernate.session_factory.SessionManager;

import java.util.List;

public class VersionTest {
    @Test
    public void testVersionNumber() {
        GetStudent dbOperator = new GetStudent();
        List<Student> studentsInit = dbOperator.getAll(SessionManager.getSession(), StudentVersion.class);
        System.out.println(studentsInit);
    }
}

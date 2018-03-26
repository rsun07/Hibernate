package pers.xiaoming.hibernate.query;

import org.hibernate.Session;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import pers.xiaoming.hibernate.command.basic.CURDStudentBasic;
import pers.xiaoming.hibernate.entity.Student;
import pers.xiaoming.hibernate.session_factory.SessionManager;

import java.util.ArrayList;
import java.util.List;

public class DataProcessor {

    private static final int NUM_OF_DATA_GENERATE = 10;
    private static List<Integer> ids;
    private static List<Student> students;

    public static List<Integer> getIds() {
        return ids;
    }

    @BeforeTest
    public static void dbDataPrepare() {
        CURDStudentBasic dbOperator = new CURDStudentBasic();
        ids = new ArrayList<>();
        students = new ArrayList<>();

        for (int i = 0; i < NUM_OF_DATA_GENERATE; i++) {
            Student student = Student.builder().name("John" + i)
                    .age(20 + i).score(80.0 + i).build();
            students.add(student);

            Session session = SessionManager.getSession();
            int id = dbOperator.create(session, student);
            ids.add(id);
        }
    }

    // As we truncate the table every time before suite test
    // this is redundant now
    @AfterTest
    public static void cleanup() {
        for(int id : ids) {
            CURDStudentBasic dbOperator = new CURDStudentBasic();

            Session session = SessionManager.getSession();
            dbOperator.delete(session, id);
        }
    }

    public static boolean validateStudent(Student student) {
        return students.contains(student);
    }
}

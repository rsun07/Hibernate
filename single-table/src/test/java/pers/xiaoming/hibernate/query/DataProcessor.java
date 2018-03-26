package pers.xiaoming.hibernate.query;

import lombok.Getter;
import org.hibernate.Session;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import pers.xiaoming.hibernate.command.basic.CURDStudentBasic;
import pers.xiaoming.hibernate.entity.Student;
import pers.xiaoming.hibernate.session_factory.SessionManager;

import java.util.ArrayList;
import java.util.List;

public class DataProcessor {

    @Getter
    private static final int NUM_OF_DATA_GENERATE = 10;

    @Getter
    private static final String NAME_PREFIX = "John";

    @Getter
    private static final int AGE_START = 20;

    @Getter
    private static final double SCORE_START = 80;

    @Getter
    private static List<Integer> ids;

    private static List<Student> students;

    @BeforeTest
    public static void dbDataPrepare() throws Exception {
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
    @AfterTest(enabled = false)
    public static void cleanup() throws Exception {
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

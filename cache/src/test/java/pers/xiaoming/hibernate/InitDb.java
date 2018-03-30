package pers.xiaoming.hibernate;

import org.hibernate.Session;
import org.testng.annotations.BeforeSuite;
import pers.xiaoming.hibernate.command.CreateStudent;
import pers.xiaoming.hibernate.entity.Student;
import pers.xiaoming.hibernate.session_factory.SessionManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InitDb {

    private static final int NUM_OF_DATA_GENERATE = 2;

    private static final String NAME_PREFIX = "John";

    private static final int AGE_START = 20;

    private static final double SCORE_START = 80;

    private static List<Integer> ids;

    private static List<Student> students;

    @BeforeSuite
    public static void initData() {
        ids = new ArrayList<>();
        students = new ArrayList<>();

        CreateStudent dbOperator = new CreateStudent();
        Random random = new Random();

        /*
         *
         * The result will be :
         * name: John0, John1, John2 ... John9
         * age: 20, 21, 22, 20, 21, 22 ...
         * score: integer between 80 and 100
         *
         */

        for (int i = 0; i < NUM_OF_DATA_GENERATE; i++) {
            Student student = Student.builder()
                    .name("John" + i)
                    .age(20 + i%3)
                    .score(80.0 + random.nextInt(20))
                    .build();
            students.add(student);

            Session session = SessionManager.getSession();
            int id = dbOperator.create(session, student);
            ids.add(id);
        }
    }
}

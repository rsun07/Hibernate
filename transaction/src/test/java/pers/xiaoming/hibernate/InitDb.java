package pers.xiaoming.hibernate;

import org.testng.annotations.BeforeSuite;
import pers.xiaoming.hibernate.command.CreateStudent;
import pers.xiaoming.hibernate.entity.Student;
import pers.xiaoming.hibernate.entity.StudentTimestamp;
import pers.xiaoming.hibernate.entity.StudentVersion;
import pers.xiaoming.hibernate.session_factory.SessionManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InitDb {

    private static final int NUM_OF_DATA_GENERATE = 2;

    private static final String NAME_PREFIX = "John";

    private static final int AGE_START = 20;

    private static final double SCORE_START = 80;

    private static List<Student> students;

    @BeforeSuite
    public static void initData() {
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
                    .name(NAME_PREFIX + i)
                    .age(AGE_START + i%3)
                    .score(SCORE_START + random.nextInt((int)(100 - SCORE_START)))
                    .build();
            students.add(student);

            Student studentVersion = new StudentVersion(student);
            Student studentTimestamp = new StudentTimestamp(student);

            // for optimistic lock
            dbOperator.create(SessionManager.getSession(), studentVersion);
            dbOperator.create(SessionManager.getSession(), studentTimestamp);

            // for pessimistic lock
            dbOperator.create(SessionManager.getSession(), student);
        }
    }

    public static List<Student> getStudents() {
        return students;
    }
}

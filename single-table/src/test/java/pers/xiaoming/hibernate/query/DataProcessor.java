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
import java.util.Random;

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

            int id = dbOperator.create(student);
            ids.add(id);
        }
    }

    // As we truncate the table every time before suite test
    // this is redundant now
    @AfterTest(enabled = false)
    public static void cleanup() throws Exception {
        for(int id : ids) {
            CURDStudentBasic dbOperator = new CURDStudentBasic();

            dbOperator.delete(id);
        }
    }

    public static boolean validateStudent(Student student) {
        return students.contains(student);
    }
}

package pers.xiaoming.hibernate.query;

import org.hibernate.Session;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import pers.xiaoming.hibernate.command.basic.CURDStudentBasic;
import pers.xiaoming.hibernate.entity.Student;
import pers.xiaoming.hibernate.session_factory.Server;

import java.util.ArrayList;
import java.util.List;

public class DataPreparator {

    private static final int NUM_OF_DATA_GENERATE = 10;
    private List<Integer> ids;

    @BeforeTest
    public void dbDataPrepare() {
        CURDStudentBasic dbOperator = new CURDStudentBasic();
        ids = new ArrayList<>();

        for (int i = 0; i < NUM_OF_DATA_GENERATE; i++) {
            Student student = Student.builder().name("John" + i)
                    .age(20 + i).score(80.0 + i).build();

            Session session = Server.getSession();
            int id = dbOperator.create(session, student);
            ids.add(id);
        }
    }

    @AfterTest
    public void cleanup() {
        for(int id : ids) {
            CURDStudentBasic dbOperator = new CURDStudentBasic();

            Session session = Server.getSession();
            dbOperator.delete(session, id);
        }
    }
}

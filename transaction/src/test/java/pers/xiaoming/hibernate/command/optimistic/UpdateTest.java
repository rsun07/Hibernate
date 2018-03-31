package pers.xiaoming.hibernate.command.optimistic;

import org.testng.Assert;
import org.testng.annotations.Test;
import pers.xiaoming.hibernate.command.GetStudent;
import pers.xiaoming.hibernate.command.UpdateStudent;
import pers.xiaoming.hibernate.entity.Student;
import pers.xiaoming.hibernate.entity.StudentTimestamp;
import pers.xiaoming.hibernate.entity.StudentVersion;
import pers.xiaoming.hibernate.session_factory.SessionManager;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class UpdateTest {
    private Student before;
    private Student after;

    /*
     Hibernate:
        update
            StudentVersion
        set
            version=?,
            name=?,
            age=?,
            score=?
        where
            id=?
            and version=?

        +----+---------+-------+------+-------+
        | id | version | name  | age  | score |
        +----+---------+-------+------+-------+
        |  1 |       1 | Mike  |   20 |    81 |
        |  2 |       0 | John1 |   21 |    94 |
        +----+---------+-------+------+-------+
     */
    @Test
    public void testVersionNumber() throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        test(StudentVersion.class);
        Assert.assertTrue(((StudentVersion)after).getVersion() > ((StudentVersion)before).getVersion());
    }

    /*
        Hibernate:
            update
                StudentTimestamp
            set
                timestamp=?,
                name=?,
                age=?,
                score=?
            where
                id=?
                and timestamp=?

        +----+----------------------------+-------+------+-------+
        | id | timestamp                  | name  | age  | score |
        +----+----------------------------+-------+------+-------+
        |  1 | 2018-03-31 13:42:18.679000 | Mike  |   20 |    85 |
        |  2 | 2018-03-31 13:42:08.295000 | John1 |   21 |    92 |
        +----+----------------------------+-------+------+-------+
     */

    @Test
    public void testTimestamp() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        test(StudentTimestamp.class);
        Assert.assertTrue(((StudentTimestamp)after).getTimestamp().after(((StudentTimestamp)before).getTimestamp()));
    }

    @SuppressWarnings("unchecked")
    private void test(Class studentClass) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        GetStudent getOperator = new GetStudent();
        UpdateStudent updateOperator = new UpdateStudent();

        List<Student> studentsInit = getOperator.getAll(SessionManager.getSession(), studentClass);

        int updateId = 0;
        int updateDbId = 1;
        String updatedName = "Mike";

        Student updatedStudent = (Student) studentClass.getConstructor(Student.class).newInstance(studentsInit.get(updateId)); ;
        updatedStudent.setName(updatedName);
        updatedStudent.setId(updateDbId);

        boolean isUpdated = updateOperator.update(SessionManager.getSession(), updatedStudent, studentClass);

        List<Student> studentsUpdated = getOperator.getAll(SessionManager.getSession(), studentClass);

        Assert.assertTrue(isUpdated);
        // the version number of timestamp for non updated records won't change
        if (updateId != 1) {
            int nonUpdateId = 1;
            Assert.assertEquals(studentsUpdated.get(nonUpdateId), studentsInit.get(nonUpdateId));
        }

        // the version number of timestamp for updated records will increase
        before =  studentsInit.get(updateId);
        after =  studentsUpdated.get(updateId);
        Assert.assertEquals(after.getId(), before.getId());
        Assert.assertEquals(after.getAge(), before.getAge());
        Assert.assertEquals(after.getScore(), before.getScore());

        Assert.assertNotEquals(after.getName(), before.getName());
    }
}

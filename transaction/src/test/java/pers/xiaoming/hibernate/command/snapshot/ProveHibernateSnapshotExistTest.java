package pers.xiaoming.hibernate.command.snapshot;

import org.testng.Assert;
import org.testng.annotations.Test;
import pers.xiaoming.hibernate.InitDb;
import pers.xiaoming.hibernate.command.GetStudent;
import pers.xiaoming.hibernate.entity.Student;
import pers.xiaoming.hibernate.session_factory.SessionManager;

public class ProveHibernateSnapshotExistTest {

    /*
    Hibernate:
        select
            student0_.id as id1_0_0_,
            student0_.name as name2_0_0_,
            student0_.age as age3_0_0_,
            student0_.score as score4_0_0_
        from
            Student student0_
        where
            student0_.id=?
    Hibernate:
        update
            Student
        set
            name=?,
            age=?,
            score=?
        where
            id=?
    Hibernate:
        select
            student0_.id as id1_0_0_,
            student0_.name as name2_0_0_,
            student0_.age as age3_0_0_,
            student0_.score as score4_0_0_
        from
            Student student0_
        where
            student0_.id=?
     */
    @Test(enabled = false)
    public void testUpdateName() {
        int testId = 0;
        int dbId = testId + 1;

        String updatedName = "Mike";

        Student student = InitDb.getStudent(testId);

        ProveSnapshotExist dbOperator = new ProveSnapshotExist();

        dbOperator.run(SessionManager.getSession(), dbId, student.getName(), updatedName);

        Student stuAfterUpdate = new GetStudent().get(SessionManager.getSession(), dbId);

        Assert.assertEquals(stuAfterUpdate.getScore(), student.getScore());
        Assert.assertEquals(stuAfterUpdate.getAge(), student.getAge());

        Assert.assertEquals(stuAfterUpdate.getName(), updatedName);
    }

    /*
    No update:

    Hibernate:
        select
            student0_.id as id1_0_0_,
            student0_.name as name2_0_0_,
            student0_.age as age3_0_0_,
            student0_.score as score4_0_0_
        from
            Student student0_
        where
            student0_.id=?
    Hibernate:
        select
            student0_.id as id1_0_0_,
            student0_.name as name2_0_0_,
            student0_.age as age3_0_0_,
            student0_.score as score4_0_0_
        from
            Student student0_
        where
            student0_.id=?

     */
    @Test
    public void testNoUpdate() {
        int testId = 0;
        int dbId = testId + 1;

        Student student = InitDb.getStudent(testId);

        String updatedName = student.getName();

        ProveSnapshotExist dbOperator = new ProveSnapshotExist();

        dbOperator.run(SessionManager.getSession(), dbId, student.getName(), updatedName);

        Student stuAfterUpdate = new GetStudent().get(SessionManager.getSession(), dbId);

        Assert.assertEquals(stuAfterUpdate.getScore(), student.getScore());
        Assert.assertEquals(stuAfterUpdate.getAge(), student.getAge());
        Assert.assertEquals(stuAfterUpdate.getName(), student.getName());
    }
}

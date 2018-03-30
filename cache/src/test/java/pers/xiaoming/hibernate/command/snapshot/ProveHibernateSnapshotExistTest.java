package pers.xiaoming.hibernate.command.snapshot;

import org.testng.Assert;
import org.testng.annotations.Test;
import pers.xiaoming.hibernate.InitDb;
import pers.xiaoming.hibernate.command.GetStudent;
import pers.xiaoming.hibernate.entity.Student;
import pers.xiaoming.hibernate.session_factory.SessionManager;

public class ProveHibernateSnapshotExistTest {

    @Test
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
}

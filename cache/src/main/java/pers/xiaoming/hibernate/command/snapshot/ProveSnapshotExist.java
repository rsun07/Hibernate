package pers.xiaoming.hibernate.command.snapshot;

import org.hibernate.Session;
import pers.xiaoming.hibernate.entity.Student;

public class ProveSnapshotExist {
    public void run(Session session, String originName, String updatedName) {
        try {
            session.beginTransaction();

            // Student 1 should have name as "John0" from the data initializer
            Student student = session.get(Student.class, 1);

            assert student.getName().equals(originName);

            student.setName(updatedName);

            // only update the Java Heap,
            // No db save or update command here

            // at the time transaction commit,
            // it will check hibernate snapshot with session cache
            // if changes, update. Otherwise, ignore.

            session.getTransaction().commit();

        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }
}

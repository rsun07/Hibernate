package pers.xiaoming.hibernate.command;

import org.hibernate.Session;
import pers.xiaoming.hibernate.entity.Student;

public class UpdateStudent {
    public boolean update(Session session, Student updatedStudent, Class studentClass) {
        try {
            session.beginTransaction();

            @SuppressWarnings("unchecked")
            Student student = (Student) session.get(studentClass, updatedStudent.getId());

            if (updatedStudent.getName() != null) {
                student.setName(updatedStudent.getName());
            }

            // ignore the update other fields

            session.update(student);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }
}

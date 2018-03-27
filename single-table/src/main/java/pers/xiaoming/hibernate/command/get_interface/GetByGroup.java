package pers.xiaoming.hibernate.command.get_interface;

import org.hibernate.Session;
import pers.xiaoming.hibernate.entity.Student;

import java.util.List;

public interface GetByGroup {
    List<Student> get(Session session, int appearance);
}

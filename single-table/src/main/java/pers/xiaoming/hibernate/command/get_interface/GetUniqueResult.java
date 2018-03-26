package pers.xiaoming.hibernate.command.get_interface;

import org.hibernate.Session;
import pers.xiaoming.hibernate.entity.Student;

public interface GetUniqueResult {
    Student get(Session session, int id) throws Exception;
}

package pers.xiaoming.hibernate.command.get_interface;

import org.hibernate.Session;
import pers.xiaoming.hibernate.entity.Student;

import java.util.List;

public interface GetByFuzzyName {
    List<Student> get(Session session, String fuzzyName) throws Exception;
}

package pers.xiaoming.hibernate.command.get_interface;

import org.hibernate.Session;
import pers.xiaoming.hibernate.entity.Student;

import java.util.List;

public interface GetByOrder {
    List<Student> get(String orderByField, QueryOrder queryOrder, int maxResult) throws Exception;

    enum QueryOrder {
        DESC,
        ASC;
    }
}

package pers.xiaoming.hibernate.command.get_interface;

import org.hibernate.Session;

import java.util.List;

public interface GetByGroup {
    List<Object> get(Session session, int appearance);
}

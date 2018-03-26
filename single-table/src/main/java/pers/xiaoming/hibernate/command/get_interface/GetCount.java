package pers.xiaoming.hibernate.command.get_interface;

import org.hibernate.Session;

public interface GetCount {
    Long get(Session session);
}

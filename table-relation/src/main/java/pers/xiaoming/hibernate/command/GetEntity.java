package pers.xiaoming.hibernate.command;

import org.hibernate.Session;

public interface GetEntity<T> {
    T get(Session session, int id);
}

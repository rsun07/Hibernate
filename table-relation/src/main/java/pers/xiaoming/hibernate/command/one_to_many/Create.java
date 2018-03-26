package pers.xiaoming.hibernate.command.one_to_many;

import org.hibernate.Session;
import pers.xiaoming.hibernate.SessionManager;
import pers.xiaoming.hibernate.entity.one_to_many.Person;

public class Create {
    public void create() {
        Session session = SessionManager.getSession();

        try {
            session.beginTransaction();

            Person person1 = new Person();
        } catch (Exception e) {

        }
    }
}

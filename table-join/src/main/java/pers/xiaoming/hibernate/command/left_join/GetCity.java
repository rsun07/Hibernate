package pers.xiaoming.hibernate.command.left_join;

import org.hibernate.Session;
import pers.xiaoming.hibernate.entity.City;

public interface GetCity {
    City get(Session session, int cityId) throws Exception;
}

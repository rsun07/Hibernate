package pers.xiaoming.hibernate.command;

import org.hibernate.Session;
import pers.xiaoming.hibernate.entity.City;

public interface GetCity {
    City get(Session session, int cityId) throws Exception;
}

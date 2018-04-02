package pers.xiaoming.hibernate.command;

import pers.xiaoming.hibernate.entity.City;

public interface GetCity {
    City get(int cityId) throws Exception;
}

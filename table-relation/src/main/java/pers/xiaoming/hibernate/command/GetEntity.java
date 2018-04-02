package pers.xiaoming.hibernate.command;

public interface GetEntity<T> {
    T get(int id);
}

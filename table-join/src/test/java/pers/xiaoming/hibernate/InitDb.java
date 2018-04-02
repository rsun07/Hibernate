package pers.xiaoming.hibernate;

import org.testng.annotations.BeforeSuite;
import pers.xiaoming.hibernate.command.create.CreateCity;
import pers.xiaoming.hibernate.entity.City;
import pers.xiaoming.hibernate.entity.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InitDb {
    private static final int TOTAL_CITY = 3;
    private static final List<Integer> NO_PERSON_IN_CITIES = Arrays.asList(2, 1, 5);

    private static List<City> cities = new ArrayList<>(TOTAL_CITY);

    public static List<City> getCities() {
        return cities;
    }

    @BeforeSuite
    public static void initDao() {
        SessionManager.getSession();
    }

    @BeforeSuite
    public static void initData() throws Exception {
        CreateCity createCity = new CreateCity();
        for (int i = 0; i < TOTAL_CITY; i++) {
            City city = new City("City" + i);
            for (int j = 0; j < NO_PERSON_IN_CITIES.get(i); j++) {
                Person person = new Person("City" + i + "_Person" + j);
                city.getResidents().add(person);
            }
            createCity.create(city);
            cities.add(city);
        }
    }
}

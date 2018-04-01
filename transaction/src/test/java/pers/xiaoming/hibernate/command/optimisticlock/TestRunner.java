package pers.xiaoming.hibernate.command.optimisticlock;

import org.testng.TestNG;
import pers.xiaoming.hibernate.InitDb;

public class TestRunner {
    // run test NG as a Java program
    // Another way to run TestNG
    public static void main(String[] args) {
        TestNG testNG = new TestNG();
        testNG.setTestClasses(new Class[] {InitDb.class, DirtyReadTest.class});
        testNG.run();
    }

}

package pers.xiaoming.hibernate.command.list_vs_iterate;

import org.testng.annotations.Test;
import pers.xiaoming.hibernate.session_factory.SessionManager;

public class GetTest {

    /*
     * Demo not a test. No Assertion. Disabled
     *
     * For get by list(), it will do two separate queries
     *

     Simply full table scan query twice:

        Hibernate:
            select
                student0_.id as id1_0_,
                student0_.name as name2_0_,
                student0_.age as age3_0_,
                student0_.score as score4_0_
            from
                Student student0_
        Student(id=1, name=John0, age=20, score=99.0)
        Student(id=2, name=John1, age=21, score=95.0)

        ------Separator for two queries--------

        Hibernate:
            select
                student0_.id as id1_0_,
                student0_.name as name2_0_,
                student0_.age as age3_0_,
                student0_.score as score4_0_
            from
                Student student0_
        Student(id=1, name=John0, age=20, score=99.0)
        Student(id=2, name=John1, age=21, score=95.0)
     *
     */
    @Test(enabled = false)
    public void testGetByList() {
        GetByList dbOperator = new GetByList();

        dbOperator.getAll(SessionManager.getSession());
    }

    /*
        1. Lazy initialize : Only Select the ids
        Hibernate:
            select
                student0_.id as col_0_0_
            from
                Student student0_

        2. When the detail attributes are called, query again with the id.
        Hibernate:
            select
                student0_.id as id1_0_0_,
                student0_.name as name2_0_0_,
                student0_.age as age3_0_0_,
                student0_.score as score4_0_0_
            from
                Student student0_
            where
                student0_.id=?
        Student(id=1, name=John0, age=20, score=92.0)

        3. Same as Step 2
        Hibernate:
            select
                student0_.id as id1_0_0_,
                student0_.name as name2_0_0_,
                student0_.age as age3_0_0_,
                student0_.score as score4_0_0_
            from
                Student student0_
            where
                student0_.id=?
        Student(id=2, name=John1, age=21, score=97.0)

        ------Separator for two queries--------

        4. Second query all Students, also lazy initialization, only query the ids
        Hibernate:
            select
                student0_.id as col_0_0_
            from
                Student student0_

        5. Read the results from Session cache, no need to query db again
        Only iterate() use the cache, list() won't
        And the Session cache is valid within a Transaction only.

        Student(id=1, name=John0, age=20, score=92.0)
        Student(id=2, name=John1, age=21, score=97.0)
     */

    @Test(enabled = false)
    public void testGetByIterate() {
        GetByIterate dbOperator = new GetByIterate();

        dbOperator.getAll(SessionManager.getSession());
    }


    /*
    list() will fetch all data once and load into Session cache.
    But list() never read from cache but do another query
    iterate() use lazy initialize strategy, query the id only first,
    then query details if needed.
    When query details, iterate() will query cache first then db.

    If full scan needed only once, iterate() will do N+1 more queries than list()
    When full scan needed for multiple times, it will be nice to try to do
    list() first, load everything into cache, then use iterator to do whatever detail query from the cache


     */
    @Test
    public void testGetByListThenIterate() {

    }
}

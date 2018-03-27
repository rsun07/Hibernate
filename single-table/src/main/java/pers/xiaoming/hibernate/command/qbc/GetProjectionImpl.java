package pers.xiaoming.hibernate.command.qbc;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import pers.xiaoming.hibernate.command.get_interface.GetProjection;
import pers.xiaoming.hibernate.entity.Student;

import java.util.Arrays;
import java.util.List;

public class GetProjectionImpl implements GetProjection {

    @SuppressWarnings("unchecked")
    public List<Student> get(Session session, int age) throws Exception {
        try {
            session.beginTransaction();

            Criteria criteria = session.createCriteria(Student.class);

            // set projection list
            ProjectionList projectionList = Projections.projectionList();

            // similar to that in sql
            // must set alias to allow the aliasToBean() Transformer to do the projection
            // alias name should be the same as field names in Java Class
            projectionList.add(Projections.property("age"), "age");
            projectionList.add(Projections.property("score"), "score");
            criteria.setProjection(projectionList);

            criteria.add(Restrictions.gt("age", age));

            criteria.setResultTransformer(Transformers.aliasToBean(Student.class));

            List<Student> students = criteria.list();

            session.getTransaction().commit();

            return students;

        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }
}

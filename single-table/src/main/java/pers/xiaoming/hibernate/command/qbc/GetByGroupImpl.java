package pers.xiaoming.hibernate.command.qbc;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;
import pers.xiaoming.hibernate.command.get_interface.GetByGroup;
import pers.xiaoming.hibernate.entity.AgeCountQueryResult;
import pers.xiaoming.hibernate.entity.Student;
import pers.xiaoming.hibernate.session_factory.SessionManager;

import java.util.ArrayList;
import java.util.List;

// Criteria doesn't support HAVING operation
public class GetByGroupImpl implements GetByGroup {

    @Override
    @SuppressWarnings("unchecked")
    public List<Object> get(int appearance) {
        Session session = SessionManager.getSession();

        try {
            session.beginTransaction();

            // Criteria doesn't support HAVING operation
            Criteria criteria = session.createCriteria(Student.class);

            ProjectionList projectionList = Projections.projectionList();
            projectionList.add(Projections.groupProperty("age"), "age");
            projectionList.add(Projections.count("age"), "ageCount");
            criteria.setProjection(projectionList);

            criteria.setResultTransformer(Transformers.aliasToBean(AgeCountQueryResult.class));

            List<AgeCountQueryResult> results = criteria.list();


            session.getTransaction().commit();

            return filterByAppearance(results, appearance);

        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }

    private List<Object> filterByAppearance(List<AgeCountQueryResult> results, int appearance) {
        List<Object> list = new ArrayList<>();
        for (AgeCountQueryResult result : results) {
            if (result.getAgeCount() > appearance) {
                list.add(result.getAge());
            }
        }
        return list;
    }
}

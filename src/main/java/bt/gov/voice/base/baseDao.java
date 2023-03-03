package bt.gov.voice.base;

import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Properties;

@Transactional(value = "txManager")
public abstract class baseDao {

    protected EntityManager em;

    @Resource(name = "queryProps")
    protected Properties properties;

    @PersistenceContext(unitName = "default")
    public void setEm(EntityManager em) {
        this.em = em;
    }

    protected Session getCurrentSession() {
        return em.unwrap(Session.class);
    }

    protected Session getReportingSession() {
        return getCurrentSession();
    }

    protected Session getArchivalSession() {
        return getCurrentSession();
    }

    protected org.hibernate.Query hibernateQuery(String query, Class dtoClazz) {
        return getCurrentSession()
                .createSQLQuery(query)
                .setResultTransformer(Transformers.aliasToBean(dtoClazz));
    }

    protected org.hibernate.Query hibernateReportingQuery(String query, Class dtoClazz) {
        return getReportingSession()
                .createSQLQuery(query)
                .setResultTransformer(Transformers.aliasToBean(dtoClazz));
    }

    protected org.hibernate.Query hibernateArchivalQuery(String query, Class dtoClazz) {
        return getArchivalSession()
                .createSQLQuery(query)
                .setResultTransformer(Transformers.aliasToBean(dtoClazz));
    }

    protected org.hibernate.Query hibernateQuery(String query) {
        return getCurrentSession().createSQLQuery(query);
    }

    protected org.hibernate.Query hibernateReportingQuery(String query) {
        return getReportingSession().createSQLQuery(query);
    }

    protected org.hibernate.Query hibernateArchivalQuery(String query) {
        return getArchivalSession().createSQLQuery(query);
    }

    protected Query persistenceQuery(String query, Class entityClazz) {
        return em.createNativeQuery(query, entityClazz);
    }

    protected void saveOrUpdate(Object obj) {
        getCurrentSession().saveOrUpdate(obj);
    }

    protected Query persistenceQuery(String query) {
        return em.createNativeQuery(query);
    }

    protected org.hibernate.query.Query sqlQuery(String query) {
        return getCurrentSession().createSQLQuery(query);
    }

    protected org.hibernate.Query hibernateQueryToken(String query, Class dtoClazz) {
        return getCurrentSession().createSQLQuery(query)
                .addScalar("id", StandardBasicTypes.INTEGER)
                .addScalar("access_token", StandardBasicTypes.STRING)
                .addScalar("scope", StandardBasicTypes.STRING)
                .addScalar("token_type", StandardBasicTypes.STRING)
                .addScalar("created_on", StandardBasicTypes.LONG)
                .addScalar("expires_in", StandardBasicTypes.INTEGER)
                .setResultTransformer(Transformers.aliasToBean(dtoClazz));
    }


}





























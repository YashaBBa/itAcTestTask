package connection;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateConfig {

    private static SessionFactory sessionFactory;


    static {

        Configuration configuration = new Configuration();
        try {
            configuration.configure();
            sessionFactory = configuration.buildSessionFactory();
        } catch (HibernateException ex) {
            throw new HibernateException("Error configuring Hibernate or building SessionFactory", ex);
        }


    }

    public static Session getSession() throws HibernateException {
        try {
            return sessionFactory.openSession();
        } catch (HibernateException ex) {
            throw new HibernateException("Error getting session from the session factory", ex);
        }
    }

}

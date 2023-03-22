package dao.impl;

import connection.HibernateConfig;
import dao.UserDao;
import entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class UserDaoImpl implements UserDao {
    private static final String ERROR_CHECKING_IF_USER_UNIQ = "Error checking if user uniq";
    private static final String ERROR_MESSAGE_ADDING_NEW_USER = "Error adding new user to the database";
    private static final String ERROR_MESSAGE_FETCHING_ALL_USERS = "Error fetching all users from the database";
    private final Logger logger = LogManager.getLogger(UserDaoImpl.class);

    @Override
    public List<User> getAllUsersDataFormDB() {
        List<User> list;
        try {
            logger.debug("Session was opened");
            Session session = HibernateConfig.getSession();
            session.beginTransaction();
            Query query = session.createQuery("FROM User");
            list = query.list();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            logger.error(ERROR_MESSAGE_FETCHING_ALL_USERS, e);
            throw new HibernateException(ERROR_MESSAGE_FETCHING_ALL_USERS, e);
        } catch (RuntimeException e) {
            logger.error(ERROR_MESSAGE_FETCHING_ALL_USERS, e);
            throw new RuntimeException(ERROR_MESSAGE_FETCHING_ALL_USERS, e);
        }

        return list;
    }


    @Override
    public boolean addNewUserInDB(User user) {
        try {
            logger.debug("Session was opened");
            Session session = HibernateConfig.getSession();
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            logger.error(ERROR_MESSAGE_ADDING_NEW_USER, e);
            throw new HibernateException(ERROR_MESSAGE_ADDING_NEW_USER, e);
        } catch (RuntimeException e) {
            logger.error(ERROR_MESSAGE_ADDING_NEW_USER, e);
            throw new RuntimeException(ERROR_MESSAGE_ADDING_NEW_USER, e);
        }
        return true;
    }

    @Override
    public boolean checkIsUserAlreadyExists(String email) {
        User user = null;
        try {
            logger.debug("Session was opened");
            Session session = HibernateConfig.getSession();
            session.beginTransaction();
            user = (User) session.createQuery("FROM User where email= :email")
                    .setParameter("email", email)
                    .uniqueResult();

            session.getTransaction().commit();
        } catch (HibernateException e) {
            logger.error(ERROR_CHECKING_IF_USER_UNIQ, e);
            throw new HibernateException(ERROR_CHECKING_IF_USER_UNIQ, e);
        } catch (RuntimeException e) {
            logger.error(ERROR_CHECKING_IF_USER_UNIQ, e);
            throw new RuntimeException(ERROR_CHECKING_IF_USER_UNIQ, e);
        }

        return user == null;
    }

}

package service.impl;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import entity.User;
import org.hibernate.HibernateException;
import service.EntityValidator;
import service.ServiceException;
import service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class UserServiceImpl implements UserService {

    private static final String ERROR_MESSAGE_FETCH_USERS = "Error fetch all users";

    private static final String ERROR_MESSAGE_ADDING_NEW_USER = "Error adding new user to the database ";
    private static final String ERROR_CHECKING_IF_USER_UNIQ = "Error checking if user uniq";

    private final Logger logger = LogManager.getLogger(UserServiceImpl.class);


    @Override
    public List<User> getAllUsersData() throws ServiceException {
        UserDao userDao = new UserDaoImpl();
        List<User> list = new ArrayList<>();
        try {
            if (logger.isDebugEnabled()) {
                logger.debug("Fetching all accounts.");
            }
            list = userDao.getAllUsersDataFormDB();
        } catch (HibernateException e) {
            logger.error(ERROR_MESSAGE_FETCH_USERS);
            throw new ServiceException(ERROR_MESSAGE_FETCH_USERS, e);

        } catch (RuntimeException e) {
            logger.error(ERROR_MESSAGE_FETCH_USERS);
            throw e;
        }

        Collections.sort(list, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getEmail().compareTo(o2.getEmail());
            }
        });
        return list;
    }

    @Override
    public boolean addNewUser(User user) throws ServiceException {
        UserDao userDao = new UserDaoImpl();
        if (logger.isDebugEnabled()) {
            logger.debug("Creating new user.");
        }
        EntityValidator<User> entityValidator = new UserValidation();
        ValidationResult validationResult = entityValidator.validate(user);

        if (validationResult.hasErrors()) {
            logger.error("Error validating new user data");
            validationResult.getErrors().forEach(x -> logger.error(x.getFieldIdentifier() + " : " + x.getErrorMessage()));
            return false;
        }
        if (!checkOriginalityOfEmail(user.getEmail())) {
            logger.error("Person with email {} already exist", user.getEmail());
            return false;
        }
        try {
            return userDao.addNewUserInDB(user);
        } catch (HibernateException e) {

            logger.error(ERROR_MESSAGE_ADDING_NEW_USER, e);

            throw new ServiceException(ERROR_MESSAGE_ADDING_NEW_USER, e);
        } catch (RuntimeException ex) {

            logger.error(ERROR_MESSAGE_ADDING_NEW_USER, ex);

            throw ex;
        }
    }

    @Override
    public boolean checkOriginalityOfEmail(String email) throws ServiceException {

        UserDao userDao = new UserDaoImpl();

        boolean result = false;
        try {
            if (logger.isDebugEnabled()) {
                logger.debug(".");
            }
            result = userDao.checkIsUserAlreadyExists(email);

        } catch (HibernateException e) {

            logger.error(ERROR_CHECKING_IF_USER_UNIQ, e);

            throw new ServiceException(ERROR_CHECKING_IF_USER_UNIQ, e);
        } catch (RuntimeException ex) {

            logger.error(ERROR_CHECKING_IF_USER_UNIQ, ex);

            throw ex;
        }

        return result;
    }

}

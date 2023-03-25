package service.impl;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import dto.UserDTO;
import dto.mapper.UserDTOMapper;
import entity.User;
import lombok.extern.log4j.Log4j2;
import org.hibernate.HibernateException;
import service.EntityValidator;
import service.ServiceException;
import service.UserService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class UserServiceImpl implements UserService {

    private static final String ERROR_MESSAGE_FETCH_USERS = "Error fetch all users";
    private static final String ERROR_MESSAGE_ADDING_NEW_USER = "Error adding new user to the database ";
    private static final String ERROR_CHECKING_IF_USER_UNIQ = "Error checking if user uniq";
    public static final String PERSON_WITH_EMAIL_ALREADY_EXIST = "Person with email {} already exist";


    @Override

    public List<UserDTO> getAllUsersData() throws ServiceException {
        UserDao userDao = new UserDaoImpl();
        List<UserDTO> list = new ArrayList<>();

        try {
            if (log.isDebugEnabled()) {
                log.debug("Fetching all accounts.");
            }
            UserDTOMapper userDTOMapper = new UserDTOMapper();
            list = userDao.getAllUsersDataFormDB().stream()
                    .map(userDTOMapper).collect(Collectors.toList());
        } catch (HibernateException e) {
            log.error(ERROR_MESSAGE_FETCH_USERS);
            throw new ServiceException(ERROR_MESSAGE_FETCH_USERS, e);

        } catch (RuntimeException e) {
            log.error(ERROR_MESSAGE_FETCH_USERS);
            throw e;
        }

        list.sort((UserDTO user1,UserDTO user2)->user1.getEmail().compareTo(user2.getEmail()));


        return list;
    }

    @Override
    public boolean addNewUser(User user) throws ServiceException {
        UserDao userDao = new UserDaoImpl();
        if (log.isDebugEnabled()) {
            log.debug("Creating new user.");
        }
        EntityValidator<User> entityValidator = new UserValidation();
        ValidationResult validationResult = entityValidator.validate(user);

        if (validationResult.hasErrors()) {
            log.error("Error validating new user data");
            validationResult.getErrors().forEach(x -> log.error(x.getFieldIdentifier() + " : " + x.getErrorMessage()));
            return false;
        }
        if (!checkOriginalityOfEmail(user.getEmail())) {
            log.error(PERSON_WITH_EMAIL_ALREADY_EXIST, user.getEmail());
            return false;
        }
        try {
            return userDao.addNewUserInDB(user);
        } catch (HibernateException e) {

            log.error(ERROR_MESSAGE_ADDING_NEW_USER, e);

            throw new ServiceException(ERROR_MESSAGE_ADDING_NEW_USER, e);
        } catch (RuntimeException ex) {

            log.error(ERROR_MESSAGE_ADDING_NEW_USER, ex);

            throw ex;
        }
    }

    @Override
    public boolean checkOriginalityOfEmail(String email) throws ServiceException {

        UserDao userDao = new UserDaoImpl();

        boolean result = false;
        try {
            if (log.isDebugEnabled()) {
                log.debug(".");
            }
            result = userDao.checkIfUserAlreadyExists(email);

        } catch (HibernateException e) {

            log.error(ERROR_CHECKING_IF_USER_UNIQ, e);

            throw new ServiceException(ERROR_CHECKING_IF_USER_UNIQ, e);
        } catch (RuntimeException ex) {

            log.error(ERROR_CHECKING_IF_USER_UNIQ, ex);

            throw ex;
        }

        return result;
    }

}

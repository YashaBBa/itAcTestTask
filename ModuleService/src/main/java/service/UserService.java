package service;

import entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsersData() throws ServiceException;

    boolean addNewUser(User user) throws ServiceException;

    boolean checkOriginalityOfEmail(String email) throws ServiceException;
}

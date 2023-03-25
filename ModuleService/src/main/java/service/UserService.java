package service;

import dto.UserDTO;
import entity.User;

import java.util.List;

public interface UserService {
    List<UserDTO> getAllUsersData() throws ServiceException;

    boolean addNewUser(User user) throws ServiceException;

    boolean checkOriginalityOfEmail(String email) throws ServiceException;
}

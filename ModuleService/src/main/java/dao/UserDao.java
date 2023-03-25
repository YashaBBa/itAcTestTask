package dao;

import entity.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsersDataFormDB() ;

    boolean addNewUserInDB(User user);

    boolean checkIfUserAlreadyExists(String email);
}

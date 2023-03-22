package controller;

import entity.Role;
import entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.ServiceException;
import service.UserService;
import service.impl.UserServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api")
public class Controller {
    private final Logger logger = LogManager.getLogger(Controller.class);


    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllPersons() throws ServiceException {
        UserService userService = new UserServiceImpl();
        if (logger.isDebugEnabled()) {
            logger.debug("Fetching all users");
        }
        return ResponseEntity.ok(userService.getAllUsersData());

    }

    @PostMapping("/add")
    public ResponseEntity<String> addNewPerson(User user, Role role) throws ServiceException {
        logger.info("Saving new User with email", user.getEmail());
        UserService userService = new UserServiceImpl();
        user.setRole(role);
        boolean resultOfOp = userService.addNewUser(user);
        if (resultOfOp) {
            return ResponseEntity.ok("New Person Was Created!");
        } else {
            return ResponseEntity.status(404).body("Oops! Somthing wrong whith input data!" +
                    "\n Please check validation and try again!");
        }


    }
}

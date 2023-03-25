package com.itAc.testTask.ModuleController.controller;

import dto.UserDTO;
import entity.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.ServiceException;
import service.UserService;
import service.impl.UserServiceImpl;

import java.util.List;


@RestController
@RequestMapping("/api")
@Log4j2

public class Controller {




    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAllPersons() throws ServiceException {
        UserService userService = new UserServiceImpl();
        log.debug("Fetching all users");
        return ResponseEntity.ok(userService.getAllUsersData());

    }

    @PostMapping("/add")
    public ResponseEntity<String> addNewPerson(@RequestBody User user) throws ServiceException {
        log.info("Saving new User with email", user.getEmail());
        UserService userService = new UserServiceImpl();
        boolean resultOfOp = userService.addNewUser(user);
        if (resultOfOp) {
            return ResponseEntity.ok("New Person Was Created!");
        } else {
            return ResponseEntity.status(404).body("Oops! Somthing wrong whith input data!" +
                    "\n Please check validation and try again!");
        }

    }
}

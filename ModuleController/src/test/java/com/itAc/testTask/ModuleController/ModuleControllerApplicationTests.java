package com.itAc.testTask.ModuleController;

import com.itAc.testTask.ModuleController.controller.Controller;
import entity.Role;
import entity.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
class ModuleControllerApplicationTests {

    @Autowired
    private MockMvc mockMvc;


    @Autowired
    private Controller controller;




    @Test
    public void getAllRequestTest() throws Exception {
        this.mockMvc.perform(get("/api/all"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void addCorrectUserTest() throws Exception {
        User user = new User();
        user.setName("test");
        user.setSurname("test");
        user.setPatronymic("test");
        user.setEmail("testt@gamil.com");
        user.setRole(Role.ADMINISTRATOR);

        ResponseEntity<String> response = controller.addNewPerson(user);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("New Person Was Created!", response.getBody());

    }

    @Test
    public void addNotCorrectUserTest() throws Exception {
        User user = new User();
        user.setName("testи");
        user.setSurname("tфest");
        user.setPatronymic("test");
        user.setEmail("test.gamil.com");
        user.setRole(Role.ADMINISTRATOR);




        ResponseEntity<String> response = controller.addNewPerson(user);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Oops! Somthing wrong whith input data!" +
                "\n Please check validation and try again!", response.getBody());

    }


}

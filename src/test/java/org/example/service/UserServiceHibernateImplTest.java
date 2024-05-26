package org.example.service;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.example.bean.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@FieldDefaults(level = AccessLevel.PRIVATE)
class UserServiceHibernateImplTest {
    String testName = "Max";
    String testLastName = "Orlov";
    byte testAge = 19;
    UserService userService = new UserServiceHibernateImpl();


    void testTuning(){
        userService.dropUserTable();
        userService.createUserTable();
        userService.saveUser(testName,testLastName,testAge);
    }

    @Test
    void createUserTable() {
    }

    @Test
    void dropUserTable() {
    }

    @Test
    void saveUser() {
        testTuning();
        User user = userService.getAllUsers().get(0);

        assertEquals(testName,user.getName());
    }

    @Test
    void removeUserById() {
    }

    @Test
    void getAllUsers() {
    }

    @Test
    void clearUserTables() {
    }
}
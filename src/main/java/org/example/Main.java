package org.example;

import org.example.dao.UserDao;
import org.example.dao.UserDaoJDBCImpl;
import org.example.service.UserService;
import org.example.service.UserServiceJDBCImpl;
import org.example.util.Util;

public class Main {
    public static void main(String[] args) {

        Util.getSessionFactory();
        script(new UserServiceJDBCImpl());
    }
    private static void script(UserService userService){
        userService.createUserTable();
        userService.saveUser("Alex", "Orlov", (byte)25);
        userService.saveUser("Max", "Frolov", (byte)19);
        userService.saveUser("Lera", "Qwa", (byte)22);
        userService.saveUser("Tanya", "Orlov", (byte)23);
        userService.saveUser("Oleg", "Orlov", (byte)34);

        System.out.println();
        userService.getAllUsers().forEach(System.out::println);

        userService.removeUserById(2L);

        System.out.println();
        userService.getAllUsers().forEach(System.out::println);

        userService.clearUserTables();


        System.out.println();
        userService.getAllUsers().forEach(System.out::println);
        userService.dropUserTable();
    }
}
package org.example.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Util {
    static final String URL = "jdbc:mysql://localhost:3306/test";
    static final String USER_NAME = "root";
    static final String PASSWORD = "root";
    public static Connection getJDBCConnection(){
        Connection connection;
        try {
            connection = DriverManager.getConnection(URL, USER_NAME,PASSWORD);
        }catch (SQLException e){
            System.out.println("Соединение не установлено");
            throw new RuntimeException(e);
        }
        return connection;
    }
}

package org.example.dao;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.example.bean.User;
import org.example.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.Statement;
import java.util.List;
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDaoHibernateIml implements UserDao {
    SessionFactory sessionFactory = Util.getSessionFactory();
    @Override
    public void createUserTable() {
        String sql = "CREATE TABLE IF NOT EXISTS user" +
                "( id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT, " +
                "name VARCHAR(50), " +
                "lastName VARCHAR(50)," +
                "age TINYINT(3))";

        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            transaction.commit();
        }
    }

    @Override
    public void dropUserTable() {
        String sql = "DROP TABLE IF EXISTS user";

        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery(sql);
            transaction.commit();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

    }

    @Override
    public void removeUserById(long id) {

    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public void clearUserTables() {
        String sql = "TRUNCATE TABLE user";
        try (Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery(sql);
            transaction.commit();
        }
    }
}

package org.example.dao;

import lombok.AccessLevel;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.example.bean.User;
import org.example.util.Util;
import org.hibernate.*;
import org.hibernate.query.Query;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDaoHibernateIml implements UserDao {
    SessionFactory sessionFactory = Util.getSessionFactory();

    @Override
    @SneakyThrows
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
    @SneakyThrows
    public void dropUserTable() {
        String sql = "DROP TABLE IF EXISTS user";

        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            transaction.commit();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            session.persist(new User(name,lastName,age));
            transaction.commit();
        }catch (HibernateException e){
            if(Objects.nonNull(transaction)){
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            transaction.commit();
        }catch (HibernateException e){
            if(Objects.nonNull(transaction)){
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users;
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
            transaction  = session.beginTransaction();
            Query<User> query = session.createQuery("from User", User.class);
            users =  query.list();
          transaction.commit();
        }catch (HibernateException e){
            if(Objects.nonNull(transaction)){
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
        return users;
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

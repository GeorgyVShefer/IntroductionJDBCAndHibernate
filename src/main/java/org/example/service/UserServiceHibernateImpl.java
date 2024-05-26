package org.example.service;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.example.bean.User;
import org.example.dao.UserDao;
import org.example.dao.UserDaoHibernateIml;
import org.example.dao.UserDaoJDBCImpl;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserServiceHibernateImpl implements UserService{
    UserDao userDao = new UserDaoHibernateIml();
    @Override
    public void createUserTable() {
        userDao.createUserTable();
    }

    @Override
    public void dropUserTable() {
        userDao.dropUserTable();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        userDao.saveUser(name, lastName,age);
    }

    @Override
    public void removeUserById(long id) {
        userDao.removeUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public void clearUserTables() {
        userDao.clearUserTables();
    }
}

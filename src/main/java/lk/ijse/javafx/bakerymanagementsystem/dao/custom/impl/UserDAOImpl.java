package lk.ijse.javafx.bakerymanagementsystem.dao.custom.impl;

import lk.ijse.javafx.bakerymanagementsystem.dao.custom.UserDAO;
import lk.ijse.javafx.bakerymanagementsystem.entity.User;

import java.util.List;

public class UserDAOImpl implements UserDAO {
    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public String getNextId() {
        return null;
    }

    @Override
    public boolean save(User user) {
        return false;
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public List<String> getAllIds() {
        return null;
    }

    @Override
    public User findById(String id) {
        return null;
    }
}
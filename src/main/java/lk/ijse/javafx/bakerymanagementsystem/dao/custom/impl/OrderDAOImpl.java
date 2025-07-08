package lk.ijse.javafx.bakerymanagementsystem.dao.custom.impl;

import lk.ijse.javafx.bakerymanagementsystem.dao.custom.OrderDAO;
import lk.ijse.javafx.bakerymanagementsystem.dao.custom.OrderDetailsDAO;
import lk.ijse.javafx.bakerymanagementsystem.entity.Order;

import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public List<Order> getAll() {
        return null;
    }

    @Override
    public String getNextId() {
        return null;
    }

    @Override
    public boolean save(Order order) {
        return false;
    }

    @Override
    public boolean update(Order order) {
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
    public Order findById(String id) {
        return null;
    }
}
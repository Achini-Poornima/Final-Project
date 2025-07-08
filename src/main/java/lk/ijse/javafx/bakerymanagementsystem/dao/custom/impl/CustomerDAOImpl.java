package lk.ijse.javafx.bakerymanagementsystem.dao.custom.impl;

import lk.ijse.javafx.bakerymanagementsystem.dao.custom.CustomerDAO;
import lk.ijse.javafx.bakerymanagementsystem.entity.Customer;

import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public List<Customer> getAll() {
        return null;
    }

    @Override
    public String getNextId() {
        return null;
    }

    @Override
    public boolean save(Customer customer) {
        return false;
    }

    @Override
    public boolean update(Customer customer) {
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
    public Customer findById(String id) {
        return null;
    }
}

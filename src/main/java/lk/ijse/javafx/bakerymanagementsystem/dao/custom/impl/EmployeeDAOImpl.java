package lk.ijse.javafx.bakerymanagementsystem.dao.custom.impl;

import lk.ijse.javafx.bakerymanagementsystem.dao.custom.EmployeeDAO;
import lk.ijse.javafx.bakerymanagementsystem.entity.Employee;

import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public List<Employee> getAll() {
        return null;
    }

    @Override
    public String getNextId() {
        return null;
    }

    @Override
    public boolean save(Employee employee) {
        return false;
    }

    @Override
    public boolean update(Employee employee) {
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
    public Employee findById(String id) {
        return null;
    }
}
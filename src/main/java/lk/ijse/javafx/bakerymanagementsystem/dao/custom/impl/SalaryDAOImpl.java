package lk.ijse.javafx.bakerymanagementsystem.dao.custom.impl;

import lk.ijse.javafx.bakerymanagementsystem.dao.custom.SalaryDAO;
import lk.ijse.javafx.bakerymanagementsystem.entity.Salary;

import java.util.List;

public class SalaryDAOImpl implements SalaryDAO {
    @Override
    public List<Salary> getAll() {
        return null;
    }

    @Override
    public String getNextId() {
        return null;
    }

    @Override
    public boolean save(Salary salary) {
        return false;
    }

    @Override
    public boolean update(Salary salary) {
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
    public Salary findById(String id) {
        return null;
    }
}

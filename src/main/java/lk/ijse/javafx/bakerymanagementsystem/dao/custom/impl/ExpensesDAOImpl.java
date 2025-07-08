package lk.ijse.javafx.bakerymanagementsystem.dao.custom.impl;

import lk.ijse.javafx.bakerymanagementsystem.dao.custom.ExpensesDAO;
import lk.ijse.javafx.bakerymanagementsystem.entity.Expenses;

import java.util.List;

public class ExpensesDAOImpl implements ExpensesDAO {
    @Override
    public List<Expenses> getAll() {
        return null;
    }

    @Override
    public String getNextId() {
        return null;
    }

    @Override
    public boolean save(Expenses expenses) {
        return false;
    }

    @Override
    public boolean update(Expenses expenses) {
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
    public Expenses findById(String id) {
        return null;
    }
}
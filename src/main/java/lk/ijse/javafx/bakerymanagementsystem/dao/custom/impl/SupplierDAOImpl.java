package lk.ijse.javafx.bakerymanagementsystem.dao.custom.impl;

import lk.ijse.javafx.bakerymanagementsystem.dao.custom.SupplierDAO;
import lk.ijse.javafx.bakerymanagementsystem.entity.Supplier;

import java.util.List;

public class SupplierDAOImpl implements SupplierDAO {
    @Override
    public List<Supplier> getAll() {
        return null;
    }

    @Override
    public String getNextId() {
        return null;
    }

    @Override
    public boolean save(Supplier supplier) {
        return false;
    }

    @Override
    public boolean update(Supplier supplier) {
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
    public Supplier findById(String id) {
        return null;
    }
}

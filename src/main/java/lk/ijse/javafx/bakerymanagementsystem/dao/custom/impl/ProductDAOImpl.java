package lk.ijse.javafx.bakerymanagementsystem.dao.custom.impl;

import lk.ijse.javafx.bakerymanagementsystem.dao.custom.ProductDAO;
import lk.ijse.javafx.bakerymanagementsystem.entity.Product;

import java.util.List;

public class ProductDAOImpl implements ProductDAO {
    @Override
    public List<Product> getAll() {
        return null;
    }

    @Override
    public String getNextId() {
        return null;
    }

    @Override
    public boolean save(Product product) {
        return false;
    }

    @Override
    public boolean update(Product product) {
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
    public Product findById(String id) {
        return null;
    }
}
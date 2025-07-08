package lk.ijse.javafx.bakerymanagementsystem.dao.custom.impl;

import lk.ijse.javafx.bakerymanagementsystem.dao.custom.IngredientDAO;
import lk.ijse.javafx.bakerymanagementsystem.dao.custom.InventoryDAO;
import lk.ijse.javafx.bakerymanagementsystem.entity.Inventory;

import java.util.List;

public class InventoryDAOImpl implements InventoryDAO {
    @Override
    public List<Inventory> getAll() {
        return null;
    }

    @Override
    public String getNextId() {
        return null;
    }

    @Override
    public boolean save(Inventory inventory) {
        return false;
    }

    @Override
    public boolean update(Inventory inventory) {
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
    public Inventory findById(String id) {
        return null;
    }
}

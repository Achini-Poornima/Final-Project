package lk.ijse.javafx.bakerymanagementsystem.dao.custom.impl;

import lk.ijse.javafx.bakerymanagementsystem.dao.custom.IngredientDAO;
import lk.ijse.javafx.bakerymanagementsystem.entity.Ingredient;

import java.util.List;

public class IngredientDAOImpl implements IngredientDAO {
    @Override
    public List<Ingredient> getAll() {
        return null;
    }

    @Override
    public String getNextId() {
        return null;
    }

    @Override
    public boolean save(Ingredient ingredient) {
        return false;
    }

    @Override
    public boolean update(Ingredient ingredient) {
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
    public Ingredient findById(String id) {
        return null;
    }
}
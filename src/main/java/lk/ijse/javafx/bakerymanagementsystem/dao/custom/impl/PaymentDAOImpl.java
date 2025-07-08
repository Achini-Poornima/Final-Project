package lk.ijse.javafx.bakerymanagementsystem.dao.custom.impl;

import lk.ijse.javafx.bakerymanagementsystem.dao.custom.PaymentDAO;
import lk.ijse.javafx.bakerymanagementsystem.entity.Payment;

import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {
    @Override
    public List<Payment> getAll() {
        return null;
    }

    @Override
    public String getNextId() {
        return null;
    }

    @Override
    public boolean save(Payment payment) {
        return false;
    }

    @Override
    public boolean update(Payment payment) {
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
    public Payment findById(String id) {
        return null;
    }
}
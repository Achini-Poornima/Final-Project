package lk.ijse.javafx.bakerymanagementsystem.dao.custom.impl;

import lk.ijse.javafx.bakerymanagementsystem.dao.custom.AttendanceDAO;
import lk.ijse.javafx.bakerymanagementsystem.entity.Attendance;

import java.util.List;

public class AttendanceDAOImpl implements AttendanceDAO {

    @Override
    public List<Attendance> getAll() {
        return null;
    }

    @Override
    public String getNextId() {
        return null;
    }

    @Override
    public boolean save(Attendance attendance) {
        return false;
    }

    @Override
    public boolean update(Attendance attendance) {
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
    public Attendance findById(String id) {
        return null;
    }
}

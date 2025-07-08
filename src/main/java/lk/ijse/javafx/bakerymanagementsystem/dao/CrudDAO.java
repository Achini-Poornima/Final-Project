package lk.ijse.javafx.bakerymanagementsystem.dao;

import java.util.List;

public interface CrudDAO<T> extends SuperDAO{
    List<T> getAll();
    String getNextId();
    boolean save(T t);
    boolean update(T t);
    boolean delete(String id);
    List<String> getAllIds();
    T findById(String id);
}

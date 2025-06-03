package lk.ijse.javafx.bakerymanagementsystem.model;

import lk.ijse.javafx.bakerymanagementsystem.Dto.EmployeeDto;
import lk.ijse.javafx.bakerymanagementsystem.Util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeModel {

    public boolean saveEmployee(EmployeeDto employee) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Employee (employee_id, name, contact_no, email, salary, hire_date, role) VALUES (?, ?, ?, ?, ?, ?, ?)";
        return CrudUtil.execute(
                sql,
                employee.getEmployeeId(),
                employee.getName(),
                employee.getContactNo(),
                employee.getEmail(),
                employee.getSalary(),
                employee.getJoinDate(),
                employee.getRole()
        );
    }

    public boolean updateEmployee(EmployeeDto employee) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE Employee SET name=?, contact_no=?, email=?, salary=?, hire_date=?, role=? WHERE employee_id=?";
        return CrudUtil.execute(
                sql,
                employee.getName(),
                employee.getContactNo(),
                employee.getEmail(),
                employee.getSalary(),
                employee.getJoinDate(),
                employee.getRole(),
                employee.getEmployeeId()
        );
    }

    public boolean deleteEmployee(String employee_id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM Employee WHERE employee_id=?";
        return CrudUtil.execute(sql, employee_id);
    }

    public EmployeeDto getEmployee(String employee_id) {
        String sql = "SELECT * FROM Employee WHERE employee_id=?";
        try {
            ResultSet rs = CrudUtil.execute(sql, employee_id);
            if (rs.next()) {
                return new EmployeeDto(
                        rs.getString("employee_id"),
                        rs.getString("name"),
                        rs.getString("contact_no"),
                        rs.getString("email"),
                        rs.getDouble("salary"),
                        rs.getTimestamp("hire_date").toLocalDateTime(),
                        rs.getString("role")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Ideally log this
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public ArrayList<EmployeeDto> getAllEmployees() throws SQLException, ClassNotFoundException {
        ArrayList<EmployeeDto> list = new ArrayList<>();
        String sql = "SELECT * FROM Employee";
            ResultSet rs = CrudUtil.execute(sql);
            while (rs.next()) {
                list.add(new EmployeeDto(
                        rs.getString("employee_id"),
                        rs.getString("name"),
                        rs.getString("contact_no"),
                        rs.getString("email"),
                        rs.getDouble("salary"),
                        rs.getTimestamp("hire_date").toLocalDateTime(),
                        rs.getString("role")
                ));
            }
        return list;
    }

    public ArrayList<String> getAllEmployeeIds() {
        ArrayList<String> list = new ArrayList<>();
        String sql = "SELECT employee_id FROM Employee";
        try {
            ResultSet rs = CrudUtil.execute(sql);
            while (rs.next()) {
                list.add(rs.getString("employee_id"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }
}

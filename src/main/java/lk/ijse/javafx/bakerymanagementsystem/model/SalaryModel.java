package lk.ijse.javafx.bakerymanagementsystem.model;

import lk.ijse.javafx.bakerymanagementsystem.Dto.SalaryDto;
import lk.ijse.javafx.bakerymanagementsystem.Util.CrudUtil;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SalaryModel {
    public ArrayList<SalaryDto> getAllUsers() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Salary");
        ArrayList<SalaryDto> salaryDtos = new ArrayList<>();
        while (resultSet.next()){
            salaryDtos.add(new SalaryDto(
                    resultSet.getString("salary_id"),
                    resultSet.getInt("basic_salary"),
                    resultSet.getInt("bonus"),
                    resultSet.getInt("net_salary"),
                    resultSet.getString("payment_date"),
                    resultSet.getString("employee_id")
            ));
        }
        return salaryDtos;
    }

    public String getNextId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT salary_id FROM Salary ORDER BY salary_id DESC LIMIT 1");
        char tableChar = 'L';
        if (resultSet.next()) {
            String lastId = resultSet.getString(1);
            String lastIdNUmberString = lastId.substring(1);
            int lastIdNumber = Integer.parseInt(lastIdNUmberString);
            int nextIdNumber = lastIdNumber + 1;
            String nextIdString = String.format(tableChar + "%03d", nextIdNumber);
            return nextIdString;
        }
        return tableChar + "001";
    }

    public boolean saveSalary(@NotNull SalaryDto salaryDto) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Salary(salary_id, basic_salary, bonus, net_salary, payment_date, employee_id) VALUES (?,?,?,?,?,?)";
        return CrudUtil.execute(sql, salaryDto.getSalaryId(), salaryDto.getBasicSalary(), salaryDto.getBonus(), salaryDto.getNetSalary(), salaryDto.getPaymentDate(), salaryDto.getEmployeeId());
    }

    public boolean deleteSalary(String salaryId) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM Salary WHERE salary_id = ?";
        return CrudUtil.execute(sql, salaryId);
    }

    public boolean updateUser(@NotNull SalaryDto salaryDto) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE Salary SET basic_salary = ? , bonus = ? , net_salary = ? , payment_date = ? , employee_id = ? WHERE salary_id = ?";
        return CrudUtil.execute(sql, salaryDto.getBasicSalary(), salaryDto.getBonus(), salaryDto.getNetSalary(), salaryDto.getPaymentDate(), salaryDto.getEmployeeId(), salaryDto.getSalaryId());
    }
}

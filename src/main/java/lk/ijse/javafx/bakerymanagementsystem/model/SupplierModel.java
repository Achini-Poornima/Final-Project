package lk.ijse.javafx.bakerymanagementsystem.model;

import lk.ijse.javafx.bakerymanagementsystem.Dto.SupplierDto;
import lk.ijse.javafx.bakerymanagementsystem.Util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierModel {

    public ArrayList<SupplierDto> getAllSuppliers() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Supplier");
        ArrayList<SupplierDto> supplierDtos = new ArrayList<>();
        while (resultSet.next()){
            supplierDtos.add(new SupplierDto(
                    resultSet.getString("supplier_id"),
                    resultSet.getString("name"),
                    resultSet.getString("supplied_ingredient"),
                    resultSet.getString("address"),
                    resultSet.getString("email")
            ));
        }
        return supplierDtos;
    }

    public String getNextId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("select supplier_id from Supplier order by supplier_id desc limit 1");
        char tableChar = 'S';
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

    public boolean saveSupplier(SupplierDto supplierDto) throws SQLException, ClassNotFoundException {
           String sql = "INSERT INTO Supplier(supplier_id,name,supplied_Ingredient,address,email) VALUES (?,?,?,?,?)";
           return CrudUtil.execute(sql,supplierDto.getSupplierId(),supplierDto.getName(),supplierDto.getSuppliedIngredient(),supplierDto.getAddress(),supplierDto.getEmail());
    }

    public boolean updateUser(SupplierDto supplierDto) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE Supplier SET name=?, supplied_Ingredient=?, address=?, email=? WHERE supplier_id=?";
        return CrudUtil.execute(sql, supplierDto.getName(), supplierDto.getSuppliedIngredient(), supplierDto.getAddress(), supplierDto.getEmail(), supplierDto.getSupplierId());
    }

    public boolean deleteUser(String supplierId) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM Supplier WHERE supplier_id=?";
        return CrudUtil.execute(sql,supplierId);
    }

    public ArrayList<String> getAllSupplierIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT supplier_id FROM Supplier");
        ArrayList<String> list = new ArrayList<>();
        while (rst.next()){
            String id = rst.getString(1);
            list.add(id);
        }
        return list;
    }
}

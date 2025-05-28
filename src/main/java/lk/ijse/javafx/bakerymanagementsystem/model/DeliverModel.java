package lk.ijse.javafx.bakerymanagementsystem.model;

import lk.ijse.javafx.bakerymanagementsystem.Dto.DeliverDto;
import lk.ijse.javafx.bakerymanagementsystem.Util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class DeliverModel {

    public static ArrayList<DeliverDto> getAllDelivers() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Deliver");
        ArrayList<DeliverDto> deliverDtos = new ArrayList<>();
        while (resultSet.next()){
            deliverDtos.add(new DeliverDto(
                    resultSet.getString("deliver_id"),
                    resultSet.getString("deliver_address"),
                    resultSet.getInt("deliver_charge"),
                    resultSet.getString("deliver_date"),
                    resultSet.getString("order_id")
            ));
        }
        return deliverDtos;
    }

    public String getNextid() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT deliver_Id FROM Deliver ORDER BY deliver_id desc limit 1");
        char tableChar = 'D';
        if (resultSet.next()){
            String lastId = resultSet.getString(1);
            String lastIdNUmberString = lastId.substring(1);
            int lastIdNumber = Integer.parseInt(lastIdNUmberString);
            int nextIdNumber = lastIdNumber + 1;
            String nextIdString = String.format(tableChar + "%03d", nextIdNumber);
            return nextIdString;
        }
        return tableChar + "001";
    }

    public boolean saveDliver(DeliverDto deliverDto) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Deliver(deliver_id,deliver_address,deliver_charge,deliver_date,order_id) VAlUES (?,?,?,?,?)";
        return CrudUtil.execute(sql,deliverDto.getDeliverId(),deliverDto.getDeliverAddress(),deliverDto.getDeliverCharge(),deliverDto.getDeliverDate(),deliverDto.getOrderId());
    }

    public boolean updateDeliver(DeliverDto deliverDto) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE deliver SET deliver_address = ? , deliver_charge = ? , deliver_date = ? , order_id = ? WHERE deliver_id = ?";
        return CrudUtil.execute(sql, deliverDto.getDeliverAddress(), deliverDto.getDeliverCharge(), deliverDto.getDeliverDate(), deliverDto.getOrderId(), deliverDto.getDeliverId());
    }

    public boolean deleteUser(String deliverId) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM Deliver WHERE deliver_id = ?";
        return CrudUtil.execute(sql, deliverId);
    }
}

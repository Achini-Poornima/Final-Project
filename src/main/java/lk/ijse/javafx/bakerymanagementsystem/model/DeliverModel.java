package lk.ijse.javafx.bakerymanagementsystem.model;

import lk.ijse.javafx.bakerymanagementsystem.Dto.DeliverDto;
import lk.ijse.javafx.bakerymanagementsystem.Util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class DeliverModel {

    public ArrayList<DeliverDto> getAllDeliver() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("select * from Deliver");
        ArrayList<DeliverDto> list = new ArrayList<>();
        while (resultSet.next()){
            DeliverDto deliverDto = new DeliverDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getDate(4),
                    resultSet.getString(5)
            );
            list.add(deliverDto);
        }
        return list;
    }

    public String getNextId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("select deliver_id from Deliver order by deliver_id desc limit 1");
        char tableChar = 'D';
        if (resultSet.next()){
            String lastId = resultSet.getString(1);
            String lastIdNumberString = lastId.substring(1);
            int lastIdNumber = Integer.parseInt(lastIdNumberString);
            int nextIdNumber = lastIdNumber + 1;
            String nextIdString = String.format(tableChar + "%03d", nextIdNumber);
            return nextIdString;
        }
        return tableChar + "001";
    }
}

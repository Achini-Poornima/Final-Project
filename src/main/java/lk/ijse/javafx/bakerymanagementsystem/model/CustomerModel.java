package lk.ijse.javafx.bakerymanagementsystem.model;

import lk.ijse.javafx.bakerymanagementsystem.Dto.CustomerDto;
import lk.ijse.javafx.bakerymanagementsystem.Util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerModel {
    public ArrayList<CustomerDto> viewAllCustomer()throws ClassNotFoundException,SQLException{
        ResultSet rs =CrudUtil.execute("SELECT * FROM Customer");
        ArrayList<CustomerDto> customerDtos = new ArrayList<>();
        while (rs.next()){
            customerDtos.add(new CustomerDto(
                    rs.getString("customer_id"),
                    rs.getString("name"),
                    rs.getString("address"),
                    rs.getString("contact")
            ));
        }

        return customerDtos;
    }
    public String getNextId() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("select customer_id from Customer order by customer_id desc limit 1");

        char tableChr = 'C';

        if (rst.next()){
            String lastId = rst.getString(1);
            String lastIdNumberString = lastId.substring(1);
            int lastIdNumber = Integer.parseInt(lastIdNumberString);
            int nextIdNum = lastIdNumber + 1;
            String nextIdString = String.format(tableChr + "%03d", nextIdNum);
            return nextIdString;

         }
         return "C001";
    }

     public boolean saveCustomer(CustomerDto customerDto) throws SQLException, ClassNotFoundException {
      return CrudUtil.execute("insert into Customer values (?,?,?,?)",
              customerDto.getCustomerId(),
              customerDto.getName(),
              customerDto.getAddress(),
              customerDto.getContact()
      );

    }
}

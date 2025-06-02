package lk.ijse.javafx.bakerymanagementsystem.Dto;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDetailsDto {
    private String employeeId;
    private String address;
    private String workShift;
    private Date dateOfBirth;
}
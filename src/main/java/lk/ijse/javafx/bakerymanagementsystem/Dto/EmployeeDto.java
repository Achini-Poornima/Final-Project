package lk.ijse.javafx.bakerymanagementsystem.Dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class EmployeeDto {
    private String employeeId;
    private String name;
    private String contactNo;
    private String email;
    private double salary;
    private LocalDateTime hireDate;
    private String role;
}
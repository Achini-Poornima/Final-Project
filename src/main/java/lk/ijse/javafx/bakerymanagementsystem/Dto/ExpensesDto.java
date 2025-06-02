package lk.ijse.javafx.bakerymanagementsystem.Dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.*;

@Data
@NoArgsConstructor
@Getter
@ToString
@AllArgsConstructor
@Setter
public class ExpensesDto {
    private String expensesId;
    private String category;
    private BigDecimal amount;
    private LocalDate date;
}
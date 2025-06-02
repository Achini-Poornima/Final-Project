package lk.ijse.javafx.bakerymanagementsystem.Dto;

import lombok.*;

import java.math.BigDecimal;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class PaymentDto {
    private String paymentId;
    private BigDecimal amount;
    private String paymentMethod;
    private Date paymentDate;
    private String orderId;
}
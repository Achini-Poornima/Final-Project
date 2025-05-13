package lk.ijse.javafx.bakerymanagementsystem.Dto.tm;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class DeliverTM {
    private String deliverId;
    private String deliverAddress;
    private int deliverCharge;
    private Date deliverDate;
    private String orderId;
}

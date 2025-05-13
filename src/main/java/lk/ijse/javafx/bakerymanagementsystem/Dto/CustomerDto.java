
package lk.ijse.javafx.bakerymanagementsystem.Dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CustomerDto {
    private String customerId;
    private String name;
    private String address;
    private String contact;
}

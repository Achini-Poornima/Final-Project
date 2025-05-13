package lk.ijse.javafx.bakerymanagementsystem.Dto;

import lombok.*;


@NoArgsConstructor
@Getter
@Setter
@ToString
public class CustomerDto {
    private String customerId;
    private String name;
    private String address;
    private String contact;

    public CustomerDto(String customerId, String name, String address, String contact) {
        this.customerId = customerId;
        this.name = name;
        this.address = address;
        this.contact = contact;
    }

}
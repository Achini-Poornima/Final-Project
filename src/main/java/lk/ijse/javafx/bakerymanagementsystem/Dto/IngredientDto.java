package lk.ijse.javafx.bakerymanagementsystem.Dto;

import lombok.*;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
class IngredientDto {
    private String ingredientId;
    private String name;
    private Date expireDate;
    private Double quantityAvailable;
    private String supplierId;
}
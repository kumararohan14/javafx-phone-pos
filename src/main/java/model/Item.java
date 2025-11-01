package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    private String code;
    private String description;
    private String category;
    private String brand;
    private String supplierId;
    private double costPrice;
    private double unitPrice;
    private int qtyOnHand;
    private int reorderLevel;
    private String location;
    private LocalDate productionDate;
    private String warrantyPeriod;
    private String status;
}
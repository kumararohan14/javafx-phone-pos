package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sales {
    private String saleId;
    private LocalDate saleDate;
    private java.math.BigDecimal totalAmount;
    private int quantity;
    private String month;
    private int year;
//  private LocalDateTime createdOn;
}

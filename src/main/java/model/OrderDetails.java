package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetails {
    private String orderId;
    private String itemId;
    private Integer qty;
    private Double unitPrice;
}

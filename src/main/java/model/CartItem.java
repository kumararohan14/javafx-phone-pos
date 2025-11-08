package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
    private String code;
    private String Desc;
    private Integer qtyOnHand;
    private Double unitPrice;
    private Double total;
}

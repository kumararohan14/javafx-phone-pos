package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Item {

    // Fields (same as table columns)
    private String code;          // VARCHAR(6)
    private String description;   // VARCHAR(50)
    private double unitPrice;     // DECIMAL(8,2)
    private int qtyOnHand;

}

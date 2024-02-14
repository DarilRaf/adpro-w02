package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Product {
    private String productId;
    private String productName;
    private int productQuantity;
    static int staticId;

    public static String setNewId() {
        staticId++;
        return Integer.toString(staticId);
    }
}

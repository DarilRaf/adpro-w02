package id.ac.ui.cs.advprog.eshop.enums;

import lombok.Getter;

@Getter
public enum PaymentMethod {
    VOUCHER("VOUCHER"),
    BANK("BANK");

    private final String value;

    private PaymentMethod(String value) {
        this.value = value;
    }
    
    public static boolean contains(String test) {
        for (PaymentMethod method : PaymentMethod.values()) {
            if (method.name().equals(test)) {
                return true;
            }
        }
        return false;
    }
}

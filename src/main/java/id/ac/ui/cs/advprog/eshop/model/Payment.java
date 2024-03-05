package id.ac.ui.cs.advprog.eshop.model;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Payment {
    String id;
    String method;
    String status;
    Map<String, String> paymentData;

    public Payment(String id, String method, String status, Map<String, String> paymentData) {
        if (!method.equals("VOUCHER") && !method.equals("BANK")) {
            throw new IllegalArgumentException();
        }
        this.id = id;
        this.method = method;
        this.status = status;
        this.paymentData = paymentData;
    }

    public void setStatus(String status) {
        if (!status.equals("SUCCESS") && !status.equals("REJECTED")) {
            throw new IllegalArgumentException("Invalid status value");
        }
        this.status = status;
    }
}

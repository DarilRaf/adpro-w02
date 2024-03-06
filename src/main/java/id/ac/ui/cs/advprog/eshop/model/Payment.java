package id.ac.ui.cs.advprog.eshop.model;

import java.util.Map;
import id.ac.ui.cs.advprog.eshop.enums.*;
import lombok.Getter;

@Getter
public class Payment {
    String id;
    String method;
    String status;
    Map<String, String> paymentData;
    Order order;

    public Payment(String id, String method, String status, Map<String, String> paymentData, Order order) {
        if (!PaymentMethod.contains(method)) {
            throw new IllegalArgumentException();
        }
        this.id = id;
        this.method = method;
        this.status = status;
        this.paymentData = paymentData;
        this.order = order;
    }

    public void setStatus(String status) {
        if (!status.equals("SUCCESS") && !status.equals("REJECTED")) {
            throw new IllegalArgumentException("Invalid status value");
        }
        this.status = status;
        // Update the Order status based on the Payment status if necessary
        if ("SUCCESS".equals(status)) {
            this.order.setStatus(OrderStatus.SUCCESS.getValue());
        } else if ("REJECTED".equals(status)) {
            this.order.setStatus(OrderStatus.FAILED.getValue());
        }
    }
}

package id.ac.ui.cs.advprog.eshop.model;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentTest {
    private Order order;

    @BeforeEach
    void setUp() {
        List<Product> products = new ArrayList<>();
        Product product = new Product(); // Adapt constructor as needed.
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(2);
        products.add(product);
        this.order = new Order("order1", products, System.currentTimeMillis(), "Test User");
    }

    @Test
    void testCreatePayment() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "exampleValue");
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", PaymentMethod.VOUCHER.getValue(), "SUCCESS", paymentData, order);
        assertEquals("13652556-012a-4c07-b546-54eb1396d79b", payment.getId());
        assertEquals("VOUCHER", payment.getMethod());
        assertEquals("SUCCESS", payment.getStatus());
        assertEquals(paymentData, payment.getPaymentData());
    }

    @Test
    void testCreatePaymentVoucherMethod() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABCD5678");
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", PaymentMethod.VOUCHER.getValue(), "SUCCESS", paymentData, order);
        assertEquals("VOUCHER", payment.getMethod());
        assertEquals(paymentData, payment.getPaymentData());
    }

    @Test
    void testCreateVoucherInvalidMethod() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "INVALIDCODE");
        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("13652556-012a-4c07-b546-54eb1396d79b", "INVALID", "SUCCESS", paymentData, order);
        });
    }

    @Test
    void testCreatePaymentBankMethod() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "Bank Example");
        paymentData.put("referenceCode", "REF123456");
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", PaymentMethod.BANK.getValue(), "REJECTED", paymentData, order);
        assertEquals("BANK", payment.getMethod());
        assertEquals(paymentData, payment.getPaymentData());
    }

    @Test
    void testSetStatusToSuccess() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABCD5678");
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", PaymentMethod.VOUCHER.getValue(), "REJECTED", paymentData, order);
        payment.setStatus("SUCCESS");
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testSetStatusToRejected() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABCD5678");
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", PaymentMethod.VOUCHER.getValue(), "REJECTED", paymentData, order);
        payment.setStatus("REJECTED");
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testSetStatusToInvalid() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABCD5678");
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", PaymentMethod.VOUCHER.getValue(), "REJECTED", paymentData, order);
        assertThrows(IllegalArgumentException.class, () -> payment.setStatus("HEHEH"));
    }
}
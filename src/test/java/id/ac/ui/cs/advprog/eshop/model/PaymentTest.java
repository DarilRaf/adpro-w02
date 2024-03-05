package id.ac.ui.cs.advprog.eshop.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentTest {

    @Test
    void testCreatePayment() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "exampleValue");
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "VOUCHER", "SUCCESS", paymentData);
        assertEquals("13652556-012a-4c07-b546-54eb1396d79b", payment.getId());
        assertEquals("VOUCHER", payment.getMethod());
        assertEquals("SUCCESS", payment.getStatus());
        assertEquals(paymentData, payment.getPaymentData());
    }

    @Test
    void testCreatePaymentVoucherMethod() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABCD5678");
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "VOUCHER", "SUCCESS", paymentData);
        assertEquals("VOUCHER", payment.getMethod());
        assertEquals(paymentData, payment.getPaymentData());
    }

    @Test
    void testCreateVoucherInvalidMethod() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "INVALIDCODE");
        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("13652556-012a-4c07-b546-54eb1396d79b", "INVALID", "SUCCESS", paymentData);
        });
    }

    @Test
    void testCreatePaymentBankMethod() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "Bank Example");
        paymentData.put("referenceCode", "REF123456");
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "BANK", "REJECTED", paymentData);
        assertEquals("BANK", payment.getMethod());
        assertEquals(paymentData, payment.getPaymentData());
    }

    @Test
    void testSetStatusToSuccess() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABCD5678");
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "VOUCHER", "REJECTED", paymentData);
        payment.setStatus("SUCCESS");
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testSetStatusToRejected() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABCD5678");
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "VOUCHER", "REJECTED", paymentData);
        payment.setStatus("REJECTED");
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testSetStatusToInvalid() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABCD5678");
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "VOUCHER", "REJECTED", paymentData);
        assertThrows(IllegalArgumentException.class, () -> payment.setStatus("HEHE"));
    }
}
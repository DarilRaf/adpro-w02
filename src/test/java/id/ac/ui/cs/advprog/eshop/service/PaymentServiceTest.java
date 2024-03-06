package id.ac.ui.cs.advprog.eshop.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.OrderRepository;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;

class PaymentServiceTest {
    @InjectMocks
    PaymentServiceImpl paymentService;
    @Mock
    PaymentRepository paymentRepository;
    Payment payment;
    Order order;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        paymentRepository = mock(PaymentRepository.class);
        paymentService = new PaymentServiceImpl(paymentRepository);
        List<Product> products = new ArrayList<>();
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(2);
        products.add(product);

        this.order = new Order("13652556-012a-4c07-b546-54eb1396d79b",
                products, 1708560000L, "Safira Sudrajat");

    }

    @Test
    void testAddPayment() {
        var paymentData = Map.of("key", "value");
        paymentService.addPayment(order, "VOUCHER", paymentData);
        
        verify(paymentRepository).save(any(Payment.class));
    }

    @Test
    void testSetStatus() {
        var payment = new Payment("1", "VOUCHER", "PENDING", new HashMap<>(), order);
        when(paymentRepository.findById("1")).thenReturn(payment);
        
        paymentService.setStatus(payment, "SUCCESS");
        
        verify(paymentRepository).save(payment);
        assertEquals("SUCCESS", payment.getStatus());
        // Assuming you handle Order status update within setStatus, otherwise, you'd need to mock OrderService here.
    }

    @Test
    void testGetPayment() {
        var payment = new Payment("1", "VOUCHER", "SUCCESS", new HashMap<>(), order);
        when(paymentRepository.findById("1")).thenReturn(payment);

        Payment foundPayment = paymentService.getPayment("1");

        assertEquals(payment, foundPayment);
    }

    @Test
    void testVoucherCodePaymentSuccess() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678"); // Valid voucher code
        Payment payment = paymentService.addPayment(order, "VOUCHER", paymentData);
        
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testVoucherCodePaymentRejected() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "INVALIDCODE"); // Invalid voucher code
        Payment payment = paymentService.addPayment(order, "VOUCHER", paymentData);

        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testBankTransferPaymentSuccess() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "Bank XYZ");
        paymentData.put("referenceCode", "REF123456");

        when(paymentRepository.save(any(Payment.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Payment payment = paymentService.addPayment(order, "BANK", paymentData);

        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testBankTransferPaymentRejected() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "");
        paymentData.put("referenceNumber", "REF123456");
        when(paymentRepository.save(any(Payment.class))).thenAnswer(invocation -> invocation.getArgument(0));
        Payment payment = paymentService.addPayment(order, "BANK", paymentData);
        
        assertEquals("REJECTED", payment.getStatus());
    }


}
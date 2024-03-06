package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class PaymentRepositoryTest {
    private PaymentRepository paymentRepository;

    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepository();
    }

    @Test
    void testSavePayment() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("key", "value");
        Payment payment = new Payment("1", "VOUCHER", "SUCCESS", paymentData);
        paymentRepository.save(payment);
        assertNotNull(paymentRepository.findById("1"));
    }

    @Test
    void testFindById() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("key", "value");
        Payment payment = new Payment("1", "VOUCHER", "SUCCESS", paymentData);
        paymentRepository.save(payment);
        Payment foundPayment = paymentRepository.findById("1");
        assertEquals("1", foundPayment.getId());
        assertEquals("VOUCHER", foundPayment.getMethod());
        assertEquals("SUCCESS", foundPayment.getStatus());
    }

    @Test
    void testSaveNullPayment() {
        assertThrows(IllegalArgumentException.class, () -> paymentRepository.save(null));
    }

    @Test
    void testFindByIdNonExistent() {
        assertNull(paymentRepository.findById("zczc"));
    }

}

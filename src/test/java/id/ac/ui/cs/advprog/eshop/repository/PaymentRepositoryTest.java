package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class PaymentRepositoryTest {
    private PaymentRepository paymentRepository;
    private Order order;

   @BeforeEach
   void setUp() {
       paymentRepository = new PaymentRepository();
        List<Product> products = new ArrayList<>();
        Product product = new Product(); // Adapt constructor as needed.
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(2);
        products.add(product);
        this.order = new Order("order1", products, System.currentTimeMillis(), "Test User");
   }

   @Test
   void testSavePayment() {
       Map<String, String> paymentData = new HashMap<>();
       paymentData.put("key", "value");
       Payment payment = new Payment("1", "VOUCHER", "SUCCESS", paymentData, order);
       paymentRepository.save(payment);
       assertNotNull(paymentRepository.findById("1"));
   }

   @Test
   void testFindById() {
       Map<String, String> paymentData = new HashMap<>();
       paymentData.put("key", "value");
       Payment payment = new Payment("1", "VOUCHER", "SUCCESS", paymentData, order);
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

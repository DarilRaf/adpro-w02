package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Payment addPayment(Order order, String method, Map<String, String> paymentData) {
        String id = UUID.randomUUID().toString();
        String status = determinePaymentStatus(method, paymentData);
        Payment payment = new Payment(id, method, status, paymentData, order);
        return paymentRepository.save(payment);
    }

    private String determinePaymentStatus(String method, Map<String, String> paymentData) {
        if ("VOUCHER".equals(method)) {
            String voucherCode = paymentData.get("voucherCode");
            if (voucherCode != null && voucherCode.startsWith("ESHOP") && voucherCode.length() == 16) {
                return "SUCCESS";
            } else {
                return "REJECTED";
            }
        }
        if ("BANK".equals(method)) {
            String bankName = paymentData.get("bankName");
            String referenceCode = paymentData.get("referenceCode");
            if (bankName != null && !bankName.isEmpty() && referenceCode != null && !referenceCode.isEmpty()) {
                return "SUCCESS";
            } else {
                return "REJECTED";
            }
        }
        return "PENDING";
    }

    @Override
    public Payment setStatus(Payment payment, String status) {
        payment.setStatus(status);
        if ("SUCCESS".equals(status)) {
            payment.getOrder().setStatus("SUCCESS");
        } else if ("REJECTED".equals(status)) {
            payment.getOrder().setStatus("FAILED");
        }
        return paymentRepository.save(payment);
    }

    @Override
    public Payment getPayment(String paymentId) {
        return paymentRepository.findById(paymentId);
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }
}

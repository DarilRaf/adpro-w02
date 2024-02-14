package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProduct() {
        Product product = new Product();
        when(productRepository.create(product)).thenReturn(product);

        Product createdProduct = productService.create(product);

        assertNotNull(createdProduct);
        assertEquals(product, createdProduct);
        assertNotNull(createdProduct.getProductId());
    }

    @Test
    void testFindAllProducts() {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product());
        productList.add(new Product());
        when(productRepository.findAll()).thenReturn(productList.iterator());

        List<Product> allProducts = productService.findAll();

        assertNotNull(allProducts);
        assertEquals(productList.size(), allProducts.size());
    }

    @Test
    void testDeleteProductById() {
        String productId = "1";
        when(productRepository.deleteById(productId)).thenReturn(new Product());

        Boolean result = productService.deleteById(productId);

        assertTrue(result);
    }

    @Test
    void testGetProductById() {
        String productId = "1";
        Product product = new Product();
        when(productRepository.getProduct(productId)).thenReturn(product);

        Product retrievedProduct = productService.getProduct(productId);

        assertNotNull(retrievedProduct);
        assertEquals(product, retrievedProduct);
    }
}

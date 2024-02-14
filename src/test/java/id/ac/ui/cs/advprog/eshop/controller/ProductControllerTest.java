package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @Mock
    private Model model;

    @Test
    public void testCreateProductPage() {
        String viewName = productController.createProductPage(model);
        assertEquals("CreateProduct", viewName);
        verify(model).addAttribute(eq("product"), any(Product.class));
    }

    @Test
    public void testCreateProductPost() {
        Product product = new Product();
        String viewName = productController.createProductPost(product, model);
        assertEquals("redirect:list", viewName);
        verify(productService).create(product);
    }

    @Test
    public void testProductListPage() {
        List<Product> productList = Arrays.asList(new Product(), new Product());
        when(productService.findAll()).thenReturn(productList);
        String viewName = productController.productListPage(model);
        assertEquals("ProductList", viewName);
        verify(model).addAttribute("products", productList);
    }

    @Test
    public void testDeleteProduct() {
        String productId = "1";
        String viewName = productController.deleteProduct(productId, model);
        assertEquals("redirect:/product/list", viewName);
        verify(productService).deleteById(productId);
    }

    @Test
    public void testShowEditForm() {
        String productId = "1";
        Product product = new Product();
        when(productService.getProduct(productId)).thenReturn(product);
        String viewName = productController.showEditForm(productId, model);
        assertEquals("EditProduct", viewName);
        verify(model).addAttribute("product", product);
    }


    @Test
    public void testUpdateProduct() {
        String productId = "1";
        Product updatedProduct = new Product();
        updatedProduct.setProductName("Updated Product Name");
        updatedProduct.setProductQuantity(10);

        // Mock the behavior of getProduct to return an updated product
        when(productService.getProduct(productId)).thenReturn(updatedProduct);

        String viewName = productController.updateProduct(productId, updatedProduct);

        verify(productService).getProduct(productId);

        assertEquals("redirect:/product/list", viewName);
    }

}

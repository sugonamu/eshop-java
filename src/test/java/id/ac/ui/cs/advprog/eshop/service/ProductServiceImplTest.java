package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceImplTest {

    @Autowired
    private ProductServiceImpl productService;

    @MockBean
    private ProductRepository productRepository;

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setProductId(UUID.randomUUID().toString());
        product.setProductName("Test Product");
        product.setProductQuantity(10);
    }

    @Test
    void testCreateProduct() {
        Mockito.when(productRepository.create(product)).thenReturn(product);
        Product createdProduct = productService.create(product);
        assertEquals(product, createdProduct);
        Mockito.verify(productRepository).create(product);
    }

    @Test
    void testFindAllProducts() {
        Iterator<Product> productIterator = Collections.singletonList(product).iterator();
        Mockito.when(productRepository.findAll()).thenReturn(productIterator);
        List<Product> products = productService.findAll();
        assertEquals(1, products.size());
        assertEquals(product, products.get(0));
        Mockito.verify(productRepository).findAll();
    }

    @Test
    void testUpdateProduct() {
        Mockito.when(productRepository.update(product)).thenReturn(product);
        Product updatedProduct = productService.update(product);
        assertEquals(product, updatedProduct);
        Mockito.verify(productRepository).update(product);
    }

    @Test
    void testDeleteProduct() {
        String productId = product.getProductId();
        productService.delete(productId);
        Mockito.verify(productRepository).delete(productId);
    }
}
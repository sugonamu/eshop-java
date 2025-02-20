package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ProductControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    // ✅ FIXED: Test for create product page
    @Test
    void testCreateProductPage() throws Exception {
        mockMvc.perform(get("/product/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("createProduct"))
                .andExpect(model().attributeExists("product")); // Check if 'product' exists in model
    }

    // ✅ FIXED: Test for product list page
    @Test
    void testProductListPage() throws Exception {
        Product product1 = new Product();
        product1.setProductId("1");
        product1.setProductName("Product 1");
        product1.setProductQuantity(5);

        Product product2 = new Product();
        product2.setProductId("2");
        product2.setProductName("Product 2");
        product2.setProductQuantity(10);

        List<Product> products = Arrays.asList(product1, product2);

        when(productService.findAll()).thenReturn(products);

        mockMvc.perform(get("/product/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("productList"))
                .andExpect(model().attributeExists("products")) // Ensure 'products' is in model
                .andExpect(model().attribute("products", products));

        verify(productService, times(1)).findAll();
    }

    // ✅ FIXED: Test for edit product page
    @Test
    void testEditProductPage() throws Exception {
        Product product = new Product();
        product.setProductId("1");
        product.setProductName("Test Product");
        product.setProductQuantity(10);

        when(productService.getById("1")).thenReturn(product);

        mockMvc.perform(get("/product/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("editProduct"))
                .andExpect(model().attributeExists("product")) // Ensure 'product' is in model
                .andExpect(model().attribute("product", product));

        verify(productService, times(1)).getById("1");
    }
}

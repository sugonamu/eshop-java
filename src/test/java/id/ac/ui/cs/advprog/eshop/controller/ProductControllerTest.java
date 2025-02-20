package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Collections;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;
    
    private ProductService productService;

    @Test
    public void testCreateProductPage() throws Exception {
        mockMvc.perform(get("/product/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("CreateProduct"))
                .andExpect(model().attributeExists("product"));
    }

    @Test
    public void testCreateProductPost() throws Exception {
        Product product = new Product();
        product.setProductId(UUID.randomUUID().toString());

        mockMvc.perform(post("/product/create")
                        .flashAttr("product", product))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("list"));

        Mockito.verify(productService).create(product);
    }

    @Test
    public void testProductListPage() throws Exception {
        Mockito.when(productService.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/product/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("ProductList"))
                .andExpect(model().attributeExists("products"));
    }

    @Test
    public void testEditProductPage() throws Exception {
        Product product = new Product();
        product.setProductId("1");
        Mockito.when(productService.findAll()).thenReturn(Collections.singletonList(product));

        mockMvc.perform(get("/product/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("EditProduct"))
                .andExpect(model().attributeExists("product"));
    }

    @Test
    public void testEditProductPost() throws Exception {
        Product product = new Product();
        product.setProductId("1");

        mockMvc.perform(post("/product/edit")
                        .flashAttr("product", product))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/list"));

        Mockito.verify(productService).update(product);
    }

    @Test
    public void testDeleteProduct() throws Exception {
        String productId = "1";

        mockMvc.perform(get("/product/delete/" + productId))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/list"));

        Mockito.verify(productService).delete(productId);
    }
    @Test
    public void testEditProductPage_ProductFound() throws Exception {
        Product product = new Product();
        product.setProductId("1");

        // Mocking service to return a list with the matching product
        Mockito.when(productService.findAll()).thenReturn(Collections.singletonList(product));

        mockMvc.perform(get("/product/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("EditProduct"))
                .andExpect(model().attributeExists("product"))
                .andExpect(model().attribute("product", product)); // Ensure the correct product is set
    }

    @Test
    public void testEditProductPage_ProductNotFoundInNonEmptyList() throws Exception {
        Product product = new Product();
        product.setProductId("2"); // Different ID, so it won't match

        // Mocking service to return a list with a product that DOES NOT match
        Mockito.when(productService.findAll()).thenReturn(Collections.singletonList(product));

        mockMvc.perform(get("/product/edit/1"))
                .andExpect(status().is3xxRedirection())  // Expect a redirect
                .andExpect(redirectedUrl("/product/list")); // Redirects because no matching product
    }

    @Test
    public void testEditProductPage_ProductListIsEmpty() throws Exception {
        // Mocking service to return an empty list
        Mockito.when(productService.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/product/edit/1"))
                .andExpect(status().is3xxRedirection())  // Expect a redirect
                .andExpect(redirectedUrl("/product/list")); // Redirects because no products exist
    }

}
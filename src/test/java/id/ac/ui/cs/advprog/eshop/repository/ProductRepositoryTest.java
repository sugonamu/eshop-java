package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }
    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());

    }

    //Positive scenarios
    @Test
    void testDeleteExistingProduct() {
        //adding the product
        Product product=new Product();
        product.setProductId("product-1");
        product.setProductQuantity(5);
        product.setProductName("TestProduct");
        productRepository.create(product);
        //deleting the existing product
        productRepository.delete("product-1");

        //make sure that the product is deleted
        assertNull(productRepository.findById("product-1"));
        Iterator<Product> iterator = productRepository.findAll();
        assertFalse(iterator.hasNext());
    }@Test
    void testUpdateExistingProduct() {
        // adding a product
        Product product = new Product();
        product.setProductId("product-4");
        product.setProductQuantity(6);
        product.setProductName("OriginalProduct");
        productRepository.create(product);
        //editing the product name
        product.setProductName("UpdatedProduct");
        product.setProductQuantity(6);
        productRepository.update(product);

        //check if the update worked
        Product result = productRepository.findById("product-4");
        assertNotNull(result);
        assertEquals("UpdatedProduct", result.getProductName());
        assertEquals(6, result.getProductQuantity());
        // check if the repository was also updated
        Product newProduct = productRepository.findById("product-4");
        assertNotNull(newProduct);
        assertEquals("UpdatedProduct", newProduct.getProductName());
        assertEquals(6, newProduct.getProductQuantity());
    }



    //Negative Scenario
    @Test
    void testDeleteNonExistentProduct(){
        //adding a product
        Product product=new Product();
        product.setProductId("product-1");
        product.setProductQuantity(10);
        product.setProductName("TestProduct");
        productRepository.create(product);
        //deleting a product with an nonexistent id
        productRepository.delete("product-333");
        //check if the product that was added is still in the repository
        Product retrievedProduct =productRepository.findById("product-1");
        assertNotNull(retrievedProduct);
        assertEquals("TestProduct", retrievedProduct.getProductName());
        assertEquals(10, retrievedProduct.getProductQuantity());
    }
    @Test
    void testUpdateNonExistentProduct(){

        // Prepare an updated product with an ID that is not in the repository.
        Product updatedProduct = new Product();
        updatedProduct.setProductId("product33");
        updatedProduct.setProductName("Nothing");
        updatedProduct.setProductQuantity(4);

        // Attempt to update and expect a null result.
        Product result = productRepository.update(updatedProduct);
        assertNull(result);

    }

}
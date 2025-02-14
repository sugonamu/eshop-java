package id.ac.ui.cs.advprog.eshop.functional;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import io.github.bonigarcia.seljup.SeleniumJupiter;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
class CreateProductFunctionalTest {
    @LocalServerPort
    private int serverPort;
    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;
    private String baseUrl;

    @BeforeEach
    void setupTest() {
        baseUrl = String.format("%s:%d", testBaseUrl, serverPort);
    }
    @Test
    void createProductTest(ChromeDriver driver) {
        // redirect to create product page
        driver.get(baseUrl + "/product/create");
        // fill in the fields
        WebElement nameField = driver.findElement(By.name("productName"));
        nameField.sendKeys("testproduct");
        WebElement quantityField = driver.findElement(By.name("productQuantity"));
        quantityField.sendKeys("3");

        // click the submit button to create the product and will redirect to the list
        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));
        submitButton.click();
        WebElement newProduct = driver.findElement(By.xpath("//*[contains(text(), 'testproduct')]"));
        assertTrue(newProduct.isDisplayed(), "The new product should be visible in the product list");
    }
}
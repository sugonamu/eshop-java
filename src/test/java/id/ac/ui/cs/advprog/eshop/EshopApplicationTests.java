package id.ac.ui.cs.advprog.eshop;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.mockito.Mockito.*;
import org.springframework.boot.SpringApplication;

@SpringBootTest
class EshopApplicationTests {

    @Test
    void contextLoads() {
        // Ensures that the application context loads successfully
    }

    @Test
    void mainMethodTest() {
        // Mock the SpringApplication class to verify the main method runs
        try (var mockedSpringApplication = mockStatic(SpringApplication.class)) {
            EshopApplication.main(new String[]{});
            mockedSpringApplication.verify(() -> SpringApplication.run(EshopApplication.class, new String[]{}));
        }
    }
}
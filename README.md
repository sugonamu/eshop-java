# **Project Reflections and SOLID Principles**

## **Assignment 1 - Reflection 1**
I implemented the features for editing and deleting products. Additionally, I added a confirmation button for product deletion to prevent accidental misclicks.

### **Challenges Faced**
One of the main obstacles I encountered was forgetting to add Thymeleaf as a dependency, which resulted in multiple 404 errors. Initially, I struggled to find the issue since the code itself appeared correct. However, upon realizing that the templates were not being rendered, I identified the missing dependency as the root cause.

### **Future Improvements**
- Implement input validation to prevent invalid data submission.
- Add CSRF protection for enhanced security.

---

## **Assignment 1 - Reflection 2**
Writing unit tests is essential for ensuring code reliability, but achieving 100% code coverage does not necessarily mean the code is free of bugs or logical errors. Code coverage primarily helps identify untested parts of the codebase, but it does not guarantee correctness in all scenarios.

### **Challenges Faced**
- Duplicating setup procedures and instance variables while writing functional tests led to increased redundancy and maintenance complexity.
- Encountered difficulties while merging due to unfamiliarity with the VIM text editor, which led to confusion and an unintended commit message.

### **Solutions and Best Practices**
- Refactor shared setup logic into a base test class or use JUnit’s `@BeforeEach` method to improve reusability and maintainability.
- Gain familiarity with VIM commands to avoid confusion when writing commit messages during merge operations.

---

## **Assignment 2 - Reflection 3**
### **Improvements Made**
- Removed field injections (`@Autowired`) and used constructor injection instead to prevent objects from being created in an invalid state.
- This change made testing easier and ensured compatibility with `final` fields.

### **Continuous Integration and Deployment**
- The project successfully met the requirements for **Continuous Integration (CI)** and **Continuous Deployment (CD)**.
- **CI.yml** ensures automated pipelines run tests, build the application, and perform static code analysis using **SonarCloud** and **SonarQube**, improving code quality.
- **OSSF Scorecard** was implemented to follow best security practices.
- Successfully deployed the application onto **Koyeb**.

---

## **Assignment 3 - Reflection 4**
### **Application of SOLID Principles**
1. **Single Responsibility Principle (SRP)**
    - Separated controllers into `CarController.java` and `ProductController.java` to ensure each class has a single responsibility.

2. **Open/Closed Principle (OCP)**
    - Designed the `CarServiceImpl.java` class to be open for extension but closed for modification, allowing additional services to be added without altering its core functionality.

3. **Liskov Substitution Principle (LSP)**
    - Ensured subclass implementations do not affect the correctness of the application, maintaining expected behavior across different class hierarchies.

4. **Interface Segregation Principle (ISP)**
    - Designed specific interfaces for the `Car` module, preventing classes from implementing unnecessary methods.

5. **Dependency Inversion Principle (DIP)**
    - Modified `CarController.java` to depend on abstractions instead of concrete implementations, reducing coupling and increasing testability.

---

# **Advantages of SOLID Principles and Disadvantages of Not Applying SOLID Principles**

## **Advantages of Applying SOLID Principles**

### **1. Improved Code Maintainability**
- Each class has a clear responsibility (SRP), ensuring modifications do not introduce unintended side effects in unrelated parts of the code.
- Example: If the logging mechanism needs to be updated, only the `LoggerService` class requires modification instead of multiple dependent classes.

### **2. Easier to Extend and Modify (Open/Closed Principle - OCP)**
- New functionalities can be added without modifying existing code.
- Example: Adding a new payment method (e.g., `CryptoPayment`) does not require modifying the existing `PaymentProcessor` class.

### **3. Increased Code Reusability**
- The **Liskov Substitution Principle (LSP)** ensures subclasses extend functionality without altering expected behavior.
- Code that depends on abstractions rather than concrete implementations can be reused in multiple areas of the system.

### **4. More Flexible and Decoupled Code**
- The **Interface Segregation Principle (ISP)** prevents classes from implementing unnecessary methods by creating smaller, specific interfaces.
- The **Dependency Inversion Principle (DIP)** ensures high-level modules depend on abstractions rather than low-level implementations, making it easier to swap dependencies.

### **5. Easier Unit Testing and Mocking**
- Since dependencies are injected rather than hardcoded (DIP), unit testing becomes easier.
- Example: Instead of using a real database, a mock implementation (`MockDatabaseService`) can be used for testing.

---

## **Disadvantages of Not Applying SOLID Principles with Examples**

### **1. Tightly Coupled Code (SRP Violation)**
- If multiple responsibilities are combined in a single class, modifying one feature may introduce unintended issues in other parts of the system.
- Example: In `ProductController.java`, product-related logic is tightly coupled within the controller rather than being separated into service layers.
  ```java
  @Autowired
  private ProductService service;
  
  @PostMapping("/create")
  public String createProductPost(@ModelAttribute Product product, Model model) {
      product.setProductId(UUID.randomUUID().toString());
      service.create(product);
      return "redirect:list";
  }
  ```
   - **Issue:** Business logic (setting product ID) should be in `ProductService` rather than in the controller.

### **2. Difficult to Extend (OCP Violation)**
- Without OCP, adding new functionalities requires modifying existing code, leading to a higher risk of breaking existing functionality.
- Example: `CarRepository` directly updates car objects rather than allowing extension through an abstracted service.
  ```java
  public Car update(String id, Car updatedCar) {
      for (int i = 0 ; i < carData.size(); i++) {
          Car car = carData.get(i);
          if (car.getCarId().equals(id)) {
              car.setCarName(updatedCar.getCarName());
              car.setCarColor(updatedCar.getCarColor());
              car.setCarQuantity(updatedCar.getCarQuantity());
              return car;
          }
      }
      return null;
  }
  ```
   - **Issue:** Instead of modifying this method when new fields are added, an interface or extension strategy should be used.

### **3. Unreliable Subclass Behavior (LSP Violation)**
- If a subclass modifies behavior unexpectedly, it can cause unintended side effects.
- Example: `CarController` extends `ProductController`, even though a car is a specific type of product, causing potential inconsistencies.
  ```java
  class CarController extends ProductController {
      @Autowired
      private CarServiceImpl carService;
  }
  ```
   - **Issue:** This inheritance structure implies that `CarController` should behave like `ProductController`, which may not be true in all cases.

### **4. Classes Implement Unnecessary Methods (ISP Violation)**
- Without ISP, classes are forced to implement methods they do not need.
- Example: `CarService` interface has methods that are irrelevant to all implementations.
  ```java
  public interface CarService {
      public Car create(Car car);
      public List<Car> findAll();
      Car findById(String carId);
      public void update(String carId, Car car);
      public void deleteCarById(String carId);
  }
  ```
   - **Issue:** If there were electric and fuel-based cars, methods like `chargeBattery()` and `refuel()` should belong to separate interfaces rather than a shared `CarService`.

### **5. Hard to Test and Modify Dependencies (DIP Violation)**
- Without DIP, high-level modules depend on low-level implementations, making testing harder.
- Example: `CarController` depends directly on `CarServiceImpl`, rather than an abstraction.
  ```java
  @Autowired
  private CarServiceImpl carService;
  ```
   - **Issue:** Instead of directly depending on `CarServiceImpl`, it should depend on `CarService` interface to allow flexibility in testing and future modifications.

---

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

# **Advantages and Disadvantages of SOLID Principles**

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

## **Disadvantages of Applying SOLID Principles**

### **1. Increased Code Complexity**
- Applying SOLID principles often requires breaking down code into multiple classes, interfaces, and layers, increasing complexity.
- Example: Instead of a single `UserService`, additional components such as `UserRepository`, `UserValidator`, `UserManager`, and `UserController` may be needed.

### **2. Over-Engineering in Small Projects**
- Strict adherence to SOLID can introduce unnecessary abstractions in small projects.
- Example: A simple CRUD application may not require complex dependency injection or multiple service layers.

### **3. More Boilerplate Code**
- Implementing interfaces, dependency injection, and multiple classes results in increased lines of code.
- Example: Instead of calling a function directly, multiple layers of classes must be instantiated.

### **4. Potential Performance Overhead**
- Using dependency injection frameworks and additional abstraction layers may introduce runtime overhead.
- Example: Instead of calling a method directly, the request may go through multiple layers (`Controller → Service → Repository`).

### **5. Requires More Knowledge and Experience**
- Understanding and properly applying SOLID principles requires a strong grasp of object-oriented design.
- Example: Misapplying the **Liskov Substitution Principle (LSP)** can lead to unexpected behaviors rather than preventing them.

---


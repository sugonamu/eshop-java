Assignment 1 Reflection 1:

I implemented the features for edit and delete product. I've also added a confirm button for production deletion so users would not accidentally misclick the delete button.
Some obstacles I faced were forgetting to add Thymeleaf as a dependency which lead to multiple 404 errors. I eventually discovered this issue when I realised the templates could not be displayed however, the codes were perfectly fine.
To improve the code further, features such as input validation to prevent invalid data submission and adding a CSRF protection for a better security would be a great addition for the code.

Assignment 1 Reflection 2:


Writing unit tests is essential for ensuring code reliability, but having 100% code coverage does not necessarily mean the code is free of bugs or logical errors. Code coverage helps identify untested parts of the codebase, but it does not guarantee correctness in all scenarios. When adding new functional test suites, duplicating setup procedures and instance variables can reduce code quality by increasing redundancy and maintenance complexity.
A better approach is to refactor shared setup logic into a base test class or use JUnit’s @BeforeEach method to improve reusability and maintainability.
There were some new things for me when merging, e.g VIM text editor where our messages goes, this made me confused and I thought there was issues thus leading to a bad comment when pushing.

Assignment 2 Reflection 3:

I improved the code quality by removing field injections (@Autowired) and used a constructor injection instead to prevent creation of objects in an invalid state
,making testing more difficult. It is also not compatible with final fields


Based on the implementation details provided, my project has successfully met the definition of Continuous Integration (CI) and Continuous Deployment (CD). CI.yml shows that automated pipelines are in place to run tests, build the application, and perform static code analysis with SonarCloud and SonarQube, ensuring code quality is continuously validated with each commit. 
Using OSSF Scorecard also shows a good practice of security. On the deployment side, I managed to deploy the app onto Koyeb.

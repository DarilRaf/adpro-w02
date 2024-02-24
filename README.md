1. List the code quality issue(s) that you fixed during the exercise and explain your strategy on fixing them.

- Removed some un-used variable

The SonarCloud reminded me on some code cleaning issues which are un-used variable that are still on my code.
I fixed it by simple removing it from my codebase

- Removed a commented out codes

Same as before, it also reminded me on some commented out codes that are in some files. I proceed to remove it from the codebase to make it look cleaner.

2. Look at your CI/CD workflows (GitHub)/pipelines (GitLab). Do you think the current implementation has met the definition of Continuous Integration and Continuous Deployment? Explain the reasons (minimum 3 sentences)!

I've added 3 workflows in total: CI, SonarCloud, and Koyeb.
From those 3 workflows, I think it has covered most of the CI/CD principle, such as the continuous
integration that I had on ci.yml makes sure that the code has run through some re-building so it makes sure nothing
is getting out of the way. On the other hand Koyeb workflow is used so it could automatically deploy my application
to the koyeb service, so I won't have to do it manually.

koyeb web link: https://implicit-merissa-darilraf.koyeb.app/

=============== Module 3 ==================

- Single Responsibility Principle (SRP)
  So far, the code that I implemented is already align with the single responsibility interface, since each class has already focusing on each of it's responsibility. For example, for CarServiceImpl.java, the methods within it is already focusing on the car's method like create, update, and delete.

- Open/Closed Principle (OCP)
  Considering the Open/Closed Principle (OCP), which suggests that software entities should be open for extension but closed for modification, the code that I've implemented from tutorial seems like it has already implementing this principle, for example the CarService interface which allows for new functionalities to be added with minimal changes to existing code, by implementing new services or extending existing ones.

- Liskov Substitution Principle (LSP)
  Since the codes that I implemented haven't used any subclass implementation other than interfaces. I think my code already naturally align with the Liskov Substitution Principle so it doesn't need any adjusment according to it

- Interface Segregation Principle (ISP)
  The Interface Segregation Principle suggests that no client should be forced to depend on methods it does not use. On the code, specifically with interfaces like CarService, seems to follow this principle well by defining a clear and focused contract for the services related to Car entities, but still could have a little bit of improvement which I will discuss about it more on the second point below.

- The Dependency Inversion Principle (DIP)
  The Dependency Inversion Principle is about high-level modules not depending on low-level modules, but both should depend on abstractions. My code applies DIP by using interfaces like CarService for defining business logic operations and injecting these dependencies into classes (i.e. controllers) through Spring's @Autowired annotation. So it already aligns with the Dependency Inversion Principle

1. Principles That I Applied
   One of the principle that I applied is the Interface Segregation Principle onto the Service package. Since the original method within the CarService is diversed between create, edit, delete, find all, and find by id. Since I find it that it's too much responsibility to handle i decided to divide it into another interface and class becoming CarFinder and CarFinderImpl for the implementation. This implementation seems like it also driven by the Single Responsibility Principle, where each class has a single sole purpose within it's class.

2. Advantages of applying SOLID principles
   Applying SOLID principles to my project enhances maintainability, scalability, and flexibility. It encourages cleaner, more modular code, making future changes and additions easier. Specifically, it leads to:
   S: Simplified debugging and updates due to single responsibilities.
   O: Easier addition of new features without modifying existing code.
   L: Improved code robustness by ensuring derived classes extend base classes without altering their behavior.
   I: Reduced complexity and improved code readability by segregating interfaces.
   D: Enhanced code flexibility and reduced dependencies through inversion of control.
   For example, by implementing SOLID principles in my project, such as separating CarService into CarFinder and another service interface for modification operations, I've increased the modularity of the code (SRP, ISP). This allows for easier expansion or modification without impacting existing functionalities.

3. Disadvantages of not applying SOLID principles
   Not applying SOLID principles can lead to code that is rigid, fragile, immobile, and viscous. For example, without the Single Responsibility Principle, a class might handle multiple tasks, making it hard to modify one functionality without affecting others. Ignoring the Open/Closed Principle might result in frequent modifications to existing code for any new feature, increasing the risk of bugs. Overlooking Liskov Substitution could lead to runtime errors if subclasses don't properly fit into the places their base classes are expected to. Neglecting Interface Segregation and Dependency Inversion can cause unnecessary dependencies, making the system harder to understand, extend, or refactor.

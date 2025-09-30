# Design Patterns

- **proven solutions to common software design problems**. 
- Reusable approaches to structuring code, making it **flexible, maintainable, and scalable**.

---

## 1. Creational Patterns
### Description
- Focus on **object creation mechanisms**.
- Provide ways to create objects without exposing creation logic.
- Help in reusing and controlling the object creation process.

### The Problem
- Direct use of constructors makes code **rigid and hard to maintain**.
- Multiple combinations of parameters lead to **telescoping constructors**.
- Hard to manage object creation for complex or large systems.

### The Solution
- Abstract the instantiation process.
- Allow flexibility in deciding **what to create, how to create, and when to create**.

### Types
- **Factory Method**
- **Abstract Factory**
- **Builder**
- **Prototype**
- **Singleton**

### Use Cases
- Database connections (Singleton)
- Car manufacturing system (Builder)
- Food delivery app dish creation (Factory)
- Cloning objects in games (Prototype)

### Where Used
- Frameworks and libraries for object management
- Dependency Injection containers (Spring, Angular)
- UI component creation

---

## 2. Structural Patterns
### Description
- Focus on **object composition**.
- Define how classes and objects combine to form larger structures.
- Simplify relationships between entities.

### The Problem
- Systems grow complex when many classes interact.
- Tight coupling makes it hard to change or extend.
- Incompatible interfaces cannot work together directly.

### The Solution
- Compose classes and objects to form **flexible structures**.
- Allow incompatible interfaces to interact via adapters or decorators.

### Types
- **Adapter**
- **Decorator**
- **Composite**
- **Proxy**
- **Bridge**
- **Facade**
- **Flyweight**

### Use Cases
- Media player supporting multiple formats (Adapter)
- Text editor with dynamic formatting (Decorator)
- File system representation (Composite)
- Simplified library APIs (Facade)

### Where Used
- GUI frameworks
- Middleware systems
- Cross-platform tools
- Wrapping third-party libraries

---

## 3. Behavioral Patterns
### Description
- Focus on **object interaction and responsibility**.
- Define **how objects communicate** and delegate tasks.
- Improve flexibility in communication between objects.

### The Problem
- Complex object interactions lead to tight coupling.
- Hard to change the flow of communication.
- Business logic spread across multiple classes.

### The Solution
- Encapsulate behavior and communication logic.
- Make the system more **flexible, reusable, and extensible**.

### Types
- **Observer**
- **Strategy**
- **Command**
- **Iterator**
- **Interpreter**
- **Visitor**
- **Mediator**
- **Memento**
- **State**
- **Template Method**
- **Chain of Responsibility**

### Use Cases
- News app with subscriber updates (Observer)
- Payment system with multiple methods (Strategy)
- Undo/Redo in text editors (Command, Memento)
- UI navigation or workflow handling (State, Chain of Responsibility)

### Where Used
- Event-driven systems
- Messaging and notification systems
- Workflow engines
- Game development (AI strategies, states)

---

# Key Takeaways
- **Creational:** Solve object creation problems.  
- **Structural:** Solve class composition and interface compatibility problems.  
- **Behavioral:** Solve object communication and responsibility problems.  

Design patterns make software **scalable, reusable, and easier to maintain**.

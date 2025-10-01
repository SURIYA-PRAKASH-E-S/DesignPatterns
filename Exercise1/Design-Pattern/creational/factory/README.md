
## Pattern Name
**Factory**

## Pattern Category
Creational

---

## Description
- The **Factory Pattern** is used to create objects without exposing the creation logic to the client.  
- The client requests an object through a factory class instead of using `new` directly.  
- This makes the system **extensible** and **maintainable** when adding new product types.

**The key idea is:**  
*"Delegate object creation to a factory, so adding new products doesn’t break existing code."*

---

## The Problem
- In a food delivery app, users can order different dishes like **Pizza**, **Burger**, or **Pasta**.  
- If the client directly uses `new Pizza()` or `new Burger()`, the code becomes tightly coupled.  
- Adding a new dish (like **Salad**) would require modifying existing order-processing logic.

---

## The Solution
- Introduce a **FoodFactory** that handles object creation.  
- Each food item implements a common interface `Food`.  
- The client just calls the factory → gets the required dish → no need to change existing code.

---

## Code Flow

**What This Example Does:**  
- Demonstrates how to order different dishes using the Factory Pattern.  
- Shows how adding new food items is simple without touching client logic.

**Step-by-Step Code Flow:**

1. **Food Interface (Food.java)**  
   - Declares a `prepare()` method for all food items.

2. **Concrete Products (Pizza.java, Burger.java, Salad.java)**  
   - Implement the `Food` interface.  
   - Provide their own `prepare()` method.

3. **Abstract Factory (FoodFactory.java)**  
   - Declares `createFood()` method.

4. **Concrete Factories (PizzaFactory.java, BurgerFactory.java, PastaFactory.java, SaladFactory.java)**  
   - Each overrides `createFood()` to return its dish.

5. **Main Program (Main.java)**  
   - Client selects a dish.  
   - Calls the factory to get the object.  
   - Calls `prepare()` without knowing how it was created.

---

## Real-World Example: Food Delivery App

**Without Factory Pattern:**  
- Client code directly uses `new Pizza()`, `new Burger()`.  
- Adding new dishes requires modifying client code.

**With Factory Pattern:**  
- Client just asks `FoodFactory` for a dish.  
- Adding new dishes (e.g., Salad) is easy.  
- Core order logic stays unchanged.

---

## Benefits
- **Encapsulation:** Hides object creation logic.  
- **Extensibility:** Easy to add new food items.  
- **Loose Coupling:** Client depends on abstraction, not concrete classes.  
- **Maintainability:** Core code remains untouched.

---

## Use Cases
- Food delivery apps.  
- Payment methods (CreditCard, UPI, PayPal).  
- Document parsers (PDF, Word, Excel).  
- Vehicle rental systems (Car, Bike, Truck).

---

## Drawbacks
- **Extra Classes:** Each new product needs a new factory.  
- **Overhead:** Slightly more complex than direct `new`.  
- **Overkill for Simple Cases.**

---

## How to Execute the Code
1. Navigate to the builder folder in terminal:
```bash
   cd creational/factory/
```
2. Compile:
```bash
javac *.java
```
3. Run:
```bash
java Main
```
---

### Expected Output

```sh
--- Factory Pattern ---

Preparing Pizza
Preparing Burger
Preparing a fresh salad
```
---
### Key Takeaways

- The Factory Pattern removes direct object creation from client code.

- Makes the system scalable and flexible.

- Adding a new product requires minimal changes.

- Widely used in real-world applications with multiple product types.

---
## Analogy

*Think of it like ordering drinks at a cafe:*

- Barista = Factory (creates the drink)

- Customer Order = Client request (Coffee, Tea, Juice)

- Drink Objects = Concrete products (Coffee cup, Tea cup, Juice glass)

`Result:` Customer gets the requested drink without knowing preparation details.


## Pattern Name
**Builder**

## Pattern Category
Creational

---

## Description
- The **Builder Pattern** is used to construct complex objects step by step.  
- It separates the construction process from the final representation of the object.  
- Instead of using a constructor with many parameters, the Builder allows chaining methods to configure the object gradually.  
- Makes object creation more **readable, flexible, and extensible**.  

**The key idea is:**  
*"Build an object step by step, and get the final product at the end."*

---

## Problem Statement
In a car manufacturing system, a **Car** object is complex and consists of many parts:  
engine, wheels, seats, color, and safety features.  

Different car models (SUV, Sedan, Hatchback) require different configurations.  
Using a constructor with many parameters leads to messy and unreadable code.  
Optional parameters and multiple constructor combinations make the code error-prone.

---

## The Solution
- Provide a **CarBuilder** class with methods to configure each part of the car.  
- Use **method chaining** for readability.  
- The `build()` method returns the final **Car object**.  
- Default values are applied for unspecified parts.  
- Different car models can be assembled step by step without changing the `Car` class.  

---

## Code Flow

**What This Example Does:**  
Demonstrates how to build different car models (SUV, Sedan, Hatchback) using the **Builder Pattern**.  
Instead of having multiple constructors, we use `CarBuilder` to configure each car’s features.

**Step-by-Step Code Flow:**

1. **Car Class (Car.java)**  
   - Purpose: The final product that gets built.  
   - Contains: Model, engine, wheels, color, safety features.  
   - Constructor: Takes all parameters at once.  
   - Methods: `toString()` for displaying car details.  

2. **CarBuilder Class (CarBuilder.java)**  
   - Purpose: Builds `Car` objects step by step.  
   - Methods:  
     - `setModel()`, `setEngine()`, `setWheels()`, `setColor()`, `setSafetyFeatures()`  
     - Each returns `this` for method chaining.  
     - `build()` → creates and returns the final Car object with default values for unspecified parts.  

3. **Main Program (Main.java)**  
   - Demonstrates building multiple car models.  
   - Shows flexibility and readability of the Builder Pattern.  

---

## Real-World Example: Car Manufacturing System
Imagine a car factory where customers can:  
- Choose **Car Model** (SUV, Sedan, Hatchback).  
- Select **Engine Type** (Petrol, Diesel, Electric).  
- Configure **Wheels, Color, Seats, Safety Features**.  
- The builder assembles the car step by step.  

**Without Builder Pattern:**  
- Multiple constructors with many parameters.  
- Hard to maintain and extend.  

**With Builder Pattern:**  
- Clean, flexible, and extensible.  
- Easy to add new features without changing existing code.  

---

## Benefits
- **Readable Code:** Method chaining improves readability.  
- **Flexible:** Configure only the parts you want; defaults fill the rest.  
- **Maintainable:** Easy to extend with new features.  
- **No Telescoping Constructors:** Avoid multiple constructors with long parameter lists.  
- **Immutable Product:** Once built, the Car object remains unchanged.  

---

## Use Cases
- **Car Manufacturing:** Different models with custom configurations.  
- **Meal Ordering Systems:** Customizing meals step by step.  
- **House Construction:** Build houses with different floors, rooms, facilities.  
- **Document Builders:** Constructing complex reports.  
- **UI Component Builders:** Building UI with different configurations.  

---

## Drawbacks
- **Extra Classes:** Requires Builder class.  
- **Overkill for Simple Objects:** Not needed if object has few parameters.  
- **Slight Complexity:** More code compared to a simple constructor.  

---

## How to Execute the Code
1. Navigate to the builder folder in terminal:
```bash
   cd creational/builder/
```
2. Compile all Java files: 
```bash
  javac *.java
```
3. Run the main program:
```bash
 java Main
```
---
## Expected Output

```sh
--- Builder Pattern Demo ---

1. SUV Car:
Car {model='SUV', engine='V8 Engine', wheels='18-inch Alloy Wheels', color='Black', safetyFeatures='Airbags, ABS, Hill Assist'}

2. Sedan Car:
Car {model='Sedan', engine='V6 Engine', wheels='Alloy Wheels', color='Blue', safetyFeatures='Airbags, ABS'}

3. Hatchback Car:
Car {model='Hatchback', engine='1.2L Petrol Engine', wheels='Alloy Wheels', color='Red', safetyFeatures='Airbags, ABS'}

4. Default Car:
Car {model='Basic Car', engine='Standard Engine', wheels='Alloy Wheels', color='White', safetyFeatures='Airbags, ABS'}
```
---
## Key Takeaways

- **Method Chaining:** Each setter returns this, allowing chained calls.

- **Default Values:** Unspecified parameters get sensible defaults.

- **Flexibility:** Can build any combination of car features.

- **Readability:** Code is self-documenting and easy to understand.

- **Maintainability:** Easy to add new car parts or change defaults.

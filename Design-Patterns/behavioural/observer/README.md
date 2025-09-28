

## Pattern Name
**Observer Pattern**

## Pattern Category
**Behavioural**

---

## Description

- The **Observer pattern** defines a **one-to-many dependency** between objects so that when one object changes state, all its dependents are notified and updated automatically.
- It helps establish **loose coupling** between the **subject** and its **observers**.
- Think of it like a **Flipkart order system**: when the order status changes, multiple services (Customer App, Seller Dashboard, Logistics) are notified instantly.

---

## The Problem

You want to:
- Notify multiple systems when an order status changes.
- Maintain consistency across systems.
- Avoid tightly coupling the order system with each service.
- Allow dynamic subscription/unsubscription.
- Scale easily by adding new observers.

---

## The Solution

- Create a **Subject interface** (`Subject.java`) that defines how observers are managed.
- Create an **Observer interface** (`Observer.java`) with an `update()` method.
- **OrderSystem** acts as the **concrete subject** and notifies all registered services.
- Each service (Customer, Seller, Logistics) implements **Observer** to get updates.
- The OrderSystem doesn’t need to know what each service does internally.

---

## Code Flow 

### Components

1. **Subject Interface** (`Subject.java`)
   - Manages observer registration and notification.
   - Methods: `registerObserver()`, `removeObserver()`, `notifyObservers()`

2. **Observer Interface** (`Observer.java`)
   - Defines the `update(orderId, status)` method for receiving updates.

3. **OrderSystem Class** (`OrderSystem.java`)
   - Implements `Subject`.
   - Maintains a list of observers and notifies them on status change.

4. **Concrete Observers**
   - `CustomerNotificationService.java`: Notifies customers.
   - `SellerDashboardService.java`: Updates seller dashboard.
   - `LogisticsService.java`: Notifies logistics system.

5. **Main Class** (`Main.java`)
   - Demonstrates the full flow: order placed → status updated → observers notified.

---

## Real-World Use Case: Flipkart Order Tracking

When a customer places an order:
- The OrderSystem updates the status (Placed, Shipped, Delivered).
- All registered services are notified:
  - **Customer App** shows tracking.
  - **Seller Dashboard** shows order queue.
  - **Logistics** starts or updates shipment.

---

## Key Takeaways

- Loose Coupling – Services don't need to know about the OrderSystem's internals.

- Automatic Notification – Observers are notified automatically on changes.

- Dynamic Registration – Services can subscribe/unsubscribe at runtime.

- Broadcast Communication – One change affects multiple observers.

- Scalable & Maintainable – New services can be added without modifying core logic.

---

## Use Cases

- E-commerce platforms (order status updates)

- Social media feeds (followers get updates)

- Event handling systems (GUIs, sensors)

- Model-View-Controller (MVC) architectures

- Logging or auditing systems

---

## Drawbacks

- Memory Leaks if observers aren't removed properly.

- Performance Issues with too many observers.

- Notification Order may not be guaranteed.

- Unexpected Updates if observers don’t filter messages.

---

**How to Execute the Code**

- Navigate to the observer folder in terminal: 
 ```bash
 cd behavioural/observer
```
- Compile all Java files: 
```bash
  javac *.java
```
- Run the main program:
```bash
 java Main
```
## Expected Output

```sh
--- Observer Pattern Demo ---

Order ORDER#123 status changed to: Placed
Customer App: Order ORDER#123 is now Placed
Seller Dashboard: Order ORDER#123 updated to Placed
Logistics System: Order ORDER#123 is Placed

Order ORDER#123 status changed to: Shipped
Customer App: Order ORDER#123 is now Shipped
Seller Dashboard: Order ORDER#123 updated to Shipped
Logistics System: Order ORDER#123 is Shipped

Order ORDER#123 status changed to: Delivered
Customer App: Order ORDER#123 is now Delivered
Seller Dashboard: Order ORDER#123 updated to Delivered
Logistics System: Order ORDER#123 is Delivered
```


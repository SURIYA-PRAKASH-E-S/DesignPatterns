## Pattern Name
**Strategy Pattern**

## Pattern Category
Behavioural

---

## Description
- The **Strategy pattern** defines a family of algorithms, encapsulates each one, and makes them interchangeable.  
- It allows the algorithm to vary independently from clients that use it.  
- Think of it like choosing a **payment method** – credit card, UPI, wallet, or cash on delivery. The checkout flow remains the same, but the payment logic varies.  
- It’s useful when multiple approaches can achieve the same task and need to be interchangeable.  

---

## Problem Statement (Flipkart Payments)
Flipkart supports multiple payment methods like Credit Card, UPI, Wallet, and Cash on Delivery.  
The system should allow selecting a payment method at runtime without changing the core order-processing logic.

---

## The Solution
- Define a **PaymentStrategy** interface (contract for all payment methods).  
- Implement concrete strategy classes:  
  - `CreditCardPayment`  
  - `UPIPayment`  
  - `WalletPayment`  
  - `CashOnDeliveryPayment`  
- Create a **PaymentContext** that uses the selected strategy to process payments.  
- The payment strategy can be changed at runtime depending on the customer’s choice.  

---

## Code Flow

1. **PaymentStrategy (Strategy Interface)**  
   - Declares `pay(double amount)` method.  

2. **Concrete Strategies**  
   - `CreditCardPayment` → Pay using Credit Card.  
   - `UPIPayment` → Pay using UPI.  
   - `WalletPayment` → Pay using Wallet.  
   - `CashOnDeliveryPayment` → Pay with cash at delivery time.  

3. **PaymentContext (Context Class)**  
   - Holds a reference to `PaymentStrategy`.  
   - Calls the `pay()` method of the selected strategy.  

4. **Main (Client Code)**  
   - Chooses the payment strategy at runtime.  

---

## Real-World Example: Flipkart Checkout
1. A customer adds items to the cart.  
2. At checkout, they choose a payment method.  
3. The system applies the selected payment strategy.  
4. Adding new methods (like Cryptocurrency) only requires creating a new strategy class.  

---

## Benefits
- **Encapsulation**: Each payment method has its own class.  
- **Runtime Selection**: Customer chooses at checkout.  
- **Easy Extension**: Add new payment methods without touching existing code.  
- **Eliminates Conditionals**: No long `if-else` chains.  
- **Clean Code**: Checkout logic is separated from payment logic.  

---

## Drawbacks
- More classes (object proliferation).  
- Client must know available strategies.  
- Strategy selection must be handled.  

---

## How to Run

1. Navigate to project folder:
   ```bash
   cd behavioural/strategy/
   ```

2. Compile all Java files:
   ```bash
   javac *.java
   ```

3. Run the program:
  ```bash
  java Main
  ```
---

## Expected Output

```sh
--- Strategy Pattern Demo ---

Paid Rs.500.0 using Credit Card (Card No: 1234-5678-9876-5432)
Paid Rs.300.0 using UPI (UPI ID: user@upi)
Paid Rs.300.0 using Wallet (Wallet ID: WALLET123)
Paid Rs.300.0 using Cash on Delivery
```
---

## Key Takeaways

- Encapsulation: Each algorithm (payment method) is isolated.

- Interchangeability: Strategies can be swapped at runtime.

- Clean Architecture: Payment logic separated from checkout.

- Easy Extension: Adding new payment methods is simple.

- Runtime Flexibility: Customer chooses how to pay.

---

## SAnalogy

Think of it like choosing transportation:

- Destination = Checkout task.

- Transportation Modes = Strategies (car, bus, bike, walk).

- Traveler = Context (uses chosen strategy).

- Route = Customer’s choice at runtime.

- Result = Same destination reached using different methods.


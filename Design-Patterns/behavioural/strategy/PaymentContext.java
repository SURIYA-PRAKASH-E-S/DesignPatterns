public class PaymentContext {
    private PaymentStrategy paymentStrategy;

    // Set payment strategy at runtime
    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    // Execute payment
    public void payAmount(double amount) {
        if (paymentStrategy == null) {
            System.out.println("No payment strategy selected!");
        } else {
            paymentStrategy.pay(amount);
        }
    }
}

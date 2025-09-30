
public class CashOnDeliveryPayment implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.println("Paid Rs." + amount + " using Cash on Delivery");
    }
}

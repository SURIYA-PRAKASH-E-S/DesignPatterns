// Client code
public class Main {
    public static void main(String[] args) {
        System.out.println("--- Strategy Pattern Demo ---\n");

        PaymentContext context = new PaymentContext();

        // Pay using Credit Card
        context.setPaymentStrategy(new CreditCardPayment("1234-5678-9876-5432"));
        context.payAmount(500);

        // Pay using UPI
        context.setPaymentStrategy(new UPIPayment("user@upi"));
        context.payAmount(300);

        // Pay using Wallet
        context.setPaymentStrategy(new WalletPayment("WALLET123"));
        context.payAmount(300);

        // Pay using Cash on Delivery
        context.setPaymentStrategy(new CashOnDeliveryPayment());
        context.payAmount(300);
    }
}

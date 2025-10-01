
public class CustomerNotificationService implements Observer {
    @Override
    public void update(String orderId, String status) {
        System.out.println("Customer App: Order " + orderId + " is now " + status);
    }
}

    
public class LogisticsService implements Observer {
    @Override
    public void update(String orderId, String status) {
        System.out.println("Logistics System: Order " + orderId + " is " + status);
    }
}

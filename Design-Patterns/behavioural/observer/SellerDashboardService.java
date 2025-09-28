
public class SellerDashboardService implements Observer {
    @Override
    public void update(String orderId, String status) {
        System.out.println("Seller Dashboard: Order " + orderId + " updated to " + status);
    }
}

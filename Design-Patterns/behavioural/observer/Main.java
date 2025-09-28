
public class Main {
    public static void main(String[] args) {
        System.out.println("--- Observer Pattern Demo ---");

        // Create the subject
        OrderSystem order = new OrderSystem("ORDER#123");

        // Create observers
        Observer customerApp = new CustomerNotificationService();
        Observer sellerDashboard = new SellerDashboardService();
        Observer logistics = new LogisticsService();

        // Register observers
        order.registerObserver(customerApp);
        order.registerObserver(sellerDashboard);
        order.registerObserver(logistics);

        // Simulate order status updates
        order.updateOrderStatus("Placed");
        order.updateOrderStatus("Shipped");
        order.updateOrderStatus("Delivered");
    }
}

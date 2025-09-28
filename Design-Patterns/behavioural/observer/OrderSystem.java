import java.util.*;

public class OrderSystem implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private String orderId;
    private String status;

    public OrderSystem(String orderId) {
        this.orderId = orderId;
    }

    public void updateOrderStatus(String status) {
        this.status = status;
        System.out.println("\nOrder " + orderId + " status changed to: " + status);
        notifyObservers();
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(orderId, status);
        }
    }
}


// Concrete Factory
public class PizzaFactory extends FoodFactory {
   
    public Food createFood() {
        return new Pizza();
    }
}
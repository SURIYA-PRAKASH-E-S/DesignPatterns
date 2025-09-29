// Concrete Factory
public class SaladFactory extends FoodFactory {
    @Override
    public Food createFood() {
        return new Salad();
    }
    
}

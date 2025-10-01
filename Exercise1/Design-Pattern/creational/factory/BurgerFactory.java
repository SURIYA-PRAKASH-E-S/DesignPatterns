// Concrete Factory
public class BurgerFactory extends FoodFactory {
    @Override
    public Food createFood() {
        return new Burger();
    }
    
}

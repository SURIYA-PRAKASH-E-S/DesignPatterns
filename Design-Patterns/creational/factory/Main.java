// Client Code
public class Main {
    public static void main(String[] args) {
        System.out.println("--- Factory Pattern ---\n");
        // Using Pizza Factory
        FoodFactory factory = new PizzaFactory();
        Food pizza = factory.createFood();
        System.out.println(pizza.prepare());

        // Using Burger Factory
        factory = new BurgerFactory();
        Food burger = factory.createFood();
        System.out.println(burger.prepare());

        // Using Salad Factory
        factory = new SaladFactory();
        Food salad = factory.createFood();
        System.out.println(salad.prepare());
    }
}

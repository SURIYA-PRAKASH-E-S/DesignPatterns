public class Main {
    public static void main(String[] args) {
        System.out.println("--- Builder Pattern Demo ---");

        // SUV Example
        Car suv = new CarBuilder()
                .setModel("SUV")
                .setEngine("V8 Engine")
                .setWheels("18-inch Alloy Wheels")
                .setColor("Black")
                .setSafetyFeatures("Airbags, ABS, Hill Assist")
                .build();
        System.out.println("\n1. SUV Car:\n" + suv);

        // Sedan Example
        Car sedan = new CarBuilder()
                .setModel("Sedan")
                .setEngine("V6 Engine")
                .setColor("Blue")
                .setSafetyFeatures("Airbags, ABS")
                .build();
        System.out.println("\n2. Sedan Car:\n" + sedan);

        // Hatchback Example (with partial configuration)
        Car hatchback = new CarBuilder()
                .setModel("Hatchback")
                .setEngine("1.2L Petrol Engine")
                .setColor("Red")
                .build();
        System.out.println("\n3. Hatchback Car:\n" + hatchback);

        // Default Car Example
        Car defaultCar = new CarBuilder()
                .setModel("Basic Car")
                .build();
        System.out.println("\n4. Default Car:\n" + defaultCar);
    }
}

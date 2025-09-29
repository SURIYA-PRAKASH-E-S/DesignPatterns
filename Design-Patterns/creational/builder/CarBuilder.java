public class CarBuilder {
    private String model;
    private String engine;
    private String wheels;
    private String color;
    private String safetyFeatures;

    public CarBuilder setModel(String model) {
        this.model = model;
        return this;
    }

    public CarBuilder setEngine(String engine) {
        this.engine = engine;
        return this;
    }

    public CarBuilder setWheels(String wheels) {
        this.wheels = wheels;
        return this;
    }

    public CarBuilder setColor(String color) {
        this.color = color;
        return this;
    }

    public CarBuilder setSafetyFeatures(String safetyFeatures) {
        this.safetyFeatures = safetyFeatures;
        return this;
    }



    // Final build method
    public Car build() {
        // Default values for unspecified parts
        if (engine == null) engine = "Standard Engine";
        if (wheels == null) wheels = "Alloy Wheels";
        if (color == null) color = "White";
        if (safetyFeatures == null) safetyFeatures = "Airbags, ABS";

        return new Car(model, engine, wheels, color, safetyFeatures);
    }
}

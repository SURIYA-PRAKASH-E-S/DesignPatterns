public class Car {
    private String model;
    private String engine;
    private String wheels;
    private String color;
    private String safetyFeatures;

    // Constructor (only used by builder)
    public Car(String model, String engine, String wheels, String color,
               String safetyFeatures) {
        this.model = model;
        this.engine = engine;
        this.wheels = wheels;
        this.color = color;
        this.safetyFeatures = safetyFeatures;
    }

    @Override
    public String toString() {
        return "Car {" +
                "model='" + model + '\'' +
                ", engine='" + engine + '\'' +
                ", wheels='" + wheels + '\'' +
                ", color='" + color + '\'' +
                ", safetyFeatures='" + safetyFeatures + '\'' +
                '}';
    }
}

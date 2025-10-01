// Client Code
public class Main {
    public static void main(String[] args) {
       System.out.println("--- Decorator Pattern ---\n");

        // Base text
        Text text = new PlainText("Hello World");
        System.out.println("Plain Text: " + text.render());

        // Apply Bold
        Text boldText = new BoldDecorator(text);
        System.out.println("Bold: " + boldText.render());

        // Apply Bold + Italic
        Text boldItalicText = new ItalicDecorator(boldText);
        System.out.println("Bold + Italic: " + boldItalicText.render());

        // Apply Bold + Italic + Underline
        Text fullyDecorated = new UnderlineDecorator(boldItalicText);
        System.out.println("Bold + Italic + Underline: " + fullyDecorated.render());
    }
}

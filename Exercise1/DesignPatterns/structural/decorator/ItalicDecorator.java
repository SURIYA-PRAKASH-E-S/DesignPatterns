// Italic Decorator
public class ItalicDecorator extends TextDecorator {

    public ItalicDecorator(Text text) {
        super(text);
    }

    @Override
    public String render() {
        return "*" + super.render() + "*";  // Representing italic with *
    }
}

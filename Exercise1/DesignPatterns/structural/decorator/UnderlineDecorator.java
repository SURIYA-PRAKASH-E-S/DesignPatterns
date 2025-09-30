// Underline Decorator
public class UnderlineDecorator extends TextDecorator {

    public UnderlineDecorator(Text text) {
        super(text);
    }

    @Override
    public String render() {
        return "_" + super.render() + "_";  // Representing underline with _
    }
}

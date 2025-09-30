// Bold Decorator
public class BoldDecorator extends TextDecorator {

    public BoldDecorator(Text text) {
        super(text);
    }

    @Override
    public String render() {
        return "**" + super.render() + "**";  // Representing bold with **
    }
}

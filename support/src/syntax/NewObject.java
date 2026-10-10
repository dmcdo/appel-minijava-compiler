package syntax;

public class NewObject extends Expression {
    /** Name of the class being instantiated. */
    public final Identifier i;

    public NewObject(int l, int c, Identifier i) {
        super(l, c);
        this.i = i;
    }

    public <T> T accept(SyntaxTreeVisitor<T> v) { return v.visit(this); }
}

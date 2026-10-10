package syntax;

public class ArrayLength extends Expression {
    public final Expression expressionForArray;

    public ArrayLength(int l, int c, Expression expressionForArray) {
        super(l, c);
        this.expressionForArray = expressionForArray;
    }

    public <T> T accept(SyntaxTreeVisitor<T> v) { return v.visit(this); }
}

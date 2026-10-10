package syntax;

public class ArrayLookup extends Expression {
    public final Expression expressionForArray;
    public final Expression indexInArray;

    public ArrayLookup(int l, int c, Expression expressionForArray, Expression indexInArray) {
        super(l, c);
        this.expressionForArray = expressionForArray;
        this.indexInArray = indexInArray;
    }

    public <T> T accept(SyntaxTreeVisitor<T> v) { return v.visit(this); }
}

package syntax;

public class IntegerLiteral extends Expression {
    public final int i;

    public IntegerLiteral(int l, int c, int i) {
        super(l, c);
        this.i = i;
    }

    public <T> T accept(SyntaxTreeVisitor<T> v) { return v.visit(this); }
}

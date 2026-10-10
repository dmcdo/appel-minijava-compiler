package syntax;

public class Minus extends Expression {
    public final Expression e1;
    public final Expression e2;

    public Minus(int l, int c, Expression e1, Expression e2) {
        super(l, c);
        this.e1 = e1;
        this.e2 = e2;
    }

    public <T> T accept(SyntaxTreeVisitor<T> v) { return v.visit(this); }
}

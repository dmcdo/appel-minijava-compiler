package syntax;

public class Times extends Expression {
    public final Expression e1;
    public final Expression e2;

    public Times(int l, int c, Expression e1, Expression e2) {
        super(l, c);
        this.e1 = e1;
        this.e2 = e2;
    }

    public <T> T accept(SyntaxTreeVisitor<T> v) { return v.visit(this); }
}

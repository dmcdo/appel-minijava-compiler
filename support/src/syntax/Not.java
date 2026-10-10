package syntax;

public class Not extends Expression {
    public final Expression e;

    public Not(int l, int c, Expression e) {
        super(l, c);
        this.e = e;
    }

    public <T> T accept(SyntaxTreeVisitor<T> v) { return v.visit(this); }
}

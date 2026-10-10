package syntax;

public class Assign extends Statement {
    public final Identifier i;
    public final Expression e;

    public Assign(int l, int c, Identifier i, Expression e) {
        super(l, c);
        this.i = i;
        this.e = e;
    }

    public <T> T accept(SyntaxTreeVisitor<T> v) { return v.visit(this); }
}

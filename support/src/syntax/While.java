package syntax;

public class While extends Statement {
    public final Expression e;
    public final Statement s;

    public While(int l, int c, Expression e, Statement s) {
        super(l, c);
        this.e = e;
        this.s = s;
    }

    public <T> T accept(SyntaxTreeVisitor<T> v) { return v.visit(this); }
}

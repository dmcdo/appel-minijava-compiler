package syntax;

public class If extends Statement {
    public final Expression e;
    public final Statement s1;
    public final Statement s2;

    public If(int l, int c, Expression e, Statement s1, Statement s2) {
        super(l, c);
        this.e = e;
        this.s1 = s1;
        this.s2 = s2;
    }

    public <T> T accept(SyntaxTreeVisitor<T> v) { return v.visit(this); }
}

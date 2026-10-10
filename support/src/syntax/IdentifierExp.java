package syntax;

public class IdentifierExp extends Expression {
    public final String s;

    public IdentifierExp(int l, int c, String s) {
        super(l, c);
        this.s = s;
    }

    public <T> T accept(SyntaxTreeVisitor<T> v) { return v.visit(this); }
}

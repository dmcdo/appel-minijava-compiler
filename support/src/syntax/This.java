package syntax;

public class This extends Expression {
    public This(int l, int c) {
        super(l, c);
    }

    public <T> T accept(SyntaxTreeVisitor<T> v) { return v.visit(this); }
}

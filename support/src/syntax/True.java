package syntax;

public class True extends Expression {
    public True(int l, int c) {
        super(l, c);
    }

    public <T> T accept(SyntaxTreeVisitor<T> v) { return v.visit(this); }
}

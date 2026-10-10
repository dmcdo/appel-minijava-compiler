package syntax;

public class False extends Expression {
    public False(int l, int c) {
        super(l, c);
    }

    public <T> T accept(SyntaxTreeVisitor<T> v) { return v.visit(this); }
}

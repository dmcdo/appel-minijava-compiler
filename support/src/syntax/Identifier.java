package syntax;

public class Identifier extends AST {
    public final String s;

    public Identifier(int l, int c, String s) {
        super(l, c);
        this.s = s;
    }

    public <T> T accept(SyntaxTreeVisitor<T> v) { return v.visit(this); }

    @Override
    public String toString() { return s; }
}

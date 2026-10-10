package syntax;

public class LocalDecl extends AST {
    public final Type t;
    public final Identifier i;

    public LocalDecl(Type t, Identifier i) {
        super(i.lineNumber, i.columnNumber);
        this.t = t;
        this.i = i;
    }

    public <T> T accept(SyntaxTreeVisitor<T> v) { return v.visit(this); }
}

package syntax;

public class FormalDecl extends AST {
    public final Type t;
    public final Identifier i;

    public FormalDecl(Type t, Identifier i) {
        super(i.lineNumber, i.columnNumber);
        this.t = t;
        this.i = i;
    }

    public <T> T accept(SyntaxTreeVisitor<T> v) { return v.visit(this); }
}

package syntax;

import java.util.List;

public class MethodDecl extends AST {
    public final Type t;
    public final Identifier i;
    public final List<FormalDecl> formals;
    public final List<LocalDecl> locals;
    public final List<Statement> sl;
    /** The returned expression. */
    public final Expression e;

    public MethodDecl(Type t, Identifier i, List<FormalDecl> formals, List<LocalDecl> locals, List<Statement> sl, Expression e) {
        super(i.lineNumber, i.columnNumber);
        this.t = t;
        this.i = i;
        this.formals = formals;
        this.locals = locals;
        this.sl = sl;
        this.e = e;
    }

    public <T> T accept(SyntaxTreeVisitor<T> v) { return v.visit(this); }
}

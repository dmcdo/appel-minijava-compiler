package syntax;

import java.util.List;

public class Program extends AST {
    public final MainClass m;
    public final List<ClassDecl> cl;

    public Program(MainClass m, List<ClassDecl> cl) {
        super(m == null ? 0 : m.lineNumber, m == null ? 0 : m.columnNumber);
        this.m = m;
        this.cl = cl;
    }

    public <T> T accept(SyntaxTreeVisitor<T> v) { return v.visit(this); }
}

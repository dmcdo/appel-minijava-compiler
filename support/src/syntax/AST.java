package syntax;

/** Root of the MiniJava abstract syntax tree. */
public abstract class AST {
    public final int lineNumber;
    public final int columnNumber;

    public AST() {
        this(0, 0);
    }

    public AST(int lineNumber, int columnNumber) {
        this.lineNumber = lineNumber;
        this.columnNumber = columnNumber;
    }

    public abstract <T> T accept(SyntaxTreeVisitor<T> v);
}

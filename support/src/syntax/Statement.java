package syntax;

public abstract class Statement extends AST {
    public Statement() {
        super();
    }

    public Statement(int l, int c) {
        super(l, c);
    }
}

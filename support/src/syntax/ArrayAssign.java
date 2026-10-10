package syntax;

public class ArrayAssign extends Statement {
    public final Identifier nameOfArray;
    public final Expression indexInArray;
    public final Expression e;

    public ArrayAssign(int l, int c, Identifier nameOfArray, Expression indexInArray, Expression e) {
        super(l, c);
        this.nameOfArray = nameOfArray;
        this.indexInArray = indexInArray;
        this.e = e;
    }

    public <T> T accept(SyntaxTreeVisitor<T> v) { return v.visit(this); }
}

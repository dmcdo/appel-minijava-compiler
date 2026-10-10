package syntax;

import java.util.List;

public class Call extends Expression {
    /** The receiver. */
    public final Expression e;
    /** The method name. */
    public final Identifier i;
    /** The actual arguments. */
    public final List<Expression> el;

    public Call(int l, int c, Expression e, Identifier i, List<Expression> el) {
        super(l, c);
        this.e = e;
        this.i = i;
        this.el = el;
    }

    public <T> T accept(SyntaxTreeVisitor<T> v) { return v.visit(this); }
}

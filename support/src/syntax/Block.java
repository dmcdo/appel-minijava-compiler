package syntax;

import java.util.List;

public class Block extends Statement {
    public final List<Statement> sl;

    public Block(List<Statement> sl) {
        super();
        this.sl = sl;
    }

    public Block(int l, int c, List<Statement> sl) {
        super(l, c);
        this.sl = sl;
    }

    public <T> T accept(SyntaxTreeVisitor<T> v) { return v.visit(this); }
}

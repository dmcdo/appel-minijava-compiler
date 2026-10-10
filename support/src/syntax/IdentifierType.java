package syntax;

public class IdentifierType extends Type {
    public final String nameOfType;

    public IdentifierType(int l, int c, String nameOfType) {
        super(l, c);
        this.nameOfType = nameOfType;
    }

    public <T> T accept(SyntaxTreeVisitor<T> v) { return v.visit(this); }

    @Override
    public String toString() { return nameOfType; }
}

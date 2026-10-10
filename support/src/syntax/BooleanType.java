package syntax;

public class BooleanType extends Type {
    public <T> T accept(SyntaxTreeVisitor<T> v) { return v.visit(this); }

    @Override
    public String toString() { return "boolean"; }
}

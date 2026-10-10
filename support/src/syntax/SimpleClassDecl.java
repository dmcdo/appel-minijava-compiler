package syntax;

import java.util.List;

public class SimpleClassDecl extends ClassDecl {
    public SimpleClassDecl(Identifier i, List<FieldDecl> fields, List<MethodDecl> methods) {
        super(i, fields, methods);
    }

    public <T> T accept(SyntaxTreeVisitor<T> v) { return v.visit(this); }
}

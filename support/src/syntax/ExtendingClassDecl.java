package syntax;

import java.util.List;

public class ExtendingClassDecl extends ClassDecl {
    /** Name of the superclass. */
    public final Identifier j;

    public ExtendingClassDecl(Identifier i, Identifier j, List<FieldDecl> fields, List<MethodDecl> methods) {
        super(i, fields, methods);
        this.j = j;
    }

    public <T> T accept(SyntaxTreeVisitor<T> v) { return v.visit(this); }
}

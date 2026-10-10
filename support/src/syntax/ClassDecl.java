package syntax;

import java.util.List;

public abstract class ClassDecl extends AST {
    public final Identifier i;
    public final List<FieldDecl> fields;
    public final List<MethodDecl> methods;

    public ClassDecl(Identifier i, List<FieldDecl> fields, List<MethodDecl> methods) {
        super(i.lineNumber, i.columnNumber);
        this.i = i;
        this.fields = fields;
        this.methods = methods;
    }
}

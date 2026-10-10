package syntax;

public class MainClass extends AST {
    public final Identifier nameOfMainClass;
    public final Identifier nameOfCommandLineArgs;
    public final Statement body;

    public MainClass(int l, int c, Identifier nameOfMainClass, Identifier nameOfCommandLineArgs, Statement body) {
        super(l, c);
        this.nameOfMainClass = nameOfMainClass;
        this.nameOfCommandLineArgs = nameOfCommandLineArgs;
        this.body = body;
    }

    public <T> T accept(SyntaxTreeVisitor<T> v) { return v.visit(this); }
}

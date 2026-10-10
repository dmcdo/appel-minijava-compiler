package syntax;

/**
 * A MiniJava type.  toString() gives the source spelling ("int", "boolean",
 * "int[]", "void" or the class name); for the built-in types it is always
 * the same interned literal.
 */
public abstract class Type extends AST {
    public static final Type THE_INTEGER_TYPE   = new IntegerType();
    public static final Type THE_BOOLEAN_TYPE   = new BooleanType();
    public static final Type THE_INT_ARRAY_TYPE = new IntArrayType();
    public static final Type THE_VOID_TYPE      = new VoidType();

    public Type() {
        super();
    }

    public Type(int l, int c) {
        super(l, c);
    }
}

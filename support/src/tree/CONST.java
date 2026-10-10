package tree;

import java.util.Collections;
import java.util.List;

public class CONST extends Exp {
    public static final CONST TRUE  = new CONST(1);
    public static final CONST FALSE = new CONST(0);
    public static final CONST ZERO  = new CONST(0);
    public static final CONST ONE   = new CONST(1);

    public final int value;

    public CONST(int value) {
        this.value = value;
    }

    public ExpList kids() { return null; }
    public Exp build(ExpList kids) { return this; }
    public List<Exp> subcomponents() { return Collections.emptyList(); }
    public Exp create(List<Exp> kids) { return this; }
}

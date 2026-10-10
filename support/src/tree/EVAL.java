package tree;

import java.util.Arrays;
import java.util.List;

/** Evaluate an expression and discard the result (Appel calls this EXP). */
public class EVAL extends Stm {
    public final Exp exp;

    public EVAL(Exp exp) {
        this.exp = exp;
    }

    public ExpList kids() { return new ExpList(exp, null); }
    public Stm build(ExpList kids) { return new EVAL(kids.head); }
    public List<Exp> subcomponents() { return Arrays.asList(exp); }
    public Stm create(List<Exp> kids) { return new EVAL(kids.get(0)); }
}

package tree;

import java.util.Arrays;
import java.util.List;

public class MEM extends Exp {
    public final Exp exp;

    public MEM(Exp exp) {
        this.exp = exp;
    }

    public ExpList kids() { return new ExpList(exp, null); }
    public Exp build(ExpList kids) { return new MEM(kids.head); }
    public List<Exp> subcomponents() { return Arrays.asList(exp); }
    public Exp create(List<Exp> kids) { return new MEM(kids.get(0)); }
}

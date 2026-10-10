package tree;

import java.util.Arrays;
import java.util.List;

public class JUMP extends Stm {
    public final Exp exp;
    /** Every label the jump can go to. */
    public final List<NameOfLabel> targets;

    public JUMP(Exp exp, List<NameOfLabel> targets) {
        this.exp = exp;
        this.targets = targets;
    }

    public JUMP(NameOfLabel target) {
        this(new NAME(target), Arrays.asList(target));
    }

    public ExpList kids() { return new ExpList(exp, null); }
    public Stm build(ExpList kids) { return new JUMP(kids.head, targets); }
    public List<Exp> subcomponents() { return Arrays.asList(exp); }
    public Stm create(List<Exp> kids) { return new JUMP(kids.get(0), targets); }
}

package tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CALL extends Exp {
    public final Exp func;
    public final ExpList args;

    public CALL(Exp func, ExpList args) {
        this.func = func;
        this.args = args;
    }

    public CALL(Exp func, List<Exp> args) {
        this(func, ExpList.toExpList(args));
    }

    public CALL(Exp func, Exp... args) {
        this(func, Arrays.asList(args));
    }

    /* Rebuilding always gives a plain CALL, even for a subclass. */
    public ExpList kids() { return new ExpList(func, args); }
    public Exp build(ExpList kids) { return new CALL(kids.head, kids.tail); }

    public List<Exp> subcomponents() {
        List<Exp> l = new ArrayList<>();
        l.add(func);
        l.addAll(ExpList.toList(args));
        return l;
    }

    public Exp create(List<Exp> kids) {
        return new CALL(kids.get(0), kids.subList(1, kids.size()));
    }
}

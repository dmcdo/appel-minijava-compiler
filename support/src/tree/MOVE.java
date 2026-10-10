package tree;

import java.util.Arrays;
import java.util.List;

public class MOVE extends Stm {
    public final Exp dst, src;

    public MOVE(Exp dst, Exp src) {
        this.dst = dst;
        this.src = src;
    }

    /* A MEM destination is an address to compute; any other destination is not evaluated. */
    public ExpList kids() {
        if (dst instanceof MEM)
            return new ExpList(((MEM)dst).exp, new ExpList(src, null));
        return new ExpList(src, null);
    }

    public Stm build(ExpList kids) {
        if (dst instanceof MEM)
            return new MOVE(new MEM(kids.head), kids.tail.head);
        return new MOVE(dst, kids.head);
    }

    public List<Exp> subcomponents() {
        if (dst instanceof MEM)
            return Arrays.asList(((MEM)dst).exp, src);
        return Arrays.asList(src);
    }

    public Stm create(List<Exp> kids) {
        if (dst instanceof MEM)
            return new MOVE(new MEM(kids.get(0)), kids.get(1));
        return new MOVE(dst, kids.get(0));
    }
}

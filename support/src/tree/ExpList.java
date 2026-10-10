package tree;

import java.util.ArrayList;
import java.util.List;

/** A linked list of expressions; the empty list is null. */
public class ExpList {
    public final Exp head;
    public final ExpList tail;

    public ExpList(Exp head, ExpList tail) {
        this.head = head;
        this.tail = tail;
    }

    public static ExpList toExpList(List<Exp> l) {
        ExpList r = null;
        if (l != null)
            for (int i = l.size() - 1; i >= 0; i--)
                r = new ExpList(l.get(i), r);
        return r;
    }

    public static List<Exp> toList(ExpList l) {
        List<Exp> r = new ArrayList<>();
        for (; l != null; l = l.tail)
            r.add(l.head);
        return r;
    }

    public List<Exp> toList() {
        return toList(this);
    }
}

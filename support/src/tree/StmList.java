package tree;

import java.util.ArrayList;
import java.util.List;

/** A linked list of statements; the empty list is null. */
public class StmList {
    public final Stm head;
    public final StmList tail;

    public StmList(Stm head, StmList tail) {
        this.head = head;
        this.tail = tail;
    }

    public static StmList toStmList(List<Stm> l) {
        StmList r = null;
        if (l != null)
            for (int i = l.size() - 1; i >= 0; i--)
                r = new StmList(l.get(i), r);
        return r;
    }

    public static List<Stm> toList(StmList l) {
        List<Stm> r = new ArrayList<>();
        for (; l != null; l = l.tail)
            r.add(l.head);
        return r;
    }

    public List<Stm> toList() {
        return toList(this);
    }
}

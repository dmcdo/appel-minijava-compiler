package canon;

import java.util.List;

import tree.*;

/**
 * Rewrites an IR tree into canonical form (Appel, section 8.1): no SEQ or
 * ESEQ, and every CALL is either a statement of its own or the source of a
 * MOVE into a temporary.
 *
 * Nodes are taken apart with kids() and put back together with build(), so
 * a node type defined elsewhere takes part as long as it implements those
 * two; a node whose kids() is null is left alone.
 */
public final class Canon {
    private Canon() {}

    /** MOVE(TEMP, CALL): the call stays where it is. */
    private static final class MoveCall extends Stm {
        final TEMP dst;
        final CALL src;

        MoveCall(TEMP dst, CALL src) {
            this.dst = dst;
            this.src = src;
        }

        public ExpList kids() { return src.kids(); }
        public Stm build(ExpList kids) { return new MOVE(dst, src.build(kids)); }
        public List<Exp> subcomponents() { return src.subcomponents(); }
        public Stm create(List<Exp> kids) { return new MOVE(dst, src.create(kids)); }
    }

    /** EVAL(CALL): the call stays where it is. */
    private static final class ExpCall extends Stm {
        final CALL call;

        ExpCall(CALL call) {
            this.call = call;
        }

        public ExpList kids() { return call.kids(); }
        public Stm build(ExpList kids) { return new EVAL(call.build(kids)); }
        public List<Exp> subcomponents() { return call.subcomponents(); }
        public Stm create(List<Exp> kids) { return new EVAL(call.create(kids)); }
    }

    /** Statements to run first, then expressions free of side effects. */
    private static final class StmExpList {
        final Stm stm;
        final ExpList exps;

        StmExpList(Stm stm, ExpList exps) {
            this.stm = stm;
            this.exps = exps;
        }
    }

    private static final StmExpList nopNull = new StmExpList(Stm.NOOP, null);

    private static boolean isNop(Stm a) {
        return a instanceof EVAL && ((EVAL)a).exp instanceof CONST;
    }

    private static Stm seq(Stm a, Stm b) {
        if (isNop(a)) return b;
        if (isNop(b)) return a;
        return new SEQ(a, b);
    }

    /** Can a be run before b is evaluated instead of after, without changing b? */
    private static boolean commute(Stm a, Exp b) {
        return isNop(a) || b instanceof NAME || b instanceof CONST;
    }

    private static Stm do_stm(Stm s) {
        if (s instanceof SEQ)
            return seq(do_stm(((SEQ)s).left), do_stm(((SEQ)s).right));

        if (s instanceof MOVE) {
            MOVE m = (MOVE)s;
            if (m.dst instanceof TEMP && m.src instanceof CALL)
                return reorder_stm(new MoveCall((TEMP)m.dst, (CALL)m.src));
            if (m.dst instanceof ESEQ)
                return do_stm(new SEQ(((ESEQ)m.dst).stm, new MOVE(((ESEQ)m.dst).exp, m.src)));
            return reorder_stm(s);
        }

        if (s instanceof EVAL && ((EVAL)s).exp instanceof CALL)
            return reorder_stm(new ExpCall((CALL)((EVAL)s).exp));

        return reorder_stm(s);
    }

    private static Stm reorder_stm(Stm s) {
        StmExpList x = reorder(s.kids());
        return seq(x.stm, s.build(x.exps));
    }

    private static ESEQ do_exp(Exp e) {
        if (e instanceof ESEQ) {
            Stm stms = do_stm(((ESEQ)e).stm);
            ESEQ b = do_exp(((ESEQ)e).exp);
            return new ESEQ(seq(stms, b.stm), b.exp);
        }
        return reorder_exp(e);
    }

    private static ESEQ reorder_exp(Exp e) {
        StmExpList x = reorder(e.kids());
        return new ESEQ(x.stm, e.build(x.exps));
    }

    private static StmExpList reorder(ExpList exps) {
        if (exps == null)
            return nopNull;

        Exp a = exps.head;
        if (a instanceof CALL) {
            // Give the result a temporary of its own, so a later call cannot overwrite it.
            NameOfTemp t = NameOfTemp.generateTemp();
            Exp e = new ESEQ(new MOVE(new TEMP(t), a), new TEMP(t));
            return reorder(new ExpList(e, exps.tail));
        }

        ESEQ aa = do_exp(a);
        StmExpList bb = reorder(exps.tail);
        if (commute(bb.stm, aa.exp))
            return new StmExpList(seq(aa.stm, bb.stm), new ExpList(aa.exp, bb.exps));

        NameOfTemp t = NameOfTemp.generateTemp();
        return new StmExpList(
            seq(aa.stm, seq(new MOVE(new TEMP(t), aa.exp), bb.stm)),
            new ExpList(new TEMP(t), bb.exps)
        );
    }

    private static StmList linear(Stm s, StmList l) {
        if (s instanceof SEQ)
            return linear(((SEQ)s).left, linear(((SEQ)s).right, l));
        return new StmList(s, l);
    }

    /** The canonical form of s, as a list of statements. */
    public static List<Stm> linearize(Stm s) {
        return StmList.toList(linear(do_stm(s), null));
    }
}

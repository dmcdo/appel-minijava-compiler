package tree;

import java.io.PrintStream;
import java.util.List;

/** Prints IR trees, one node per line, indented by depth. */
public final class TreePrint {
    private TreePrint() {}

    public static void print(PrintStream out, Stm s) { out.print(toString(s)); }
    public static void print(PrintStream out, Exp e) { out.print(toString(e)); }

    public static void print(PrintStream out, List<Stm> stms) {
        for (Stm s : stms)
            print(out, s);
    }

    public static String toString(Stm s) {
        StringBuilder sb = new StringBuilder();
        stm(sb, s, 0);
        return sb.toString();
    }

    public static String toString(Exp e) {
        StringBuilder sb = new StringBuilder();
        exp(sb, e, 0);
        return sb.toString();
    }

    private static void line(StringBuilder sb, int d, String text) {
        for (int i = 0; i < d; i++)
            sb.append("  ");
        sb.append(text).append('\n');
    }

    private static void stm(StringBuilder sb, Stm s, int d) {
        if (s == null) {
            line(sb, d, "null");
        } else if (s instanceof SEQ) {
            line(sb, d, "SEQ(");
            stm(sb, ((SEQ)s).left, d + 1);
            stm(sb, ((SEQ)s).right, d + 1);
            line(sb, d, ")");
        } else if (s instanceof LABEL) {
            line(sb, d, "LABEL " + ((LABEL)s).label);
        } else if (s instanceof JUMP) {
            line(sb, d, "JUMP(");
            exp(sb, ((JUMP)s).exp, d + 1);
            line(sb, d, ")");
        } else if (s instanceof CJUMP) {
            CJUMP c = (CJUMP)s;
            line(sb, d, "CJUMP(" + CJUMP.toString(c.relop) + ",");
            exp(sb, c.left, d + 1);
            exp(sb, c.right, d + 1);
            line(sb, d + 1, c.iftrue + ", " + c.iffalse);
            line(sb, d, ")");
        } else if (s instanceof MOVE) {
            line(sb, d, "MOVE(");
            exp(sb, ((MOVE)s).dst, d + 1);
            exp(sb, ((MOVE)s).src, d + 1);
            line(sb, d, ")");
        } else if (s instanceof EVAL) {
            line(sb, d, "EVAL(");
            exp(sb, ((EVAL)s).exp, d + 1);
            line(sb, d, ")");
        } else {
            other(sb, s.getClass().getSimpleName(), s.subcomponents(), d);
        }
    }

    private static void exp(StringBuilder sb, Exp e, int d) {
        if (e == null) {
            line(sb, d, "null");
        } else if (e instanceof BINOP) {
            BINOP b = (BINOP)e;
            line(sb, d, "BINOP(" + BINOP.toString(b.binop) + ",");
            exp(sb, b.left, d + 1);
            exp(sb, b.right, d + 1);
            line(sb, d, ")");
        } else if (e instanceof MEM) {
            line(sb, d, "MEM(");
            exp(sb, ((MEM)e).exp, d + 1);
            line(sb, d, ")");
        } else if (e instanceof TEMP) {
            line(sb, d, "TEMP " + ((TEMP)e).temp);
        } else if (e instanceof ESEQ) {
            line(sb, d, "ESEQ(");
            stm(sb, ((ESEQ)e).stm, d + 1);
            exp(sb, ((ESEQ)e).exp, d + 1);
            line(sb, d, ")");
        } else if (e instanceof NAME) {
            line(sb, d, "NAME " + ((NAME)e).label);
        } else if (e instanceof CONST) {
            line(sb, d, "CONST " + ((CONST)e).value);
        } else if (e instanceof CALL) {
            line(sb, d, "CALL(");
            exp(sb, ((CALL)e).func, d + 1);
            for (ExpList a = ((CALL)e).args; a != null; a = a.tail)
                exp(sb, a.head, d + 1);
            line(sb, d, ")");
        } else {
            other(sb, e.getClass().getSimpleName(), e.subcomponents(), d);
        }
    }

    /* A node type defined outside this package. */
    private static void other(StringBuilder sb, String name, List<Exp> kids, int d) {
        if (kids == null || kids.isEmpty()) {
            line(sb, d, name);
            return;
        }
        line(sb, d, name + "(");
        for (Exp k : kids)
            exp(sb, k, d + 1);
        line(sb, d, ")");
    }
}

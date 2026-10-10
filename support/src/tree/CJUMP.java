package tree;

import java.util.Arrays;
import java.util.List;

public class CJUMP extends Stm {
    public static final int EQ = 0, NE = 1, LT = 2, GT = 3, LE = 4, GE = 5,
                            ULT = 6, ULE = 7, UGT = 8, UGE = 9;

    public final int relop;
    public final Exp left, right;
    public final NameOfLabel iftrue, iffalse;

    public CJUMP(int relop, Exp left, Exp right, NameOfLabel iftrue, NameOfLabel iffalse) {
        this.relop = relop;
        this.left = left;
        this.right = right;
        this.iftrue = iftrue;
        this.iffalse = iffalse;
    }

    /** The operator that is true exactly when relop is false. */
    public static int notRel(int relop) {
        switch (relop) {
            case EQ:  return NE;
            case NE:  return EQ;
            case LT:  return GE;
            case GE:  return LT;
            case GT:  return LE;
            case LE:  return GT;
            case ULT: return UGE;
            case UGE: return ULT;
            case UGT: return ULE;
            case ULE: return UGT;
            default:  throw new IllegalArgumentException("bad CJUMP operator " + relop);
        }
    }

    public static String toString(int relop) {
        switch (relop) {
            case EQ:  return "EQ";
            case NE:  return "NE";
            case LT:  return "LT";
            case GT:  return "GT";
            case LE:  return "LE";
            case GE:  return "GE";
            case ULT: return "ULT";
            case ULE: return "ULE";
            case UGT: return "UGT";
            case UGE: return "UGE";
            default:  throw new IllegalArgumentException("bad CJUMP operator " + relop);
        }
    }

    public ExpList kids() { return new ExpList(left, new ExpList(right, null)); }
    public Stm build(ExpList kids) { return new CJUMP(relop, kids.head, kids.tail.head, iftrue, iffalse); }
    public List<Exp> subcomponents() { return Arrays.asList(left, right); }
    public Stm create(List<Exp> kids) { return new CJUMP(relop, kids.get(0), kids.get(1), iftrue, iffalse); }
}

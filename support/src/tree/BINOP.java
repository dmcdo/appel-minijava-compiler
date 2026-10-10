package tree;

import java.util.Arrays;
import java.util.List;

public class BINOP extends Exp {
    public static final int PLUS = 0, MINUS = 1, MUL = 2, DIV = 3,
                            AND = 4, OR = 5,
                            LSHIFT = 6, RSHIFT = 7, ARSHIFT = 8, XOR = 9;

    public final int binop;
    public final Exp left, right;

    public BINOP(int binop, Exp left, Exp right) {
        this.binop = binop;
        this.left = left;
        this.right = right;
    }

    public static String toString(int binop) {
        switch (binop) {
            case PLUS:    return "PLUS";
            case MINUS:   return "MINUS";
            case MUL:     return "MUL";
            case DIV:     return "DIV";
            case AND:     return "AND";
            case OR:      return "OR";
            case LSHIFT:  return "LSHIFT";
            case RSHIFT:  return "RSHIFT";
            case ARSHIFT: return "ARSHIFT";
            case XOR:     return "XOR";
            default:      throw new IllegalArgumentException("bad BINOP operator " + binop);
        }
    }

    public ExpList kids() { return new ExpList(left, new ExpList(right, null)); }
    public Exp build(ExpList kids) { return new BINOP(binop, kids.head, kids.tail.head); }
    public List<Exp> subcomponents() { return Arrays.asList(left, right); }
    public Exp create(List<Exp> kids) { return new BINOP(binop, kids.get(0), kids.get(1)); }
}

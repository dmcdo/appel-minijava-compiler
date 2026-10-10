package assem;

import java.util.List;
import java.util.Map;

import tree.NameOfLabel;
import tree.NameOfTemp;

/**
 * An assembly instruction over temporaries (Appel, section 9.1).
 *
 * In the assembly text, `s0 `s1 ... stand for the temporaries of use(),
 * `d0 `d1 ... for those of def(), `j0 `j1 ... for the labels of jumps(),
 * and `` for a backquote.
 */
public abstract class Instruction {
    public final String assem;

    protected Instruction(String assem) {
        this.assem = assem;
    }

    /** The temporaries read; may be null. */
    public abstract List<NameOfTemp> use();
    /** The temporaries written; may be null. */
    public abstract List<NameOfTemp> def();
    /** The labels control may go to; null means it only falls through. */
    public abstract List<NameOfLabel> jumps();

    /** The assembly text with every temporary under its own name. */
    public String format() {
        return format(null);
    }

    /** The assembly text with every temporary replaced by what the map gives for it. */
    public <T> String format(Map<NameOfTemp, T> map) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < assem.length(); i++) {
            char c = assem.charAt(i);
            if (c != '`') {
                sb.append(c);
                continue;
            }

            if (++i >= assem.length())
                throw new Error("bad assembly format: " + assem);
            char kind = assem.charAt(i);
            if (kind == '`') {
                sb.append('`');
                continue;
            }

            int start = i + 1, end = start;
            while (end < assem.length() && Character.isDigit(assem.charAt(end)))
                end++;
            if (end == start)
                throw new Error("bad assembly format: " + assem);
            int n = Integer.parseInt(assem.substring(start, end));
            i = end - 1;

            switch (kind) {
                case 's': sb.append(name(map, operand(use(), n))); break;
                case 'd': sb.append(name(map, operand(def(), n))); break;
                case 'j': sb.append(operand(jumps(), n)); break;
                default:  throw new Error("bad assembly format: " + assem);
            }
        }

        return sb.toString();
    }

    private <E> E operand(List<E> list, int n) {
        if (list == null || n >= list.size())
            throw new Error("missing operand " + n + " in: " + assem);
        return list.get(n);
    }

    private static <T> String name(Map<NameOfTemp, T> map, NameOfTemp t) {
        T mapped = map == null ? null : map.get(t);
        return mapped == null ? t.toString() : mapped.toString();
    }

    @Override
    public String toString() { return format(); }
}

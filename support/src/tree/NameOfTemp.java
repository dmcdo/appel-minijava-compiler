package tree;

/**
 * The name of a temporary (an abstract register).  Two temporaries with the
 * same name are equal, so machine registers can be written new NameOfTemp("%o0").
 */
public final class NameOfTemp implements Comparable<NameOfTemp> {
    private static int count = 0;

    private final String name;

    public NameOfTemp(String name) {
        if (name == null)
            throw new IllegalArgumentException("null temporary name");
        this.name = name;
    }

    /** A fresh temporary: t001, t002, ... */
    public static NameOfTemp generateTemp() {
        return generateTemp("t");
    }

    public static NameOfTemp generateTemp(String prefix) {
        return new NameOfTemp(String.format("%s%03d", prefix, ++count));
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof NameOfTemp && name.equals(((NameOfTemp)o).name);
    }

    @Override
    public int hashCode() { return name.hashCode(); }

    @Override
    public int compareTo(NameOfTemp o) { return name.compareTo(o.name); }

    @Override
    public String toString() { return name; }
}

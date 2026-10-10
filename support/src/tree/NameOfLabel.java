package tree;

/**
 * The name of an assembly label.  A name built from several parts is joined
 * with '$', e.g. new NameOfLabel("Factorial", "main") is Factorial$main.
 */
public final class NameOfLabel implements Comparable<NameOfLabel> {
    private static int count = 0;

    private final String name;

    public NameOfLabel(String... parts) {
        if (parts == null || parts.length == 0)
            throw new IllegalArgumentException("empty label name");
        this.name = String.join("$", parts);
    }

    /** A fresh label: L$001, L$002, ... */
    public static NameOfLabel generateLabel() {
        return generateLabel("L");
    }

    /** A fresh label starting with the given parts, e.g. if$then$003. */
    public static NameOfLabel generateLabel(String... parts) {
        String[] all = new String[parts.length + 1];
        System.arraycopy(parts, 0, all, 0, parts.length);
        all[parts.length] = String.format("%03d", ++count);
        return new NameOfLabel(all);
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof NameOfLabel && name.equals(((NameOfLabel)o).name);
    }

    @Override
    public int hashCode() { return name.hashCode(); }

    @Override
    public int compareTo(NameOfLabel o) { return name.compareTo(o.name); }

    @Override
    public String toString() { return name; }
}

public class X extends Gate {
    private static final double[][] gate = {{0, 1},
                                            {1, 0}};
    public X(int actingOnQubit) {
        super(gate, actingOnQubit, "X");
    }
}

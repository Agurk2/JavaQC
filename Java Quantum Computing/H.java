public class H extends Gate {
    private static final double[][] gate = {{1 / Math.sqrt(2), 1 / Math.sqrt(2)},
                                            {1 / Math.sqrt(2), -1 / Math.sqrt(2)}};
    public H(int actingOnQubit) {
        super(gate, actingOnQubit, "H");
    }
}
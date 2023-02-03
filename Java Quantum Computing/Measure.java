public class Measure extends Gate {
    private static final double[][] gate = {{1, 0}, 
                                            {0, 1}};
    public Measure(int qubit) {
        super(gate, qubit, "Measure");
    }
}
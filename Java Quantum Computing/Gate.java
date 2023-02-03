/**
 * Abstract Gate class which stores basic information about a gate
 */
public abstract class Gate {
    private final double[][] gate;
    private int aoq;
    public String ref;
    /**
     * Constructor for objects of class Gate
     */
    public Gate(double[][] g, int actingOnQubit, String reference) {
        this.aoq = 0;
        this.gate = g;
        this.aoq = actingOnQubit;
        this.ref = reference;
    }

    public double[][] getGate() {
        return this.gate;
    }

    public String getReference() {
        return this.ref;
    }
}

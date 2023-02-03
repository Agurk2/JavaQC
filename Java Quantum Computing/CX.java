/**
 * This class was never implemented correctly due to the
 * special nature of CNOTs
 */
public class CX extends Gate {
    private final int c, t;
    private static final double[][] gate = {{1, 1},
                                            {1, 1}};
    public CX(int control, int target) {
        super(gate, control, "CX");
        this.c = control;
        this.t = target;
    }

    private int[] returnControlTarget() {
        int[] temp = {c, t};
        return temp;
    }
}
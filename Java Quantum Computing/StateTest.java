/**
 * StateTest class test that the State class is properly functioning 
 */
public class StateTest {
    State s = new State();
    private static final double[][] gate = {{0, 1},
    {1, 0}};
    double[] ccc = s.multiplyMatrix(gate);

    public void test() {
        System.out.println("Testing matrix multiplication with vector");
        for (int i = 0; i < ccc.length; i++) {
            System.out.println(ccc[i]);
        }
        for (int i = 0; i < s.getState().length; i++) {
            System.out.println(s.getState()[i]);
        }
        System.out.println();
        System.out.println("Testing matrix multiplcation with matrix");
        double[][] I = {{1, 0}, {0, 1}};
        double[][] temp = s.multiplyMatrix(gate, I);
        for (double[] temp1 : temp) {
            for (int c = 0; c < temp1.length; c++) {
                System.out.println(temp1[c]);
            }
        }
    }
}
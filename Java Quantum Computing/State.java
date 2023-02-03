/**
 * Class State controls and updates the state of the qubit
 */
public class State {
    private double[] state = {1, 0};
    public void updateState(double[][] gate) {
        this.state = multiplyMatrix(gate);
    }

    /**
     * @param matrix.length = 2
     * @returns temp
     */
    public double[] multiplyMatrix(double[][] matrix) {
        double[] temp = new double[2];

        temp[0] = this.state[0] * matrix[0][0] + this.state[1] * matrix[0][1];
        temp[1] = this.state[0] * matrix[1][0] + this.state[1] * matrix[1][1];

        return temp;
    }

    /**
     * @param first.length = 2, second.length = 2
     * @returns temp
     */
    public double[][] multiplyMatrix(double[][] first, double[][] second) {
        double[][] temp = new double[2][2];

        temp[0][0] = first[0][0] * second[0][0] + first[0][1] * second[1][0];
        temp[0][1] = first[0][0] * second[0][1] + first[0][1] * second[1][1];
        temp[1][0] = first[1][0] * second[0][0] + first[1][1] * second[1][0];
        temp[1][1] = first[1][0] * second[0][1] + first[1][1] * second[1][1];

        return temp;
    }

    /**
     * @returns state
     */
    public double[] getState() {
        return this.state;
    }
}

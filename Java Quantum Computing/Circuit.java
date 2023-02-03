import java.util.ArrayList;
import java.text.DecimalFormat;
/**
 * The circuit class implements all the other classes into one collective circuit. When extended, a user will be able to
 * simulate an actual quantum computer, although at this version, H and X gates are only available
 * 
 * Version@1.0.0
 * Author@Onorregaard
 */
public class Circuit {
    // Keeps track of the gates
    private final ArrayList<Gate>[] gates;
    
    // Keeps track of the states
    private final ArrayList<State> states;
    
    // Stores the size of the circuit
    private final int qubits;
    
    /**
     * Main constructor for class Circuit.
     * 
     * Note that the circuit functions index-vise like an Array.
     * - Cannot change the size once instansiated
     * - Index starts at 0 and goes to size-1
     */
    public Circuit(int size) {
        this.gates = new ArrayList[size];
        this.states = new ArrayList(size);;
        this.qubits = size;

        for (int i = 0; i < size; i++) {
            gates[i] = new ArrayList<>();
            states.add(new State());
        }
    }
    
    /**
     * Add a NOT gate
     */
    public void x(int qubit) {
        X x = new X(qubit);
        this.gates[qubit].add(x);
    }
    
    /**
     * Add a hadamard gate
     */
    public void h(int qubit) {
        H hadamard = new H(qubit);
        this.gates[qubit].add(hadamard);
    }
    
    /**
     * Add a cx gate
     * 
     * Note: this is not functional
     */
    public void cx(int control, int target) {
        CX cnot = new CX(control, target);
        this.gates[control].add(cnot);
    }
    
    /**
     * Add a measurement gate
     */
    public void measure(int qubit) {
        Measure measurent = new Measure(qubit);
        this.gates[qubit].add(measurent);
    }
    
    /**
     * Generates the possible bit combinations with n size
     * @Returns temp which stores the bit combinations
     */
    public ArrayList getBitCombinations(int n) {
        ArrayList temp = new ArrayList<String>();
        for (int i = 0; i < Math.pow(2, n); i++) {
            String currentBit = Integer.toBinaryString(i);
            while (currentBit.length() < Integer.toBinaryString((int) Math.pow(2, n)).length() - 1) {
                currentBit = "0" + currentBit;
            }
            temp.add(currentBit);
        }
        return temp;
    }

    public void execute(double shots) {
        
        ArrayList<Integer> results = new ArrayList<Integer>(); // Stores raw results
        for (int i = 0; i < qubits; i++) { // Loops throught the circuit
            for (int j = 0; j < gates[i].size(); j++) {
                states.get(i).updateState(gates[i].get(j).getGate());
                if (gates[i].get(j).getReference().equals("Measure")) { // If the gate is a measurement gate
                    double zero = 0;
                    double one = 0;
                    // Takes two measurement for the 0 and 1 state
                    zero = shots * ((double) Math.round(states.get(0).getState()[0] * states.get(0).getState()[0] * 100) / 100);
                    one = shots * ((double) Math.round(states.get(0).getState()[1] * states.get(0).getState()[1] * 100) / 100);
                    
                    // Casting to fix an error
                    int izero = (int) zero;
                    int ione = (int) one;
                    
                    // Add it to the results
                    results.add(izero);
                    results.add(ione);
                }
            }
        }
        // for (int i = 0; i < results.size(); i++) {
        // System.out.println("|" + i + "|: " + results.get(i));
        // }

        ArrayList<String> bitCombination = getBitCombinations(results.size()/2); // Stores the possible combinations of bits
        ArrayList<Integer> processedBits = new ArrayList<Integer>(); // Stores the processed bit combinations
        for(int i = 0; i < bitCombination.size(); i++)
        {
            String temp = "9" + bitCombination.get(i);
            int bit = Integer.parseInt(temp);
            // System.out.println(bit);
            while (bit > 0) {
                if(bit != 9)
                {
                    processedBits.add(bit%10);
                }
                bit /= 10;
            }

        }
        
        ArrayList<Integer> sortedRes = new ArrayList<>(); // Stores the results in relation to the bit
        for(int i = 0; i < processedBits.size(); i++)
        {
            int res = 0;
            int step = 0;
            for(int x = 0; x < qubits/2; x++)
            {
                if(processedBits.get(i+x) == 0)
                {
                    res += results.get(step);
                    step += 2;
                }
                else{
                    res += results.get(step+1);
                    step += 2;
                }
            }
            sortedRes.add(res);
        }

        ArrayList<Integer> temp = new ArrayList<>(); // Assign the results to the correct bit
        int divisor = 0; // For percentage
        for(int i = 0; i < sortedRes.size(); i += 2) // Assigns each bit combination with the proper outcomes
        {
            // Checks that the bit combination is possible
            if(sortedRes.get(i) == 0 || sortedRes.get(i+1) == 0)
            {
                temp.add(0);
            }
            else{
                temp.add((sortedRes.get(i)+sortedRes.get(i+1)));
                divisor += sortedRes.get(i)+sortedRes.get(i+1);
           }
            
        }
        
        // Outcome of the circuit
        System.out.println("Outcome of circuit");
        System.out.println("------------------");
        for(int i = 0; i < temp.size(); i++)
        {
            System.out.println("|" + bitCombination.get(i) + "| " + (double)temp.get(i)/divisor*100 + "%");
        }
        
        
        /**
         * I left this code here for you to see the struggle I went through... Sometimes coding can just be
         * too much.
         */
        // for(int i = 0; i < sortedRes.size(); i++)
        // {
            // System.out.println(bitCombination.get(i) + " : " + sortedRes.get(i));
        // }
        // for(int i = 0; i < bitCombination.size(); i++)
        // {
            // System.out.println(i);
        // }
        // System.out.println("---");
        // for(int i = 0; i < results.size(); i++)
        // {
            // System.out.println(i);
        // }
        // System.out.println("---");
        // for(int i = 0; i < processedBits.size(); i++)
        // {
            // System.out.println(i);
        // }
        // System.out.println("---");
        // int kl = 0;
        // int ol = 0;
        // for(int i = 0; i < bitCombination.size(); i++)
        // {
            // kl = 0;
            // int n = i*2;
            // //System.out.println(n);
            // //System.out.println(n+1);
            // if(processedBits.get(n) == 0)
            // {
                // kl += results.get(i);
            // }
            // if(processedBits.get(n) == 1)
            // {
                // kl += results.get(i);
            // }
            // System.out.println(kl);
        // }
        // System.out.println("---");
        // System.out.println("For 00: " + (results.get(0) + results.get(2)));
        // System.out.println("For 01: " + (results.get(0) + results.get(3)));
        // System.out.println("For 10: " + (results.get(1) + results.get(2)));
        // System.out.println("For 11: " + (results.get(1) + results.get(3)));

        // System.out.println("---");
        // int p = 0;
        // for(int currBitComb = 0; currBitComb < bitCombination.size(); currBitComb++)
        // {
            // for(int n = 0; n < 2; n++)
            // {
                // int x = currBitComb*2;
                // System.out.println(x);
                // System.out.println(x+1);
            // }
        // }
        // ArrayList results = new ArrayList<Integer>();
        // ArrayList bits = getBitCombinations(qubits);
        // for(int i = 0; i < qubits*2; i++)
        // {
        // }
        // int y = 0;
        // for(int i = 0; i < gates[y].size(); i++)
        // {
            // while(y < qubits)
            // {
                // states.get(i).updateState(gates[i].get(y).getGate());
                // y++;
            // }
            // y = 0;
        // }
        // for(State s: states)
        // {
            // for(int i = 0; i < 2; i++)
            // {
                // System.out.println(s.getState()[i]);
            // }
        // }
    }

}

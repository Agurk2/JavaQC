/**
 * CircuitTest class tests that the Circuit class properly function.
 */
public class CircuitTest {
    Circuit circ = new Circuit(2);
    public void main() {
        // Feel free to comment and uncomment out any of the code below.
        
        // NOT gates
        
        circ.x(0);
        circ.x(1);
        
        // Hadamard (or 50/50 gates)
        
        // circ.h(0);
        // circ.h(1);
        
        // Measurement - which qubits you will measure
        
        circ.measure(0);
        circ.measure(1);
        
        // Executes the circuit. Change the parameter to change the shots
        
        circ.execute(1024);
    }
}
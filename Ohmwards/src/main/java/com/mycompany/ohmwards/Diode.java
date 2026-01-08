/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ohmwards;

import java.util.Vector;

/**
 *
 * @author Cameron
 */
public class Diode extends Component {
    private boolean electronFlow;
    
    public Diode(boolean electronFlow, Direction diodeDirection, ConnectionType type, Vector<Double> position, double voltage, double current, double resistance, double power, String name) {
        super(type, position, voltage, current, resistance, power, name, diodeDirection); // Change this to direction enum
        this.electronFlow = electronFlow;
    }
   
    public boolean getElectronFlow() {
        return electronFlow;
    }
    
    public void setElectronFlow(boolean newFlow) {
        this.electronFlow = newFlow;
    }
    
    public void Break() {
        // TODO!
    }
}
